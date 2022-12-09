package models.alien;

import utils.Position;

public class Alien {
    private final AlienType type;
    private Position position;
    private final int width;
    private final int height;

    public Alien(AlienType type, int xPosition, int yPosition,int width, int height) {
        this.type = type;
        this.position = new Position(xPosition, yPosition);
        this.width = width;
        this.height = height;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}