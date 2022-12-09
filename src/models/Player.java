package models;

import utils.Position;

public class Player {
    private int lives;
    private int score;
    private Room currentRoom;
    private Position position;
    private final int width;
    private final int height;
    private final String imageName;

    public Player(int lives, int score, int xPosition, int yPosition, int width, int height, String imageName) {
        this.lives = lives;
        this.score = score;
        this.position = new Position(xPosition, yPosition);
        this.width = width;
        this.height = height;
        this.imageName = imageName;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }

    public void setCurrentRoom(Room room){
        currentRoom = room;
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

    public String getImageName() { return imageName; }
}
