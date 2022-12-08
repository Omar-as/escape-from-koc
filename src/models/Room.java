package models;

import models.Key;
import models.Obj;

public class Room {
    private Key key;
    private Obj[] objects;
    private Door door;

    public Room(Key key, Obj[] objects,Door door) {
        this.key = key;
        this.objects = objects;
        this.door = door;
    }

    public Key getKey() {
        return key;
    }

    public Obj[] getObjects() {
        return objects;
    }

    public Door getDoor() { return door; }
}
