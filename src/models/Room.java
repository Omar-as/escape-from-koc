package models;

import models.objects.Obj;

import java.util.ArrayList;

public class Room {
    private final String name;
    private final ArrayList<Obj> objects;
    private Key key;

    public Room(String name) {
        this.name = name;
        this.objects = new ArrayList<>();
        this.key = new Key(0, 0);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Obj> getObjects() {
        return objects;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
