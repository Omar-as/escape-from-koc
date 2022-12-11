package models;

import models.alien.Alien;

public class Game extends State {
    private Alien[] aliens;
    private boolean isPaused;
    private Room[] rooms;
    private PowerUp[] powerUps;
    private Player player;
    private Door door;

    public Game(Alien[] aliens, boolean isPaused, GameData data, Room[] rooms, PowerUp[] powerUps, Player player, Door door) {
        this.aliens = aliens;
        this.isPaused = isPaused;
        this.rooms = rooms;
        this.powerUps = powerUps;
        this.player = player;
        this.door = door;
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
