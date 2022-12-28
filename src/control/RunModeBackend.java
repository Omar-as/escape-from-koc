package control;

import models.RunModeState;
import ui.ScreenFactory;
import ui.ScreenManager;
import utils.Constants;

import java.awt.event.KeyEvent;

public class RunModeBackend implements Backend<RunModeState> {
    @Override
    public void updateState(RunModeState state) {
        if (state.isCompleted()) return;
        movePlayer(state);
        if (state.getTimeoutAfter() <= 0 && !state.isCompleted()) {
            state.setCompleted();
            ScreenManager.getInstance().setScreen(ScreenFactory.getGameEndScreen(false));
        }
        state.decTimeoutAfter();
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
                state.incCurrentRoom();
                if (state.getCurrentRoom() == state.getRooms().length) {
                    state.setCompleted();
                    ScreenManager.getInstance().setScreen(ScreenFactory.getGameEndScreen(true));
                } else {
                    state.setKey();
                    state.resetTimeoutAfter();
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
        var playerCenterX = player.getPosition().getX() + player.getWidth() / 2;
        var playerCenterY = player.getPosition().getY() + player.getHeight() / 2;
        var underCenterX = under.getPosition().getX() + under.getWidth() / 2;
        var underCenterY = under.getPosition().getY() + under.getHeight() / 2;
        var distance = Math.sqrt(Math.pow(playerCenterX - underCenterX, 2) + Math.pow(playerCenterY - underCenterY, 2));
        // TODO: Remove magic numbers
        if (clickX >= underX && clickX <= underX + under.getWidth() && clickY >= underY && clickY <= underY + under.getHeight() && distance <= 100) {
            state.getKey().setFound();
            state.setShowKeyFor((int) (Constants.SECOND_MILLS / Constants.REPAINT_DELAY_MILLS));
        }
    }
}
