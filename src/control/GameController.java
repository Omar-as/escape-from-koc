package control;

import models.Game;
import models.Player;
import utils.Constants;

import java.awt.event.KeyAdapter;

public class GameController implements Controller<Game> {

    IsKeyPressed CheckKeys = new IsKeyPressed();

    @Override
    public void updateState(Game state) {
        Player player = state.getPlayer();

        if (CheckKeys.isWPressed()) {
            if(player.getPosition().getY() - 10 > 0) {
                player.setYPosition(player.getPosition().getY() - 10);
            } else {
                player.setYPosition(0);
            }
        }
        else if (CheckKeys.isAPressed()) {

            if(player.getPosition().getX() - 10 > 0) {
                player.setXPosition(player.getPosition().getX() - 10);
            } else {
                player.setXPosition(0);
            }
        }
        else if (CheckKeys.isSPressed()) {
            if (player.getPosition().getY() + 10 < Constants.FRAME_HEIGHT){
                player.setYPosition(player.getPosition().getY() + 10);
            } else {
                player.setYPosition(Constants.FRAME_HEIGHT - 10);
            }
        }
        else if (CheckKeys.isDPressed()){
            if(player.getPosition().getX() + 10 < Constants.FRAME_WIDTH) {
                player.setXPosition(player.getPosition().getX() + 10);
            } else {
                player.setXPosition(Constants.FRAME_WIDTH - 10);
            }
        }
    }
}
