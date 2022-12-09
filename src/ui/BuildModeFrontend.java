package ui;

import models.BuildModeState;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BuildModeFrontend implements Frontend<BuildModeState> {
    @Override
    public void drawState(BuildModeState state, Graphics canvas) {

        canvas.setColor(Color.BLACK);
        // canvas.fillRect(0, 0, 2000, 2000);
        // canvas.drawString(String.valueOf(state.getCurrentRoom()), 0, 0);

        for (var obj : state.getRooms()[state.getCurrentRoom()].getObjects()) {
            canvas.drawImage(GraphicsManager.getInstance().getImage(obj.getImageIdx(), obj.getWidth(), obj.getHeight()), obj.getPosition().getX(), obj.getPosition().getY(), null);
        }
    }
}
