package ui.frontends;

import control.KeyManager;
import models.RunModeState;
import models.alien.Alien;
import models.alien.AlienType;
import ui.Frontend;
import ui.GraphicsManager;
import utils.Asset;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RunModeFrontend implements Frontend<RunModeState> {

    @Override
    public void drawState(RunModeState state, Graphics canvas) {
        int width = state.getWidth();
        int height = state.getHeight();

        canvas.clearRect(0, 0, width, height); // Clear entire canvas

        var BackGroundImage = GraphicsManager.getInstance().getImage(Asset.BACKGROUND, height, width);
        canvas.drawImage(BackGroundImage, 0, 0, null);

        canvas.setColor(Color.BLACK);

        // Draw aliens
        for (var alien : state.getAliens()) {
            // draw blind alien
            if (alien.getType() == AlienType.BLIND ) {
                if (alien.getFramesPassed() == 10) {
                    var alienCurrentAsset = alien.getCurrentSprite();

                    if (alien.getCurrentDirection()[0] == 0 && alien.getCurrentDirection()[1] == -1) {
                        alien.setCurrentSprite(alienCurrentAsset == Asset.ALIEN_BLIND_MOVE_UP1 ? Asset.ALIEN_BLIND_MOVE_UP2 : Asset.ALIEN_BLIND_MOVE_UP1);
                    } else if (alien.getCurrentDirection()[0] == 0 && alien.getCurrentDirection()[1] == 1) {
                        alien.setCurrentSprite(alienCurrentAsset == Asset.ALIEN_BLIND_MOVE_DOWN1 ? Asset.ALIEN_BLIND_MOVE_DOWN2 : Asset.ALIEN_BLIND_MOVE_DOWN1);
                    } else if (alien.getCurrentDirection()[0] == -1 && alien.getCurrentDirection()[1] == 0) {
                        alien.setCurrentSprite(alienCurrentAsset == Asset.ALIEN_BLIND_MOVE_LEFT1 ? Asset.ALIEN_BLIND_MOVE_LEFT2 : Asset.ALIEN_BLIND_MOVE_LEFT1);
                    } else if (alien.getCurrentDirection()[0] == 1 && alien.getCurrentDirection()[1] == 0) {
                        alien.setCurrentSprite(alienCurrentAsset == Asset.ALIEN_BLIND_MOVE_RIGHT1 ? Asset.ALIEN_BLIND_MOVE_RIGHT2 : Asset.ALIEN_BLIND_MOVE_RIGHT1);
                    } else {
                        alien.setCurrentSprite(Asset.ALIEN_BLIND_IDLE);
                    }

                    alien.setFramesPassed(0);
                } else {
                    alien.setFramesPassed(alien.getFramesPassed() + 1);
                }
            }
            else alien.setCurrentSprite(alien.getType().asset);

            var alienImage = GraphicsManager.getInstance().getImage(alien.getCurrentSprite(), alien.getWidth(), alien.getHeight());
//            if (alien.getType() == AlienType.SHOOTER)
            canvas.drawImage(alienImage, alien.getPosition().getX(), alien.getPosition().getY(), null);
        }
        for(var projectile : state.getProjectiles()){
            var projectileImage = GraphicsManager.getInstance().getImage(projectile.getSprite(), projectile.getWidth(), projectile.getHeight());

            canvas.drawImage(projectileImage, projectile.getPosition().getX(), projectile.getPosition().getY(), null);
        }

        // Draw door
        var isDoorOpen = state.getKey().isFound() && state.getShowKeyFor() == 0;
        var door = state.getDoor();
        var doorImage = GraphicsManager.getInstance().getImage(isDoorOpen ? Asset.DOOR_OPEN : Asset.DOOR_CLOSED, door.getWidth(), door.getHeight());
        canvas.drawImage(doorImage, door.getPosition().getX(), door.getPosition().getY(), null);

        // Draw all objects
        var objects = state.getRooms()[state.getCurrentRoom()].getObjects();
        var under = state.getKey().getUnder();
        for (var obj : objects) {
            if (obj == under && state.getKey().isFound() && state.getShowKeyFor() > 0) {
                // Remove magic numbers
                var keyImage = GraphicsManager.getInstance().getImage(Asset.KEY, obj.getWidth(), obj.getHeight());
                canvas.drawImage(keyImage, obj.getPosition().getX(), obj.getPosition().getY(), null);
            } else {
                var objectImage = GraphicsManager.getInstance().getImage(obj.getType().asset, obj.getWidth(), obj.getHeight());
                canvas.drawImage(objectImage, obj.getPosition().getX(), obj.getPosition().getY(), null);
            }
        }

        // Draw player
        var player = state.getPlayer();
        if (player.getFramesPassed() == 10) {
            var playerCurrentAsset = player.getCurrentSprite();
            var isUpPressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_UP);
            var isLeftPressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_LEFT);
            var isDownPressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_DOWN);
            var isRightPressed = KeyManager.getInstance().isKeyPressed(KeyEvent.VK_RIGHT);

            if (isUpPressed) {
                player.setCurrentSprite(playerCurrentAsset == Asset.PLAYER_MOVE_UP1 ? Asset.PLAYER_MOVE_UP2 : Asset.PLAYER_MOVE_UP1);
            } else if (isDownPressed) {
                player.setCurrentSprite(playerCurrentAsset == Asset.PLAYER_MOVE_DOWN1 ? Asset.PLAYER_MOVE_DOWN2 : Asset.PLAYER_MOVE_DOWN1);
            } else if (isLeftPressed) {
                player.setCurrentSprite(playerCurrentAsset == Asset.PLAYER_MOVE_LEFT1 ? Asset.PLAYER_MOVE_LEFT2 : Asset.PLAYER_MOVE_LEFT1);
            } else if (isRightPressed) {
                player.setCurrentSprite(playerCurrentAsset == Asset.PLAYER_MOVE_RIGHT1 ? Asset.PLAYER_MOVE_RIGHT2 : Asset.PLAYER_MOVE_RIGHT1);
            } else {
                player.setCurrentSprite(Asset.PLAYER_IDLE);
            }

            player.setFramesPassed(0);
        } else {
            player.setFramesPassed(player.getFramesPassed() + 1);
        }


        var playerImage = GraphicsManager.getInstance().getImage(player.getCurrentSprite(), player.getWidth(), player.getHeight());
        canvas.drawImage(playerImage, player.getPosition().getX(), player.getPosition().getY(), null);
    }
}
