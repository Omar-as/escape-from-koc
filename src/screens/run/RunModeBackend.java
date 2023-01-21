package screens.run;

import managers.AccountManager;
import managers.DataStoreManager;
import managers.KeyManager;
import managers.ScreenManager;
import models.*;
import models.alien.Alien;
import models.alien.AlienType;
import models.objects.Obj;
import models.powerUps.PowerUp;
import models.powerUps.PowerUpType;
import screens.Backend;
import screens.ScreenFactory;
import utils.Constants;
import utils.RandomUtils;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class RunModeBackend implements Backend<RunModeState> {
    @Override
    public void updateState(RunModeState state) {
        if (state.isCompleted()) return;
        state.incFrames();

        movePlayer(state);
        usePowerUp(state);

        //TODO: remove iterator
        var alienArrayLength = state.getAliens().size();
        for (int i = 0; i < alienArrayLength; i++) {
            var alien = state.getAliens().get(i);
            switch (alien.getType()) {
                case BLIND:
                    blindAlienBehaviour(alien, state);
                    break;
                case TIME_WASTING:
                    timeWastingAlienBehaviour(alien, state);
                    if (alien == null) {
                        alienArrayLength--;
                        i--;
                    }
                    break;
                case SHOOTER:
                    shooterAlienBehaviour(alien, state);
                    break;
            }
        }

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
                    state.setAliens(new ArrayList<>());
                    state.setProjectiles(new ArrayList<>());
                    state.setPowerUps(new ArrayList<>());
                    state.resetTimeForNextAlien();
                    state.resetTimeForNextPowerUp();
                    player.setPosition(Constants.STARTING_X, Constants.STARTING_Y);
                }
            } else player.setPosition(backupPosition);
        }

        state.decShowKeyFor();
    }

    public void usePowerUp(RunModeState state) {
        var player = state.getPlayer();

        var isHintPressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_H);
        var isProtectionVestPressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_P);

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
//                    hintPowerUpBehaviour(state);
                    player.incBagHints();
                } else if (powerup.getType() == PowerUpType.ProtectionVest) {
//                    protectionVestPowerUpBehaviour(state);
                    player.incBagProtectionVests();
                } else if (powerup.getType() == PowerUpType.PlasticBottle) {

                    // To Be Changeddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd
//                    protectionVestPowerUpBehaviour(state);
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
        Room room = state.getRooms()[state.getCurrentRoom()];
        Alien alien = new Alien(AlienType.values()[random.nextInt(AlienType.values().length)], 0, 0, Constants.ALIEN_DIM, Constants.ALIEN_DIM);

        if (alien.getType() == AlienType.TIME_WASTING) {
            var time = (((long) state.getRooms()[state.getCurrentRoom()].getObjects().size() * 5 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
            alien.setTimePercentLeftWhenSpawned((int) (((float) state.getTimeoutAfter() / time) * (float) 100.0));
            alien.setTimeLeftWhenSpawned((int) (state.getTimeoutAfter()));
            alien.setMode();
        }

        RandomUtils.runModeRandomize(state, room, alien);
        state.getAliens().add(alien);
    }

    private void spawnPowerUp(Random random, RunModeState state) {
        Room room = state.getRooms()[state.getCurrentRoom()];
        PowerUp powerUp = new PowerUp(PowerUpType.values()[random.nextInt(PowerUpType.values().length)], 0, 0, Constants.POWER_UP_DIM, Constants.POWER_UP_DIM);
        RandomUtils.runModeRandomize(state, room, powerUp);
        state.getPowerUps().add(powerUp);
    }

    private void blindAlienBehaviour(Alien alien, RunModeState state) {
        var done = false;
        var xPosition = alien.getPosition().getX();
        var yPosition = alien.getPosition().getY();
        var objs = state.getRooms()[state.getCurrentRoom()].getObjects();
        var door = state.getDoor();
        var player = state.getPlayer();
        var intersects = 0;
        alien.decActionTimeOut();
        while (!done && !alien.intersects(player)) {
            if (alien.getActionTimeOut() <= 0 || intersects != 0) {
                alien.setCurrentDirectionRandomly();
                alien.setPosition(alien.getPosition().getX() - alien.getCurrentDirection()[0], alien.getPosition().getY() - alien.getCurrentDirection()[1]);
                alien.resetActionTimeOut();
            }
            xPosition = alien.getPosition().getX() + (2 * alien.getCurrentDirection()[0]);
            yPosition = alien.getPosition().getY() + (2 * alien.getCurrentDirection()[1]);
            var dummyRect = new Rectangle(new Position(xPosition, yPosition), alien.getWidth(), alien.getHeight());
            intersects = objs.stream()
                    .map(dummyRect::intersects)
                    .mapToInt(b -> b ? 1 : 0)
                    .sum()
                    + (dummyRect.intersects(door) ? 1 : 0)
                    + ((xPosition < 0 || yPosition < 0 || yPosition > (state.getHeight() - alien.getHeight()) || xPosition > (state.getWidth() - alien.getWidth())) ? 1 : 0);
            done = intersects == 0;
        }
        if (alien.intersects(player)) {
            player.setLives(player.getLives() - 1);
            player.setPosition(0, 0);
        }
        alien.setPosition(xPosition, yPosition);
    }

    private void timeWastingAlienBehaviour(Alien alien, RunModeState state) {
        if (state.getKey().isFound()) return;
        var random = new Random();
        var room = state.getRooms()[state.getCurrentRoom()];
        var objects = room.getObjects();
        int timePassed;
        int confusionDelay;

        Obj randObj;
        do {
            randObj = objects.get(random.nextInt(objects.size()));
        } while (randObj == state.getKey().getUnder());

        switch (alien.getMode()) {
            case CONFUSED:
                timePassed = (int) (alien.getTimeLeftWhenSpawned() - state.getTimeoutAfter());
                confusionDelay = (int) ((2 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
                if (timePassed < confusionDelay) {
                    return;
                } else if (timePassed > confusionDelay) {
                    state.getAliens().remove(alien);
                    return;
                }
                break;
            case NORMAL:
                alien.decActionTimeOut();
                if (alien.getActionTimeOut() <= 0) {
                    state.setKey(new Key(randObj));
                    alien.resetActionTimeOut();
                }
                break;
            case PETTY:
                timePassed = (int) (alien.getTimeLeftWhenSpawned() - state.getTimeoutAfter());
                confusionDelay = (int) ((Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
                if (timePassed < confusionDelay) {
                    state.setKey(new Key(randObj));
                    state.getAliens().remove(alien);
                }
                break;
        }

    }

    private void shooterAlienBehaviour(Alien alien, RunModeState state) {
        var player = state.getPlayer();
        if (alien.distanceTo(player) <= 64) {
            player.setPosition(0, 0);
            player.setLives(player.getLives() - 1);
        }
        alien.decActionTimeOut();
        if (alien.getActionTimeOut() <= 0) {
            var projectiles = state.getProjectiles();
            var projectile = new Projectile(alien.aim(state.getPlayer()), alien.getPosition().getX() + alien.getWidth() / 2, alien.getPosition().getY() + alien.getHeight() / 2, 20, 20);
            projectiles.add(projectile);
            alien.resetActionTimeOut();
        }
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
