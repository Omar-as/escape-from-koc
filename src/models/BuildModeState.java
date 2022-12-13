package models;

import models.objects.Obj;

public class BuildModeState extends State {
    private final Room[] rooms;
    private final Door door;
    private int width;
    private int height;
    private int currentRoom;
    private Obj selectedObject;

    public BuildModeState(Room[] rooms) {
        this.width = 0;
        this.height = 0;
        this.rooms = rooms;
        this.currentRoom = 0;
        this.selectedObject = null;
        // TODO: Remove magic numbers
        this.door = new Door(width - 50, height - 50, 50, 50);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        // TODO: Remove magic number
        this.door.setXPosition(width - 50);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        // TODO: Remove magic number
        this.door.setYPosition(height - 50);
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

    public Door getDoor() {
        return door;
    }
}
