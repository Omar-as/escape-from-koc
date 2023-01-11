package control;

import models.Key;
import models.Rectangle;
import models.Room;
import models.RunModeState;
import models.alien.Alien;
import models.alien.AlienType;
import models.objects.Obj;
import ui.ScreenFactory;
import ui.ScreenManager;
import utils.Constants;
import utils.Position;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class RunModeBackend implements Backend<RunModeState> {
    @Override
    public void updateState(RunModeState state) {
        if (state.isCompleted()) return;
        movePlayer(state);

        for (var alien : state.getAliens()) {
            if (alien.getType() == AlienType.BLIND) moveBlindAlien(alien, state);
            else if (alien.getType() == AlienType.TIME_WASTING) changeKeyPosition(alien, state);
            else if (alien.getType() == AlienType.SHOOTER) fireProjectile(alien);
        }
        if (state.getTimeoutAfter() <= 0 && !state.isCompleted()) {
            state.setCompleted();
            ScreenManager.getInstance().setScreen(ScreenFactory.getGameEndScreen(false));
        }
        state.decTimeoutAfter();
        if (state.getTimeForNextAlien() <= 0){
            spawnAlien(new Random(), state);
            state.resetTimeForNextAlien();
        }

        state.decTimeForNextAlien();
            
    }

    private void movePlayer(RunModeState state) {
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
                state.incCurrentRoom();
                if (state.getCurrentRoom() == state.getRooms().length) {
                    state.setCompleted();
                    ScreenManager.getInstance().setScreen(ScreenFactory.getGameEndScreen(true));
                } else {
                    state.setKey();
                    state.resetTimeoutAfter();
                    state.setAliens(new ArrayList<Alien>());
                }
            } else player.setPosition(backupPosition);
        }

        state.decShowKeyFor();
    }

    public void pickupKey(RunModeState state, int clickX, int clickY) {
        if (state.getKey().isFound()) return;
        var under = state.getKey().getUnder();
        var underX = under.getPosition().getX();
        var underY = under.getPosition().getY();
        var player = state.getPlayer();
        var distance = player.distanceBetweenObjects(under);
        // TODO: Remove magic numbers
        if (clickX >= underX && clickX <= underX + under.getWidth() && clickY >= underY && clickY <= underY + under.getHeight() && distance <= 100) {
            state.getKey().setFound();
            state.setShowKeyFor((int) (Constants.SECOND_MILLS / Constants.REPAINT_DELAY_MILLS));
        }
    }
    private void spawnAlien(Random random, RunModeState state) {
        Room room = state.getRooms()[state.getCurrentRoom()];
        // TODO: Remove magic numbers
        Alien alien = new Alien(AlienType.values()[random.nextInt(2)], 0, 0 ,64, 64);
        var minDistance = 100;

        var objects = room.getObjects();
        var done = false;

        while (!done) {
            int x = random.nextInt(state.getWidth() - alien.getWidth());
            int y = random.nextInt(state.getHeight() - alien.getHeight());

            alien.setPosition(x, y);

            int tooClose = objects.stream()
                    .map(alien::distanceBetweenObjects)
                    .mapToInt(b -> (b < minDistance) ? 1 : 0)
                    .sum();

            done = tooClose == 0;
        }
        state.getAliens().add(alien);
    }
    private void moveBlindAlien(Alien alien, RunModeState state) {
        var random = new Random();
        var done = false;
        var xPosition = alien.getPosition().getX();
        var yPosition = alien.getPosition().getY();
        var objs = state.getRooms()[state.getCurrentRoom()].getObjects();
        var door = state.getDoor();
        var player = state.getPlayer();
        var intersects = 0;
        while (!done && !alien.intersects(player)) {
            alien.decActionTimeOut();
            if (alien.getActionTimeOut() <= 0 || intersects != 0) {
                int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
                alien.setCurrentDirection(directions[random.nextInt(4)]);
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
//                    + (dummyRect.intersects(player) ? 1 : 0)
                    + ((xPosition < 0 || yPosition < 0 || yPosition > (state.getHeight() - alien.getHeight()) || xPosition > (state.getWidth() - alien.getWidth())) ? 1 : 0);
            done = intersects == 0;
            System.out.println(intersects);
        }
        alien.setPosition(xPosition, yPosition);
    }

    private void changeKeyPosition(Alien alien, RunModeState state) {
        if (state.getKey().isFound()) return;

        alien.decActionTimeOut();
        if (alien.getActionTimeOut() <= 0) {

//            System.out.println(state.getKey().getUnder());

            var random = new Random();
            var room = state.getRooms()[state.getCurrentRoom()];
            var objects = room.getObjects();
            Obj randObj;
            do {
                randObj = objects.get(random.nextInt(objects.size()));
            } while (randObj == state.getKey().getUnder());

            state.setKey(new Key(randObj));

//            System.out.println(state.getKey().getUnder());

            alien.resetActionTimeOut();
        }
    }
    private void fireProjectile (Alien alien) {

    }
}
