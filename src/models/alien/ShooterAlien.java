package models.alien;

import models.Rectangle;
import utils.Asset;
import utils.Constants;

public class ShooterAlien extends Alien {
    private int framesTillShoot;
    public ShooterAlien(int xPosition, int yPosition, int width, int height) {
        super(AlienType.SHOOTER, xPosition, yPosition, width, height);
        setCurrentSprite(Asset.ALIEN_SHOOTER);
        resetFramesTillShoot();
    }

    public int getFramesTillShoot() {
        return framesTillShoot;
    }

    public void resetFramesTillShoot() {
        framesTillShoot = (int) (Constants.SECOND_MILLS / Constants.REPAINT_DELAY_MILLS);
    }

    public void decFramesTillShoot() {
        framesTillShoot = Math.max(framesTillShoot - 1, 0);
    }
}
