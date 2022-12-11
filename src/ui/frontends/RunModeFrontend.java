package ui.frontends;

import models.RunModeState;
import ui.Frontend;
import ui.GraphicsManager;
import utils.Asset;

import java.awt.*;

public class RunModeFrontend implements Frontend<RunModeState> {
    @Override
    public void drawState(RunModeState state, Graphics canvas) {
        int width  = state.getWidth();
        int height = state.getHeight();

        canvas.clearRect(0, 0, width, height); // Clear entire canvas

        canvas.setColor(Color.BLACK);

        // Draw aliens
        for (var alien : state.getAliens()) {
            var alienImage = GraphicsManager.getInstance().getImage(alien.getType().asset, alien.getWidth(), alien.getHeight());
            canvas.drawImage(alienImage, alien.getPosition().getX(), alien.getPosition().getY(), null);
        }

        // Draw door
        var isKeyFound = state.getRooms()[state.getCurrentRoom()].getKey().isFound();
        var door = state.getDoor();
        var doorImage = GraphicsManager.getInstance().getImage(isKeyFound ? Asset.DOOR_OPEN : Asset.DOOR_CLOSED, door.getWidth(), door.getHeight());
        canvas.drawImage(doorImage,door.getPosition().getX(), door.getPosition().getY(), null);

        // Draw all objects
        var objects = state.getRooms()[state.getCurrentRoom()].getObjects();
        for (var obj : objects) {
            var objectImage = GraphicsManager.getInstance().getImage(obj.getType().asset, obj.getWidth(), obj.getHeight());
            canvas.drawImage(objectImage, obj.getPosition().getX(), obj.getPosition().getY(), null);
        }

        // Draw player
        var player = state.getPlayer();
        var playerImage = GraphicsManager.getInstance().getImage(Asset.PLAYER, player.getWidth(), player.getHeight());
        canvas.drawImage(playerImage, player.getPosition().getX(), player.getPosition().getY(), null);
    }
}
