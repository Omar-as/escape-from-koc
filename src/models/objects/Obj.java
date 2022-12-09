package models.objects;

import models.Rectangle;
import utils.Asset;
import utils.Position;

public abstract class Obj extends Rectangle {
    private final Asset asset;

    public Obj(int xPosition, int yPosition, int width, int height, Asset asset) {
        super(new Position(xPosition, yPosition), width, height);
        this.asset = asset;
    }

    public Asset getAsset() {
        return asset;
    }
}
