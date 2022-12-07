package models;

import models.alien.Alien;

public class Game extends State {
    private Alien[] aliens;
    private boolean isPaused;
    private GameData data;
    private Room rooms;
    private PowerUp[] powerUps;
    private Player player;

//    public Game(Alien[] aliens, boolean isPaused, GameData data, Room rooms, PowerUp[] powerUps, Player player) {
    public Game(boolean isPaused, Player player) {
        this.isPaused = isPaused;
//        this.data = data;
//        this.rooms = rooms;
//        this.powerUps = powerUps;
        this.player = player;
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

    public GameData getData() {
        return data;
    }

    public void setData(GameData data) {
        this.data = data;
    }

    public Room getRooms() {
        return rooms;
    }

    public void setRooms(Room rooms) {
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
}
