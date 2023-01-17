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
    private int actionTimer;
    private int actionTimeOut;
    private int framesPassed = 10;
    private Asset currentSprite;
    private int timeLeftWhenSpawned;
    private int timePercentLeftWhenSpawned;
    public enum timeWastingMode {
        NORMAL,
        CONFUSED,
        PETTY;
    };
    private timeWastingMode mode;

    public Alien(AlienType type, int xPosition, int yPosition, int width, int height) {
        super(new Position(xPosition, yPosition), width, height);
        this.type = type;
        resetActionTimeOut();
        switch (type) {
            case BLIND:
                actionTimer = 2;
                setCurrentDirectionRandomly();
                break;
            case TIME_WASTING:
                actionTimer = 3;
                break;
            case SHOOTER:
                actionTimer = 1;
                break;
        }
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
        actionTimeOut = (int) ((actionTimer * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
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

    public int getTimeLeftWhenSpawned() {
        return timeLeftWhenSpawned;
    }

    public void setTimeLeftWhenSpawned(int timeLeftWhenSpawned) {
        this.timeLeftWhenSpawned = timeLeftWhenSpawned;
    }

    public int getTimePercentLeftWhenSpawned() {
        return timePercentLeftWhenSpawned;
    }

    public void setTimePercentLeftWhenSpawned(int timePercentLeftWhenSpawned) {
        this.timePercentLeftWhenSpawned = timePercentLeftWhenSpawned;
    }

    public timeWastingMode getMode() {
        return mode;
    }
    public void setMode(){
        if (timePercentLeftWhenSpawned >= 70) this.mode = timeWastingMode.NORMAL;
        else if (30 <= timePercentLeftWhenSpawned && timePercentLeftWhenSpawned < 70) this.mode = timeWastingMode.CONFUSED;
        else if (timePercentLeftWhenSpawned < 30) this.mode = timeWastingMode.PETTY;
    }
    public float[] aim(Rectangle target){
        var thisCenterX = this.getPosition().getX() + this.getWidth()/2;
        var thisCenterY = this.getPosition().getY() + this.getHeight()/2;
        var targetCenterX = target.getPosition().getX() + target.getWidth()/2;
        var targetCenterY = target.getPosition().getY() + target.getHeight()/2;

        int xDir = targetCenterX - thisCenterX ;
        int yDir = targetCenterY - thisCenterY ;

        float distance = (float) Math.sqrt(Math.pow(xDir, 2) + Math.pow(yDir, 2));

        float unitX = (float) xDir/(float) distance;
        float unitY =(float) yDir/(float)distance;

        return (new float[]{unitX,unitY});
    }
}