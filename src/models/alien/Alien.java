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
        return this.type;
    }

    public Position getPosition() {
        return this.position;
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