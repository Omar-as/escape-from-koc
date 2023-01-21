package models.alien;

public class ConfusedStrategy implements ITimeWastingStrategy {
    @Override
    public int getFramesTillAction() {
        return 2;
    }
}
