package models;

import utils.Position;

public class Door {
    private final int width;

    private final int height;

    private final Position position;

    public Door(int width, int height, int xPosition, int yPosition) {
        this.width = width;
        this.height = height;
        this.position = new Position(xPosition, yPosition);
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public Position getPosition() { return position; }
}
