package models;

import utils.Position;

public class Key {
    private final Position position;
    private boolean isFound;

    public Key(int xPosition, int yPosition){
        this.position = new Position(xPosition, yPosition);
        this.isFound = false;
    }

    public boolean isFound() {
        return isFound;
    }

    public void setFound() {
        isFound = true;
    }

    public Position getPosition() {
        return position;
    }
}