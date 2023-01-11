package models.alien;

import models.Rectangle;
import utils.Asset;
import utils.Constants;
import utils.Position;

import java.util.Random;

public class Alien extends Rectangle {
    private final AlienType type;

    private final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private int[] currentDirection;
    private int actionTimeOut;
    private int framesPassed = 10;
    private Asset currentSprite;

    public Alien(AlienType type, int xPosition, int yPosition, int width, int height) {
        super(new Position(xPosition, yPosition), width, height);
        this.type = type;
         resetActionTimeOut();
        if (type == AlienType.BLIND) setCurrentDirectionRandomly();
    }

    public AlienType getType() {
        return type;
    }

    public int[] getCurrentDirection(){
        return currentDirection;
    }
    public void setCurrentDirectionRandomly(){
        Random random = new Random();
        currentDirection = directions[random.nextInt(4)];
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