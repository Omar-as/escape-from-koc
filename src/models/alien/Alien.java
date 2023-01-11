package models.alien;

import models.Rectangle;
import utils.Asset;
import utils.Constants;
import utils.Position;

public class Alien extends Rectangle {
    private final AlienType type;
    private int[] currentDirection;
    private int actionTimeOut;
    private int framesPassed = 10;
    private Asset currentSprite;

    public Alien(AlienType type, int xPosition, int yPosition, int width, int height) {
        super(new Position(xPosition, yPosition), width, height);
        this.type = type;
        if (type != AlienType.BLIND) resetActionTimeOut();
    }

    public AlienType getType() {
        return type;
    }

    public int[] getCurrentDirection(){
        return currentDirection;
    }
    public void setCurrentDirection(int[] direction){
        currentDirection = direction;
    }

    public int getActionTimeOut() {
        return actionTimeOut;
    }

    public void decActionTimeOut() {
        actionTimeOut = Math.max(actionTimeOut - 1, 0);
    }
    public void resetActionTimeOut(){
        actionTimeOut = (int) ((5 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    }
    public int getFramesPassed(){
        return framesPassed;
    }
    public void setFramesPassed(int framesPassed) {
        this.framesPassed = framesPassed;
    }
    public Asset getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(Asset currentSprite) {
        this.currentSprite = currentSprite;
    }
}