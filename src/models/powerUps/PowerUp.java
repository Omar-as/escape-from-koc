package models.powerUps;

import models.Rectangle;
import utils.Constants;
import utils.Position;

public class PowerUp extends Rectangle {
    private final PowerUpType type;

    private int timer = 12;
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

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }


    public void resetActionTimeOut(){
        actionTimeOut = (int) ((actionTimer * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    }

}
