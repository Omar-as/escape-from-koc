package models.alien;

import models.Position;
import models.Rectangle;
import utils.Asset;

public class Alien extends Rectangle {
    private Asset currentSprite;
    private final AlienType type;

    public Alien(AlienType type, int xPosition, int yPosition, int width, int height) {
        super(new Position(xPosition, yPosition), width, height);
        this.type = type;
    }

    public Asset getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(Asset currentSprite) {
        this.currentSprite = currentSprite;
    }

    public AlienType getType() {
        return type;
    }
}