package models;

public class Rectangle {
    private Position position;
    private int width;
    private int height;

    public Rectangle(Position position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean intersects(Rectangle other) {
        // MODIFIES : Nothing
        // EFFECTS : Nothing
        // DOES :
        // Returns if rectangles are intersecting from top,
        // bottom, right, left or inside each other
        // Returns false if rectangles are far from each other,
        // not intersecting
        int selfX1 = this.position.getX();
        int selfY1 = this.position.getY();
        int selfX2 = this.position.getX() + this.width;
        int selfY2 = this.position.getY() + this.height;
        int otherX1 = other.position.getX();
        int otherY1 = other.position.getY();
        int otherX2 = other.position.getX() + other.width;
        int otherY2 = other.position.getY() + other.height;

        return Math.min(selfX2, otherX2) > Math.max(selfX1, otherX1) && Math.min(selfY2, otherY2) > Math.max(selfY1, otherY1);
    }

    public int distanceTo(Rectangle other) {
        var thisCenterX = this.getPosition().getX() + this.getWidth() / 2;
        var thisCenterY = this.getPosition().getY() + this.getHeight() / 2;
        var otherCenterX = other.getPosition().getX() + other.getWidth() / 2;
        var otherCenterY = other.getPosition().getY() + other.getHeight() / 2;

        return (int) Math.round(Math.sqrt(Math.pow((thisCenterX - otherCenterX), 2) + Math.pow((thisCenterY - otherCenterY), 2)));
    }
}
