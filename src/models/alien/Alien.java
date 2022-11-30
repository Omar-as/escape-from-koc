package models.alien;

import utils.Position;

public class Alien {
    private final AlienType type;
    private Position position;

    public Alien(AlienType type, int xPosition, int yPosition) {
        this.type = type;
        this.position = new Position(xPosition, yPosition);
    }

    public AlienType getType() {
        return type;
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