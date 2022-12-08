package ui;

import control.Backend;
import models.Game;
import models.alien.Alien;
import models.alien.AlienType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RunModeScreen extends AnimatedScreen<Game> {

    BufferedImage playerBufferedImage;
    Image playerImage = null;

    BufferedImage closedDoorBufferedImage;
    Image closedDoorImage = null;

    BufferedImage openedDoorBufferedImage;
    Image openedDoorImage = null;

    BufferedImage blindAlienBufferedImage;
    Image blindAlienImage = null;

    BufferedImage shooterAlienBufferedImage;
    Image shooterAlienImage = null;

    BufferedImage timeAlienBufferedImage;
    Image timeAlienImage = null;


    public RunModeScreen(Game state, Backend<Game> backend) throws IOException {
        super(state, backend);
        // Load assets
        playerBufferedImage = ImageIO.read(new File("assets/player/player_art.png"));
        closedDoorBufferedImage = ImageIO.read(new File("assets/door/closed_door.png"));
        openedDoorBufferedImage = ImageIO.read(new File("assets/door/opened_door.png"));
        blindAlienBufferedImage = ImageIO.read(new File("assets/aliens/blind_alien.png"));
        shooterAlienBufferedImage = ImageIO.read(new File("assets/aliens/shooter_alien.png"));
        timeAlienBufferedImage = ImageIO.read(new File("assets/aliens/time_alien.png"));
    }

    @Override
    void drawState(Game state, Graphics canvas) {
        int width  = getWidth();
        int height = getHeight();
        var player = state.getPlayer();

        canvas.clearRect(0, 0, width, height); // Clear entire canvas

        canvas.setColor(Color.BLACK);

        // Scale images
        playerImage = playerBufferedImage.getScaledInstance(player.getWidth(), player.getHeight(), Image.SCALE_FAST);
//        closedDoorImage = closedDoorBufferedImage.getScaledInstance(
//                state.getRooms()[0].getDoor().getWidth(),
//                state.getRooms()[0].getDoor().getHeight(),Image.SCALE_DEFAULT);
//        openedDoorImage = openedDoorBufferedImage.getScaledInstance(
//                state.getRooms()[0].getDoor().getWidth(),
//                state.getRooms()[0].getDoor().getHeight(),Image.SCALE_DEFAULT);

        for (int i = 0; i < state.getAliens().length; i++){
            if(state.getAliens()[i].getType() == AlienType.BLIND){

            }
        }

        if(state.getRooms()[0].getKey().isFound())
        {
            openedDoorImage = openedDoorBufferedImage.getScaledInstance(
                    state.getRooms()[0].getDoor().getWidth(),
                    state.getRooms()[0].getDoor().getHeight(),Image.SCALE_DEFAULT);
            canvas.drawImage(openedDoorImage,
                    width - state.getRooms()[0].getDoor().getWidth(),
                    height - state.getRooms()[0].getDoor().getHeight(),
                    null);
        }else
        {
            closedDoorImage = closedDoorBufferedImage.getScaledInstance(
                    state.getRooms()[0].getDoor().getWidth(),
                    state.getRooms()[0].getDoor().getHeight(),Image.SCALE_DEFAULT);
            canvas.drawImage(closedDoorImage,
                    width - state.getRooms()[0].getDoor().getWidth(),
                    height - state.getRooms()[0].getDoor().getHeight(),
                    null);
        }

        canvas.drawImage(playerImage, player.getPosition().getX(), player.getPosition().getY(), null);
    }
}
