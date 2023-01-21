package models.alien;

import utils.Asset;
import utils.Constants;

public class BlindAlien extends Alien {
    private int framesTillSwitch;
    private int directionX;
    private int directionY;
    private int sprite;
    private Asset[] spriteVector;

    public BlindAlien(int xPosition, int yPosition, int width, int height) {
        super(AlienType.BLIND, xPosition, yPosition, width, height);
        setCurrentSprite(Asset.ALIEN_BLIND_IDLE);
        resetFramesTillSwitch();
        this.sprite = 0;
        this.spriteVector = new Asset[]{Asset.ALIEN_BLIND_MOVE_UP1, Asset.ALIEN_BLIND_MOVE_UP2};
    }

    public void setDirection(int x, int y) {
        directionX = x;
        directionY = y;

        if (x == 0 && y == 1) { // Down
            this.spriteVector = new Asset[]{Asset.ALIEN_BLIND_MOVE_DOWN1, Asset.ALIEN_BLIND_MOVE_DOWN2};
        } else if (x == 0 && y == -1) { // Up
            this.spriteVector = new Asset[]{Asset.ALIEN_BLIND_MOVE_UP1, Asset.ALIEN_BLIND_MOVE_UP2};
        } else if (x == 1 && y == 0) { // Right
            this.spriteVector = new Asset[]{Asset.ALIEN_BLIND_MOVE_RIGHT1, Asset.ALIEN_BLIND_MOVE_RIGHT2};
        } else { // Left
            this.spriteVector = new Asset[]{Asset.ALIEN_BLIND_MOVE_LEFT1, Asset.ALIEN_BLIND_MOVE_LEFT2};
        }

        setCurrentSprite(spriteVector[sprite]);
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public int getFramesTillSwitch() {
        return framesTillSwitch;
    }

    public void resetFramesTillSwitch() {
        framesTillSwitch = (int) (Constants.SECOND_MILLS / Constants.REPAINT_DELAY_MILLS);
    }

    public void decFramesTillSwitch() {
        framesTillSwitch = Math.max(framesTillSwitch - 1, 0);
    }

    public int getSprite() {
        return sprite;
    }

    public void alternateSprite() {
        sprite = sprite == 0 ? 1 : 0;
        setCurrentSprite(spriteVector[sprite]);
    }
}
