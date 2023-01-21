package models.alien;

import utils.Asset;
import utils.Constants;

public class TimeWastingAlien extends Alien {
    private int framesTillAction;
    private ITimeWastingStrategy strategy;
    public TimeWastingAlien(int xPosition, int yPosition, int width, int height) {
        super(AlienType.TIME_WASTING, xPosition, yPosition, width, height);
        setCurrentSprite(Asset.ALIEN_FRIENDLY_TIME_WASTING);
    }

    public void setStrategy(ITimeWastingStrategy strategy) {
        if (this.strategy == null || !strategy.getClass().equals(this.strategy.getClass()))
            resetFramesTillAction(strategy.getFramesTillAction());
        this.strategy = strategy;
        if (strategy instanceof FriendlyStrategy) setCurrentSprite(Asset.ALIEN_FRIENDLY_TIME_WASTING);
        else if (strategy instanceof ConfusedStrategy) setCurrentSprite(Asset.ALIEN_CONFUSED_TIME_WASTING);
        else setCurrentSprite(Asset.ALIEN_AGGRESSIVE_TIME_WASTING);
    }

    public int getFramesTillAction() {
        return framesTillAction;
    }

    public void resetFramesTillAction(int factor) {
        framesTillAction = (int) (Constants.SECOND_MILLS / Constants.REPAINT_DELAY_MILLS) * factor;
    }

    public void decFramesTillAction() {
        framesTillAction = Math.max(framesTillAction - 1, 0);
    }

    public ITimeWastingStrategy getStrategy() {
        return strategy;
    }
}
