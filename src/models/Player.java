package models;

import utils.Position;

public class Player {
    private int lives;
    private int score;
    private Room currentRoom;
    private Position position;

    public Player(int lives, int score, int xPosition, int yPosition) {
        this.lives = lives;
        this.score = score;
        this.position = new Position(xPosition, yPosition);
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

    public Room getCurrntRoom(){
        return currentRoom;
    }

    public void setCurrntRoom(Room room){
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
}
