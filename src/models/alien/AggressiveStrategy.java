package models.alien;

public class AggressiveStrategy implements ITimeWastingStrategy {
    @Override
    public int getFramesTillAction() {
        return 3;
    }
}
