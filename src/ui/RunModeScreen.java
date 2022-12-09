package ui;

import control.Backend;
import models.Game;
import utils.Asset;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RunModeScreen extends AnimatedScreen<Game> {

    Image playerImage = null;
    Image closedDoorImage = null;
    Image openedDoorImage = null;


    public RunModeScreen(Game state, Backend<Game> backend) throws IOException {
        super(state, backend);
    }

    @Override
    void drawFrame(Game state, Backend<Game> backend) {

    }

    void drawState(Game state, Graphics canvas) {
        int width  = getWidth();
        int height = getHeight();
        var player = state.getPlayer();

        canvas.clearRect(0, 0, width, height); // Clear entire canvas

        canvas.setColor(Color.BLACK);


        for (int i = 0; i < state.getAliens().length; i++)
        {
            Image alienImage = GraphicsManager.getInstance().getImage(state.getAliens()[i].getType().asset,
                    state.getAliens()[i].getWidth(), state.getAliens()[i].getHeight());
            canvas.drawImage(alienImage,state.getAliens()[i].getPosition().getX(),
                    state.getAliens()[i].getPosition().getY(),null);
        }

        if(state.getRooms()[0].getKey().isFound()) {
            openedDoorImage = GraphicsManager.getInstance().getImage(Asset.DOOR_OPEN,
                    state.getDoor().getWidth(), state.getDoor().getHeight());
            canvas.drawImage(openedDoorImage,width - state.getDoor().getWidth(),
                    height - state.getDoor().getHeight(), null);
        } else {
            closedDoorImage = GraphicsManager.getInstance().getImage(Asset.DOOR_CLOSED,
                    state.getDoor().getWidth(), state.getDoor().getHeight());
            canvas.drawImage(closedDoorImage,width - state.getDoor().getWidth(),
                    height - state.getDoor().getHeight(), null);
        }

        playerImage = GraphicsManager.getInstance().getImage(Asset.PLAYER, player.getWidth(), player.getHeight());
        canvas.drawImage(playerImage, player.getPosition().getX(), player.getPosition().getY(), null);
    }
}
