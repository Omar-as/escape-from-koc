package models;

import utils.Asset;

public class Player extends Rectangle {
    private int lives;
    private Room currentRoom;
    private Asset currentSprite;
    private int framesPassed = 10;
    private boolean isProtectionVest;
    private boolean isHint;
    private int bagHints;
    private int bagProtectionVests;
    private int bagPlasticBottles;

    public Player(int lives, int xPosition, int yPosition, int width, int height) {
        super(new Position(xPosition, yPosition), width, height);
        this.lives = lives;
        this.bagHints = 0;
        this.bagProtectionVests = 0;
        this.bagPlasticBottles = 0;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
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

    public boolean getIsProtectionVest() {
        return isProtectionVest;
    }

    public void setIsProtectionVest(boolean protectionVest) {
        isProtectionVest = protectionVest;
    }

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

    public int getBagHints() {
        return bagHints;
    }

    public void incBagHints() {
        this.bagHints++;
    }

    public void decBagHints() {
        if (this.bagHints <= 0) throw new IllegalStateException();
        this.bagHints--;
    }

    public int getBagProtectionVests() {
        return bagProtectionVests;
    }

    public void incBagProtectionVests() {
        this.bagProtectionVests++;
    }

    public void decBagProtectionVests() {
        if (this.bagProtectionVests <= 0) throw new IllegalStateException();
        this.bagProtectionVests--;
    }

    public int getBagPlasticBottles() {
        return bagPlasticBottles;
    }

    public void incBagPlasticBottles() {
        this.bagPlasticBottles++;
    }

    public void decBagPlasticBottles() {
        if (this.bagPlasticBottles <= 0) throw new IllegalStateException();
        this.bagPlasticBottles--;
    }

    public String getBagAsString() {
        return "H: %d, V: %d, B: %d".formatted(bagHints, bagProtectionVests, bagPlasticBottles);
    }
}
