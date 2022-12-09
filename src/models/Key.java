package models;

import utils.Position;

public class Key {
    private final Position position;
    private boolean isFound;

    public Key(int xPosition, int yPosition, boolean isFound){
        this.position = new Position(xPosition, yPosition);
        this.isFound = isFound;
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