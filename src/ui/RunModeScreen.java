package ui;

import control.Backend;
import models.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RunModeScreen extends AnimatedScreen<Game> {

    BufferedImage playerBufferedImage;
    Image playerImage = null;

    public RunModeScreen(Game state, Backend<Game> backend) throws IOException {
        super(state, backend);
        // Load assets
        playerBufferedImage = ImageIO.read(new File("assets/player/player_art.png"));
    }

    @Override
    void drawFrame(Game state, Backend<Game> backend) {

    }

    void drawState(Game state, Graphics canvas) {
        int width  = getWidth();
        int height = getHeight();
        var player = state.getPlayer();

        // Scale images
        playerImage = playerBufferedImage.getScaledInstance(player.getWidth(), player.getHeight(), Image.SCALE_FAST);

        canvas.clearRect(0, 0, width, height); // Clear entire canvas

        canvas.setColor(Color.BLACK);

        canvas.drawImage(playerImage, player.getPosition().getX(), player.getPosition().getY(), null);
    }
}
