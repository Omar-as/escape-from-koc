package models;

import models.Key;
import models.Obj;

public class Room {
    private Key key;
    private Obj[] objects;

    public Room(Key key, Obj[] objects) {
        this.key = key;
        this.objects = objects;
    }

    public Key getKey() {
        return key;
    }

    public Obj[] getObjects() {
        return objects;
    }
}
