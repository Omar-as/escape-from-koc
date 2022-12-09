package models;

import models.objects.Obj;

import java.util.stream.Stream;

public class BuildModeState extends State {
    private int width;
    private int height;
    private final Room[] rooms;
    private int currentRoom;
    private Obj selectedObject;

    public BuildModeState(Stream<String> roomNames) {
        this.width  = 0;
        this.height = 0;
        this.rooms  = roomNames.map(Room::new).toArray(Room[]::new);
        this.currentRoom = 0;
        this.selectedObject = null;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Obj getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Obj selectedObject) {
        this.selectedObject = selectedObject;
    }
}
