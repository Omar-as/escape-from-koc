package control;

import models.Game;
import models.Player;

import java.awt.event.KeyAdapter;

public class GameController implements Controller<Game> {

    IsKeyPressed CheckKeys = new IsKeyPressed();

    @Override
    public void updateState(Game state) {
        CheckKeys.isWPressed();
        Player player = state.getPlayer();
        player.setXPosition(player.getPosition().getX() + 10);
        player.setYPosition(player.getPosition().getY() + 10);
    }
}
