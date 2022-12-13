package models;

import utils.Position;

public class Player extends Rectangle {
    private int lives;
    private int score;
    private Room currentRoom;

    public Player(int lives, int score, int xPosition, int yPosition, int width, int height) {
        super(new Position(xPosition, yPosition), width, height);
        this.lives = lives;
        this.score = score;
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

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }
}
