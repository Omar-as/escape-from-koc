package models.alien;

public class FriendlyStrategy implements ITimeWastingStrategy {
    @Override
    public int getFramesTillAction() {
        return 0;
    }
}
