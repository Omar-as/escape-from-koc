package models;

import models.objects.Obj;

import java.util.ArrayList;

public class Room {
    private final String name;
    private final int minObjects;
    private final ArrayList<Obj> objects;

    public Room(String name, int minObjects) {
        this.name = name;
        this.minObjects = minObjects;
        this.objects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMinObjects() {
        return minObjects;
    }

    public ArrayList<Obj> getObjects() {
        return objects;
    }
}
