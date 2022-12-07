package ui;

import control.Controller;
import models.DummyState;
import models.Game;
import utils.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RunModeScreen extends AnimatedScreen<Game>{

    BufferedImage DummyPlayer;

    public RunModeScreen(Game state, Controller<Game> controller) throws IOException {

        super(state, controller);
        DummyPlayer = ImageIO.read(new File("assets/player/player_art.png"));
    }

    @Override
    void drawState(Game state, Graphics canvas) {
        canvas.clearRect(0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        canvas.setColor(Color.BLACK);



        Image DummyPlayer_resized = DummyPlayer.getScaledInstance(100,100,Image.SCALE_DEFAULT);
        canvas.drawImage(DummyPlayer_resized,state.getPlayer().getPosition().getX(),state.getPlayer().getPosition().getY(),null);
    }
}
