package models;

import utils.Position;

public class Key {
    private final Position position;
    private boolean isFound = false;

    public Key(int xPosition, int yPosition){
        this.position = new Position(xPosition, yPosition);
    }

    public boolean isFound() {
        return this.isFound;
    }

    public void setFound() {
        this.isFound = true;
    }

    public Position getPosition() {
        return this.position;
    }
}