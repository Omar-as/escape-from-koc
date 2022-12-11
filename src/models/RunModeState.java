package models;

import models.alien.Alien;

public class RunModeState extends State {
    private int width;
    private int height;
    private Alien[] aliens;
    private boolean isPaused;
    private Room[] rooms;
    private int currentRoom;
    private PowerUp[] powerUps;
    private Player player;
    private Door door;

    public RunModeState(Alien[] aliens, boolean isPaused, Room[] rooms, PowerUp[] powerUps, Player player, Door door) {
        this.width  = 0;
        this.height = 0;
        this.aliens = aliens;
        this.isPaused = isPaused;
        this.rooms = rooms;
        this.currentRoom = 0;
        this.powerUps = powerUps;
        this.player = player;
        this.door = door;
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

    public Alien[] getAliens() {
        return aliens;
    }

    public void setAliens(Alien[] aliens) {
        this.aliens = aliens;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPause(boolean isPaused) {
        this.isPaused = isPaused;
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

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public PowerUp[] getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(PowerUp[] powerUps) {
        this.powerUps = powerUps;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}
