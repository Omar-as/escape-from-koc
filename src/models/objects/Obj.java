package models.objects;

import models.Rectangle;
import models.Position;

public class Obj extends Rectangle {
    private final ObjectType type;

    public Obj(int xPosition, int yPosition, int width, int height, ObjectType type) {
        super(new Position(xPosition, yPosition), width, height);
        this.type = type;
    }

    public ObjectType getType() {
        return type;
    }
}
