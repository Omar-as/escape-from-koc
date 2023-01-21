package screens.run;

import managers.AccountManager;
import managers.DataStoreManager;
import managers.KeyManager;
import managers.ScreenManager;
import models.*;
import models.alien.*;
import models.objects.Obj;
import models.powerUps.PowerUp;
import models.powerUps.PowerUpType;
import screens.Backend;
import screens.ScreenFactory;
import utils.Constants;
import utils.RandomUtils;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static models.alien.AlienType.SHOOTER;
import static models.alien.AlienType.TIME_WASTING;

public class RunModeBackend implements Backend<RunModeState> {
    @Override
    public void updateState(RunModeState state) {
        if (state.isCompleted()) return;
        state.incFrames();

        movePlayer(state);
        usePowerUp(state);

        for (var alien : state.getShooterAliens()) shooterAlienBehaviour(alien, state);
        for (var alien : state.getBlindAliens()) blindAlienBehaviour(alien, state);
        var timeWastingAliens = state.getTimeWastingAliens();
        for (int i = timeWastingAliens.size() - 1; i >= 0; i--) timeWastingAlienBehaviour(timeWastingAliens.get(i), state);

        var powerUpArrayLength = state.getPowerUps().size();
        for (int i = 0; i < powerUpArrayLength; i++) {
            var powerUp = state.getPowerUps().get(i);
            if (powerUp.getDespawnTimer() <= 0) {
                state.getPowerUps().remove(powerUp);
                powerUpArrayLength--;
                i--;
            }
            powerUp.decTimer();
        }

        fireProjectile(state);

        if ((state.getTimeoutAfter() <= 0 && !state.isCompleted()) || state.getPlayer().getLives() == 0) {
            state.setCompleted();
            ScreenManager.getInstance().setScreen(ScreenFactory.getGameEndScreen(false));
        }
        state.decTimeoutAfter();

        if (state.getTimeForNextAlien() <= 0) {
            spawnAlien(new Random(), state);

            state.resetTimeForNextAlien();
        }
        state.decTimeForNextAlien();
        if (state.getTimeForNextPowerUp() <= 0) {
            spawnPowerUp(new Random(), state);
            state.resetTimeForNextPowerUp();
        }
        state.decTimeForNextPowerUp();
        hintPowerUpBehaviour(state);
        protectionVestPowerUpBehaviour(state);

    }

