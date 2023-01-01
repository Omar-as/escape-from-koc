package ui.frontends;

import models.BuildModeState;
import ui.Frontend;
import ui.GraphicsManager;
import utils.Asset;
import utils.Constants;

import java.awt.*;

public class BuildModeFrontend implements Frontend<BuildModeState> {
    @Override
    public void drawState(BuildModeState state, Graphics canvas) {

        var BackGroundImage = GraphicsManager.getInstance().getImage(Asset.Background, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        canvas.drawImage(BackGroundImage, 0, 0, null);
        // Draw all objects
        var objects = state.getRooms()[state.getCurrentRoom()].getObjects();
        for (var obj : objects) {
            var objectImage = GraphicsManager.getInstance().getImage(obj.getType().asset, obj.getWidth(), obj.getHeight());
            canvas.drawImage(objectImage, obj.getPosition().getX(), obj.getPosition().getY(), null);
        }

        // Draw door
        var door = state.getDoor();
        var doorImage = GraphicsManager.getInstance().getImage(Asset.DOOR_CLOSED, door.getWidth(), door.getHeight());
        canvas.drawImage(doorImage, door.getPosition().getX(), door.getPosition().getY(), null);
    }
}
