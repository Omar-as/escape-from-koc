package models;

import models.objects.Obj;
import utils.Constants;

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
        this.door = new Door(width - Constants.DOOR_DIM, height - Constants.DOOR_DIM, Constants.DOOR_DIM, Constants.DOOR_DIM);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        this.door.setXPosition(width - Constants.DOOR_DIM);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        this.door.setYPosition(height - Constants.DOOR_DIM);
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