    public void movePlayer(RunModeState state) {
        // MODIFIES: player.xPosition, player.Yposition
        // EFFECTS1: Key pressed right Player Movesby PLAYER_SPEED to right
        // EFFECTS2: Key pressed left Player Movesby PLAYER_SPEED to left
        // EFFECTS3: Key pressed up Player Movesby PLAYER_SPEED to up
        // EFFECTS4: Key pressed down Player Movesby PLAYER_SPEED to down
        // EFFECTS5: Key pressed down + right Player Moves by PLAYER_SPEED to down + right

        // Check whether keys are pressed or not
        var isUpPressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_UP);
        var isLeftPressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_LEFT);
        var isDownPressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_DOWN);
        var isRightPressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_RIGHT);

        // Horizontal Displacement
        // moveBy = numSteps * speed = 1 * speed = speed
        var moveBy = Constants.PLAYER_SPEED;

        // If we are moving diagonally, keep speed consistent:
        // moveByDiagonal = sqrt(2   * (moveByXY       ^ 2))
        // moveByXY       = sqrt(1/2 * (moveByDiagonal ^ 2))
        var movingDiagonally = (isUpPressed || isDownPressed) && (isLeftPressed || isRightPressed);
        if (movingDiagonally) moveBy = (int) Math.sqrt(Math.pow(moveBy, 2) / 2);

        var player = state.getPlayer();

        var backupPosition = player.getPosition();

        // Move player
        if (isUpPressed) player.setYPosition(Math.max(player.getPosition().getY() - moveBy, 0));
        if (isLeftPressed) player.setXPosition(Math.max(player.getPosition().getX() - moveBy, 0));
        if (isDownPressed)
            player.setYPosition(Math.min(player.getPosition().getY() + moveBy, state.getHeight() - player.getHeight()));
        if (isRightPressed)
            player.setXPosition(Math.min(player.getPosition().getX() + moveBy, state.getWidth() - player.getWidth()));

        // Collision prevention
        var objects = state.getRooms()[state.getCurrentRoom()].getObjects();
        for (var obj : objects)
            if (player.intersects(obj)) {
                player.setPosition(backupPosition);
            }
        if (player.intersects(state.getDoor())) {
            if (state.getKey().isFound() && state.getShowKeyFor() == 0) {
                if (state.getCurrentRoom() == state.getRooms().length - 1) {
                    state.setCompleted();
                    DataStoreManager.getInstance().addToCollection(
                            Constants.SCOREBOARD_COLLECTION_NAME,
                            new GameData(AccountManager.getUsername(), state.getFrames() * (int) Constants.REPAINT_DELAY_MILLS / Constants.SECOND_MILLS),
                            GameData.class
                    );
                    ScreenManager.getInstance().setScreen(ScreenFactory.getGameEndScreen(true));
                } else {
                    state.incCurrentRoom();
                    state.setKey(new Random());
                    state.resetTimeoutAfter();
                    state.getShooterAliens().clear();
                    state.getBlindAliens().clear();
                    state.getTimeWastingAliens().clear();
                    state.setProjectiles(new ArrayList<>());
                    state.setPowerUps(new ArrayList<>());
                    state.resetTimeForNextAlien();
                    state.resetTimeForNextPowerUp();
                    player.setPosition(Constants.STARTING_X, Constants.STARTING_Y);
                    state.setPlasticBottleActive(false);
                }
            } else player.setPosition(backupPosition);
        }

        state.decShowKeyFor();
    }

    public void usePowerUp(RunModeState state) {
        var player = state.getPlayer();

        var isHintPressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_H);
        var isProtectionVestPressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_P);
        var isPlasticBottlePressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_B);

        if (KeyManager.getInstance().isKeyPressed(KeyEvent.VK_W)) state.setPlasticBottleDir(new int[] {0, -1});
        if (KeyManager.getInstance().isKeyPressed(KeyEvent.VK_A)) state.setPlasticBottleDir(new int[] {-1, 0});
        if (KeyManager.getInstance().isKeyPressed(KeyEvent.VK_D)) state.setPlasticBottleDir(new int[] {1, 0});
        if (KeyManager.getInstance().isKeyPressed(KeyEvent.VK_X)) state.setPlasticBottleDir(new int[] {0, 1});

        if (isHintPressed && player.getBagHints() > 0 && !player.getIsHint()) {
//            hintPowerUpBehaviour(state);
            player.setIsHint(true);
            state.resetHintEffectTimer();
            player.decBagHints();
        }
        if (isProtectionVestPressed && player.getBagProtectionVests() > 0 && !player.getIsProtectionVest()) {
//            protectionVestPowerUpBehaviour(state);
            player.setIsProtectionVest(true);
            state.resetProtectionVestEffectTimer();
            player.decBagProtectionVests();
        }
        if(isPlasticBottlePressed && player.getBagPlasticBottles() > 0 && !state.isPlasticBottleActive()) {
            state.setPlasticBottleActive(true);
            player.decBagPlasticBottles();
        }
    }

    public void pickupPowerUp(RunModeState state, int clickX, int clickY) {
        var player = state.getPlayer();
        var powerUpLength = state.getPowerUps().size();
        for (int i = 0; i < powerUpLength; i++) {
            var powerup = state.getPowerUps().get(i);
            if (clickX >= powerup.getPosition().getX() &&
                    clickX <= powerup.getPosition().getX() + powerup.getWidth() &&
                    clickY >= powerup.getPosition().getY() &&
                    clickY <= powerup.getPosition().getY() + powerup.getHeight()) {
                if (powerup.getType() == PowerUpType.ExtraTime) {
                    extraTimePowerUpBehaviour(state);
                } else if (powerup.getType() == PowerUpType.Hint) {
                    player.incBagHints();
                } else if (powerup.getType() == PowerUpType.ProtectionVest) {
                    player.incBagProtectionVests();
                } else if (powerup.getType() == PowerUpType.PlasticBottle) {
                    player.incBagPlasticBottles();
                } else if (powerup.getType() == PowerUpType.ExtraLife) {
                    extraLifePowerUpBehaviour(state);
                }
                state.getPowerUps().remove(powerup);
                powerUpLength--;
                i--;
            }
        }
    }

    public void pickupKey(RunModeState state, int clickX, int clickY) {
        if (state.getKey().isFound()) return;
        var under = state.getKey().getUnder();
        var underX = under.getPosition().getX();
        var underY = under.getPosition().getY();
        var player = state.getPlayer();
        var distance = player.distanceTo(under);
        if (clickX >= underX && clickX <= underX + under.getWidth() && clickY >= underY && clickY <= underY + under.getHeight() && distance <= Constants.MIN_DISTANCE) {
            state.getKey().setFound();
            state.setShowKeyFor((int) (Constants.SECOND_MILLS / Constants.REPAINT_DELAY_MILLS));
        }
    }

    private void spawnAlien(Random random, RunModeState state) {
        var room = state.getRooms()[state.getCurrentRoom()];
        var alienType = AlienType.values()[random.nextInt(AlienType.values().length)];

        Alien alien;
        if (alienType == SHOOTER) {
            var shooter = new ShooterAlien(0, 0, Constants.ALIEN_DIM, Constants.ALIEN_DIM);
            state.getShooterAliens().add(shooter);
            alien = shooter;
        } else if (alienType == TIME_WASTING) {
            var waster = new TimeWastingAlien(0, 0, Constants.ALIEN_DIM, Constants.ALIEN_DIM);
            state.getTimeWastingAliens().add(waster);
            alien = waster;
        } else {
            var blind = new BlindAlien(0, 0, Constants.ALIEN_DIM, Constants.ALIEN_DIM);
            state.getBlindAliens().add(blind);
            alien = blind;
        }
        RandomUtils.runModeRandomize(state, room, alien);
    }

    private void spawnPowerUp(Random random, RunModeState state) {
        Room room = state.getRooms()[state.getCurrentRoom()];
        PowerUp powerUp = new PowerUp(PowerUpType.values()[random.nextInt(PowerUpType.values().length)], 0, 0, Constants.POWER_UP_DIM, Constants.POWER_UP_DIM);
        RandomUtils.runModeRandomize(state, room, powerUp);
        state.getPowerUps().add(powerUp);
    }

    private void blindAlienBehaviour(BlindAlien alien, RunModeState state) {
        var random = new Random();
        var player = state.getPlayer();
        if (alien.distanceTo(player) <= Constants.BLIND_ALIEN_DEADLY_RANGE * Constants.BLOCK_DIM) {
            player.setPosition(0, 0);
            RandomUtils.runModeRandomize(state, state.getRooms()[state.getCurrentRoom()], player);
            player.setLives(player.getLives() - 1);
        }
        alien.decFramesTillSwitch();
        if (alien.getFramesTillSwitch() <= 0 && !state.isPlasticBottleActive()) {
            int[][] directions = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
            var direction = directions[random.nextInt(directions.length)];
            alien.setDirection(direction[0], direction[1]);
            alien.resetFramesTillSwitch();
        } else if (alien.getFramesTillSwitch() <= 0 && state.isPlasticBottleActive()) {
            alien.setDirection(state.getPlasticBottleDir()[0], state.getPlasticBottleDir()[1]);
        }

        var backup = alien.getPosition();
        alien.setPosition(
                Math.min(Math.max(alien.getPosition().getX() + alien.getDirectionX() * Constants.BLIND_SPEED, 0), state.getWidth() - alien.getWidth()),
                Math.min(Math.max(alien.getPosition().getY() + alien.getDirectionY() * Constants.BLIND_SPEED, 0), state.getHeight() - alien.getHeight())
        );
        var objs = state.getRooms()[state.getCurrentRoom()].getObjects().stream();
        var others = Arrays.stream(new Rectangle[]{state.getDoor()});
        var shooters = state.getShooterAliens().stream();
        var blinds = state.getBlindAliens().stream().filter(a -> !a.equals(alien));
        var wasters = state.getTimeWastingAliens().stream();
        var powerUps = state.getPowerUps().stream();
        var avoid = Stream.of(objs, others, shooters, blinds, wasters, powerUps).reduce(Stream::concat).orElseGet(Stream::empty).toArray(Rectangle[]::new);
        for (var a : avoid) {
            if (alien.intersects(a)) {
                alien.setPosition(backup);
                return;
            }
        }

        alien.alternateSprite();
    }

    private void timeWastingAlienBehaviour(TimeWastingAlien alien, RunModeState state) {
        if (state.getKey().isFound()) return;

        if (state.getPercentPassed() < Constants.PERCENT_PASSED_FRIENDLY) alien.setFactor(new FriendlyStrategy());
        else if (state.getPercentPassed() > Constants.PERCENT_PASSED_AGGRESSIVE) alien.setFactor(new AggressiveStrategy());
        else alien.setFactor(new ConfusedStrategy());

        alien.decFramesTillAction();

        if (alien.getFramesTillAction() <= 0) {
            if (alien.getFactor() == new FriendlyStrategy().getFramesTillAction()) {
                switchKey(state);
                state.getTimeWastingAliens().remove(alien);
            } else if (alien.getFactor() == new ConfusedStrategy().getFramesTillAction()) {
                state.getTimeWastingAliens().remove(alien);
            } else {
                switchKey(state);
            }

            alien.resetFramesTillAction();
        }
    }

    private void switchKey(RunModeState state) {
        var random = new Random();
        var room = state.getRooms()[state.getCurrentRoom()];
        var objects = room.getObjects();

        Obj randObj;
        do {
            randObj = objects.get(random.nextInt(objects.size()));
        } while (randObj == state.getKey().getUnder());

        state.setKey(new Key(randObj));
    }

    private void shooterAlienBehaviour(ShooterAlien alien, RunModeState state) {
        var player = state.getPlayer();
        if (alien.distanceTo(player) <= Constants.SHOOTER_ALIEN_DEADLY_RANGE * Constants.BLOCK_DIM && !player.isProtectionVest()) {
            player.setPosition(0, 0);
            RandomUtils.runModeRandomize(state, state.getRooms()[state.getCurrentRoom()], player);
            player.setLives(player.getLives() - 1);
        }
        alien.decFramesTillShoot();
        if (alien.getFramesTillShoot() <= 0) {
            var projectiles = state.getProjectiles();
            var aim = shooterAlienAim(alien, player);
            var projectile = new Projectile(aim, alien.getPosition().getX() + alien.getWidth() / 2, alien.getPosition().getY() + alien.getHeight() / 2, 20, 20);
            projectiles.add(projectile);
            alien.resetFramesTillShoot();
        }
    }

    public float[] shooterAlienAim(ShooterAlien alien, Player player) {
        var alienCenterX = alien.getPosition().getX() + alien.getWidth() / 2;
        var alienCenterY = alien.getPosition().getY() + alien.getHeight() / 2;
        var playerCenterX = player.getPosition().getX() + player.getWidth() / 2;
        var playerCenterY = player.getPosition().getY() + player.getHeight() / 2;

        int xDirection = playerCenterX - alienCenterX;
        int yDirection = playerCenterY - alienCenterY;

        float distance = (float) Math.sqrt(Math.pow(xDirection, 2) + Math.pow(yDirection, 2));

        float unitX = (float) xDirection / distance;
        float unitY = (float) yDirection / distance;

        return new float[]{unitX, unitY};
    }

    private void fireProjectile(RunModeState state) {
        var player = state.getPlayer();
        var projectiles = state.getProjectiles();
        var projectileArrayLength = projectiles.size();
        var objects = state.getRooms()[state.getCurrentRoom()].getObjects();

        for (int i = 0; i < projectileArrayLength; i++) {

            var projectile = projectiles.get(i);
            projectile.move();

            var checkOutOfBounds = (projectile.getPosition().getX() < 0 ||
                    projectile.getPosition().getY() < 0 ||
                    projectile.getPosition().getX() + projectile.getHeight() > Constants.FRAME_WIDTH ||
                    projectile.getPosition().getY() + projectile.getHeight() > Constants.FRAME_HEIGHT);

            var intersects = objects.stream()
                    .map(projectile::intersects)
                    .mapToInt(b -> b ? 1 : 0)
                    .sum() != 0;
            if (projectile.intersects(player) && !player.getIsProtectionVest()) {
                player.setPosition(0, 0);
                player.setLives(player.getLives() - 1);
                projectiles.remove(projectile);
                i--;
                projectileArrayLength--;
            } else if (intersects || checkOutOfBounds) {
                projectiles.remove(projectile);
                i--;
                projectileArrayLength--;
            } else if (projectile.intersects(player) && player.getIsProtectionVest()) {
                projectiles.remove(projectile);
                i--;
                projectileArrayLength--;
            }
        }
    }

    private void extraLifePowerUpBehaviour(RunModeState state) {
        var player = state.getPlayer();
        player.setLives(player.getLives() + 1);
    }

    private void extraTimePowerUpBehaviour(RunModeState state) {
        state.incTimeoutAfter(5);
    }

    private void protectionVestPowerUpBehaviour(RunModeState state) {
        var player = state.getPlayer();
        if (player.getIsProtectionVest() && state.getProtectionVestEffectTimer() <= 0) {
            player.setProtectionVest(false);
        } else {
            state.decProtectionVestEffectTimer();
        }

    }

    private void hintPowerUpBehaviour(RunModeState state) {
        var player = state.getPlayer();
        if (player.getIsHint() && state.getHintEffectTimer() <= 0) {
            player.setIsHint(false);
        } else {
            state.decHintEffectTimer();
        }
    }
}
