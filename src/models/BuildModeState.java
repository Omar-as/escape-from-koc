package models;

import java.util.stream.Stream;

public class BuildModeState extends State {
    private final Room[] rooms;
    private int currentRoom;

    public BuildModeState(Stream<String> roomNames) {
        this.rooms = roomNames.map(Room::new).toArray(Room[]::new);
        this.currentRoom = 0;
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
}
