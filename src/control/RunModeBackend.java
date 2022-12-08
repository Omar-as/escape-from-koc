package control;

import models.Game;
import utils.Constants;

public class RunModeBackend implements Backend<Game> {
    @Override
    public void updateState(Game state, int width, int height) {
        movePlayer(state, width, height);
    }

    void movePlayer(Game state, int width, int height) {
        var player = state.getPlayer();

        // Check whether keys are pressed or not
        var isWPressed = KeyManager.getInstance().isKeyPressed('w');
        var isAPressed = KeyManager.getInstance().isKeyPressed('a');
        var isSPressed = KeyManager.getInstance().isKeyPressed('s');
        var isDPressed = KeyManager.getInstance().isKeyPressed('d');

        // Horizontal Displacement
        // moveBy = numSteps * speed = 1 * speed = speed
        var moveBy = Constants.PLAYER_SPEED;

        // If we are moving diagonally, keep speed consistent:
        // moveByDiagonal = sqrt(2   * (moveByXY       ^ 2))
        // moveByXY       = sqrt(1/2 * (moveByDiagonal ^ 2))
        var movingDiagonally = (isWPressed || isSPressed) && (isAPressed || isDPressed);
        if (movingDiagonally) moveBy = (int) Math.sqrt(Math.pow(moveBy, 2) / 2);

        // Move player
        if (isWPressed) player.setYPosition(Math.max(player.getPosition().getY() - moveBy, 0));
        if (isAPressed) player.setXPosition(Math.max(player.getPosition().getX() - moveBy, 0));
        if (isSPressed) player.setYPosition(Math.min(player.getPosition().getY() + moveBy, height - player.getHeight()));
        if (isDPressed) player.setXPosition(Math.min(player.getPosition().getX() + moveBy, width  - player.getWidth()));
    }
}
