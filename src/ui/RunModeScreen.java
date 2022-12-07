package ui;

import control.Controller;
import models.Game;
import models.Player;

import java.awt.*;

public class RunModeScreen extends AnimatedScreen<Game>{
    public RunModeScreen(Game state, Controller<Game> controller) {
        super(state, controller);
    }

    @Override
    void drawState(Game state, Graphics canvas) {
        Player player = state.getPlayer();
        canvas.clearRect(0, 0, 1024, 800);
        canvas.setColor(Color.BLACK);
        canvas.drawRect(0, 0, 100, 100);
        canvas.fillRect(player.getPosition().getX(), player.getPosition().getY(), 10, 10);
    }
}
