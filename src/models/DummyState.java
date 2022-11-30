package models;

import utils.Position;

public class DummyState extends State {
    private Position position;

    public DummyState(int xPosition, int yPosition) {
        this.position = new Position(xPosition, yPosition);
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
