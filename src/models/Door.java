package models;

public class Door extends Rectangle {
    public Door(int positionX, int positionY, int width, int height) {
        super(new Position(positionX, positionY), width, height);
    }
}
