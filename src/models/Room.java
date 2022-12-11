package models;

import models.objects.Obj;

import java.util.ArrayList;

public class Room {
    private final String name;
    private final ArrayList<Obj> objects;

    public Room(String name) {
        this.name = name;
        this.objects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Obj> getObjects() {
        return objects;
    }
}
