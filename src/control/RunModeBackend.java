package control;

import models.RunModeState;
import utils.Constants;

import java.awt.event.KeyEvent;

public class RunModeBackend implements Backend<RunModeState> {
    @Override
    public void updateState(RunModeState state) {
        movePlayer(state);
    }

    void movePlayer(RunModeState state) {
        // Check whether keys are pressed or not
        var isUpPressed    = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_UP);
        var isLeftPressed  = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_LEFT);
        var isDownPressed  = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_DOWN);
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
        if (isDownPressed) player.setYPosition(Math.min(player.getPosition().getY() + moveBy, state.getHeight() - player.getHeight()));
        if (isRightPressed) player.setXPosition(Math.min(player.getPosition().getX() + moveBy, state.getWidth()  - player.getWidth()));

        // Collision prevention
        var objects = state.getRooms()[state.getCurrentRoom()].getObjects();
        for (var obj : objects) if (player.intersects(obj)) {
            player.setPosition(backupPosition);
        }
        if (player.intersects(state.getDoor())) player.setPosition(backupPosition);
    }
}
