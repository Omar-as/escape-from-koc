package ui;

import control.Backend;
import models.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RunModeScreen extends AnimatedScreen<Game> {

    Image playerImage = null;
    Image closedDoorImage = null;
    Image openedDoorImage = null;
    Image blindAlienImage = null;
    Image shooterAlienImage = null;
    Image timeAlienImage = null;


    public RunModeScreen(Game state, Backend<Game> backend) throws IOException {
        super(state, backend);
    }

    @Override
    void drawState(Game state, Graphics canvas) {
        int width  = getWidth();
        int height = getHeight();
        var player = state.getPlayer();

        canvas.clearRect(0, 0, width, height); // Clear entire canvas

        canvas.setColor(Color.BLACK);


//        for (int i = 0; i < state.getAliens().length; i++)
//        {
//            if(state.getAliens()[i].getType() == AlienType.BLIND){
//
//            }
//        }

        if(state.getRooms()[0].getKey().isFound())
        {
            openedDoorImage = GraphicsManager.buffImages(state.getRooms()[0].getDoor().getOpenedDoorName(),
                    state.getRooms()[0].getDoor().getWidth(), state.getRooms()[0].getDoor().getHeight());
            canvas.drawImage(openedDoorImage,width - state.getRooms()[0].getDoor().getWidth(),
                    height - state.getRooms()[0].getDoor().getHeight(),null);
        } else
        {
            closedDoorImage = GraphicsManager.buffImages(state.getRooms()[0].getDoor().getClosedDoorName(),
                    state.getRooms()[0].getDoor().getWidth(), state.getRooms()[0].getDoor().getHeight());
            canvas.drawImage(closedDoorImage,width - state.getRooms()[0].getDoor().getWidth(),
                    height - state.getRooms()[0].getDoor().getHeight(),null);
        }

        playerImage = GraphicsManager.buffImages(player.getImageName(), player.getWidth(), player.getHeight());
        canvas.drawImage(playerImage, player.getPosition().getX(), player.getPosition().getY(), null);
    }
}
