package models.powerUps;

import models.Position;
import models.Rectangle;
import utils.Constants;

public class PowerUp extends Rectangle {
    private final PowerUpType type;

    private int despawnTimer = (int) ((6 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    private int effectTimer;
    private int actionTimeOut;
    private int actionTimer;

    public PowerUp(PowerUpType type, int xPosition, int yPosition, int width, int height) {
        super(new Position(xPosition, yPosition), width, height);
        this.type = type;
    }

    public PowerUpType getType() {
        return type;
    }

//    public void setType(PowerUpType type) {
//        this.type = type;
//    }

    public int getDespawnTimer() {
        return despawnTimer;
    }

    public void setDespawnTimer(int despawnTimer) {
        this.despawnTimer = despawnTimer;
    }

    public void decTimer() {
        despawnTimer = Math.max(despawnTimer - 1, 0);
    }

}
