package models;

import utils.Asset;
import utils.Position;

public class Projectile extends Rectangle {
    private final Asset sprite = Asset.PROJECTILE;
    private final int speed  = 3;
    private float[] direction;
    public Projectile(float[] direction, int xPosition, int yPosition, int width, int height){
        super(new Position(xPosition, yPosition), width, height);
        this.direction = direction;
    }
    public Asset getSprite() {
        return sprite;
    }
    public float[] getDirection() {
        return direction;
    }
    public void setDirection(float[] direction) {
        this.direction = direction;
    }
    public void move(){
        super.setPosition((int) (getPosition().getX() + (direction[0] * speed)) , (int) (getPosition().getY() + (direction[1] * speed)));
    }
}
