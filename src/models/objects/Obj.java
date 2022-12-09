package models.objects;

import models.Rectangle;
import utils.Position;

public abstract class Obj extends Rectangle {
    private final int imageIdx;

    public Obj(int xPosition, int yPosition, int width, int height, int imageIdx) {
        super(new Position(xPosition, yPosition), width, height);
        this.imageIdx = imageIdx;
    }

    public int getImageIdx() {
        return imageIdx;
    }
}
