package control;

import models.Game;
import models.Player;

import java.awt.event.KeyAdapter;

public class GameController implements Controller<Game> {

    IsKeyPressed CheckKeys = new IsKeyPressed();

    @Override
    public void updateState(Game state) {
        Player player = state.getPlayer();
        if (CheckKeys.isWPressed()) {
            player.setXPosition(player.getPosition().getX());
            player.setYPosition(player.getPosition().getY() - 10);
        }
        else if (CheckKeys.isAPressed()) {
            player.setXPosition(player.getPosition().getX() - 10);
            player.setYPosition(player.getPosition().getY());
        }
        else if (CheckKeys.isSPressed()){
            player.setXPosition(player.getPosition().getX());
            player.setYPosition(player.getPosition().getY() + 10);
        }
        else if (CheckKeys.isDPressed()){
            player.setXPosition(player.getPosition().getX() + 10);
            player.setYPosition(player.getPosition().getY());
        }
    }
}
