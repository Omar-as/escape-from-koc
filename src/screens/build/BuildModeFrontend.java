package screens.build;

import managers.GraphicsManager;
import models.BuildModeState;
import screens.Frontend;
import utils.Asset;
import utils.Constants;

import java.awt.*;

public class BuildModeFrontend implements Frontend<BuildModeState> {
    @Override
    public void drawState(BuildModeState state, Graphics canvas) {

        // Draw background
        var BackGroundImage = GraphicsManager.getInstance().getImage(Asset.BACKGROUND, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
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
