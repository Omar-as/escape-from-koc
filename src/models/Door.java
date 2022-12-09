package models;

import utils.Position;

public class Door {
    private final int width;
    private final int height;
    private final Position position;
    private final String closedDoorName;
    private final String openedDoorName;

    public Door(String closedDoorName, String openedDoorName, int width, int height, int xPosition, int yPosition) {
        this.closedDoorName = closedDoorName;
        this.openedDoorName = openedDoorName;
        this.width = width;
        this.height = height;
        this.position = new Position(xPosition, yPosition);
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public Position getPosition() { return position; }

    public String getClosedDoorName() { return closedDoorName; }

    public String getOpenedDoorName() { return openedDoorName; }
}
