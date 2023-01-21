package models.alien;

import utils.Asset;
import utils.Constants;

public class TimeWastingAlien extends Alien {
    private int framesTillAction;
    private int factor;
    public TimeWastingAlien(int xPosition, int yPosition, int width, int height) {
        super(AlienType.TIME_WASTING, xPosition, yPosition, width, height);
        setCurrentSprite(Asset.ALIEN_FRIENDLY_TIME_WASTING);
    }

    public void setFactor(ITimeWastingStrategy strategy) {
        if (strategy.getFramesTillAction() != factor) {
            this.factor = strategy.getFramesTillAction();
            resetFramesTillAction();
        }
        if (strategy instanceof FriendlyStrategy) setCurrentSprite(Asset.ALIEN_FRIENDLY_TIME_WASTING);
        else if (strategy instanceof ConfusedStrategy) setCurrentSprite(Asset.ALIEN_CONFUSED_TIME_WASTING);
        else setCurrentSprite(Asset.ALIEN_AGGRESSIVE_TIME_WASTING);
    }

    public int getFramesTillAction() {
        return framesTillAction;
    }

    public void resetFramesTillAction() {
        framesTillAction = (int) (Constants.SECOND_MILLS / Constants.REPAINT_DELAY_MILLS) * factor;
    }

    public void decFramesTillAction() {
        framesTillAction = Math.max(framesTillAction - 1, 0);
    }

    public int getFactor() {
        return factor;
    }
}
