package models.powerUps;

import utils.Position;

public class PowerUp {
    private String type;
    private Position position;
    private int timer;

    public PowerUp(String type, int xPosition, int yPosition, int timer) {
        this.type = type;
        this.position = new Position(xPosition, yPosition);
        this.timer = timer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
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
