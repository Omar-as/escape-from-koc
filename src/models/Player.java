package models;

import utils.Asset;
import utils.Position;

public class Player extends Rectangle {
    private int lives;
    private int score;
    private Room currentRoom;
    private Asset currentSprite;
    private int framesPassed = 10;
    private boolean isProtectionVest;
    private boolean isHint;

    public Player(int lives, int score, int xPosition, int yPosition, int width, int height) {
        super(new Position(xPosition, yPosition), width, height);
        this.lives = lives;
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public Asset getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(Asset currentSprite) {
        this.currentSprite = currentSprite;
    }

    public int getFramesPassed() {
        return framesPassed;
    }

    public void setFramesPassed(int framesPassed) {
        this.framesPassed = framesPassed;
    }

    public boolean getIsProtectionVest() { return isProtectionVest; }

    public void setIsProtectionVest(boolean protectionVest) { isProtectionVest = protectionVest; }

    public boolean isProtectionVest() {
        return isProtectionVest;
    }

    public void setProtectionVest(boolean protectionVest) {
        isProtectionVest = protectionVest;
    }

    public boolean getIsHint() {
        return isHint;
    }

    public void setIsHint(boolean hint) {
        isHint = hint;
    }
}
