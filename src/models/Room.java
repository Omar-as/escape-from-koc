package models;

import models.Key;
import models.Obj;

public class Room {
    private Key key;
    private Obj[] objects;

    private final int topBorder;
    private final int botBorder;
    private final int rightBorder;
    private final int leftBorder;

    public Room(Key key, Obj[] objects, int topBorder, int botBorder, int rightBorder, int leftBorder) {
        this.key = key;
        this.objects = objects;
        this.topBorder = topBorder;
        this.botBorder = botBorder;
        this.rightBorder = rightBorder;
        this.leftBorder = leftBorder;
    }

    public int getTopBorder() {
        return topBorder;
    }

    public int getBotBorder() {
        return botBorder;
    }

    public int getRightBorder() {
        return rightBorder;
    }

    public int getLeftBorder() {
        return leftBorder;
    }

    public Key getKey() {
        return key;
    }

    public Obj[] getObjects() {
        return objects;
    }
}
