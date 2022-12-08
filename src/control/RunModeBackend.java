package control;

import models.Game;
import models.Player;
import utils.Constants;

public class RunModeBackend implements Backend<Game> {
    @Override
    public void updateState(Game state, int width, int height) {
        Player player = state.getPlayer();

        if (KeyManager.getInstance().isKeyPressed('w')) {
            if(player.getPosition().getY() - 10 > 0) {
                player.setYPosition(player.getPosition().getY() - 10);
            } else {
                player.setYPosition(0);
            }
        }
        if (KeyManager.getInstance().isKeyPressed('a')) {

            if(player.getPosition().getX() - 10 > 0) {
                player.setXPosition(player.getPosition().getX() - 10);
            } else {
                player.setXPosition(0);
            }
        }
        if (KeyManager.getInstance().isKeyPressed('s')) {
            if (player.getPosition().getY() + 10 < Constants.FRAME_HEIGHT){
                player.setYPosition(player.getPosition().getY() + 10);
            } else {
                player.setYPosition(Constants.FRAME_HEIGHT - 10);
            }
        }
        if (KeyManager.getInstance().isKeyPressed('d')){
            if(player.getPosition().getX() + 10 < Constants.FRAME_WIDTH) {
                player.setXPosition(player.getPosition().getX() + 10);
            } else {
                player.setXPosition(Constants.FRAME_WIDTH - 10);
            }
        }
    }
}
