package models;

import models.objects.Obj;

import java.util.ArrayList;

public class Room {
    private Key key;
    private Door door;
    private final String name;
    private final ArrayList<Obj> objects;

    public Room(String name, Door door) {
        this.name = name;
        this.objects = new ArrayList<>();
        this.door = door;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Obj> getObjects() {
        return objects;
    }

    public Door getDoor() { return door; }
}
