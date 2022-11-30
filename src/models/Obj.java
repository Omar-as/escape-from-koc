package models;

import utils.Position;

public class Obj {
    private String type;
    private Position position;

    public Obj(String type, int xPosition, int yPosition) {
        this.type = type;
        this.position = new Position(xPosition, yPosition);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int xPosition, int yPosition) {
        position = new Position(xPosition, yPosition);
    }

    public void setXPosition(int xPosition) {
        position = new Position(xPosition, position.getY());
    }

    public void setYPosition(int yPosition) {
        position = new Position(position.getX(), yPosition);
    }
}
