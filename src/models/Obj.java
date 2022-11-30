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
        this.position = new Position(xPosition, yPosition);
    }

    public void setXPosition(int xPosition) {
        this.position = new Position(xPosition, this.position.getY());
    }

    public void setYPosition(int yPosition) {
        this.position = new Position(this.position.getX(), yPosition);
    }
}
