package models;

import models.alien.Alien;
import utils.Constants;

import java.util.Random;

public class RunModeState extends State {
    // OVERVIEW: Holds the state while in Run Mode (player and alien positions, scores and timeouts, etc...). Mutable.
    private int width;
    private int height;
    private Alien[] aliens;
    private boolean isPaused;
    private Room[] rooms;
    private int currentRoom;
    private PowerUp[] powerUps;
    private Player player;
    private Door door;
    private Key key;
    private int showKeyFor;
    private int timeoutAfter;
    private boolean completed;

    // Constructors

    public RunModeState(Alien[] aliens, boolean isPaused, Room[] rooms, PowerUp[] powerUps, Player player, Door door) {
        this.width = 1;
        this.height = 1;
        this.aliens = aliens;
        this.isPaused = isPaused;
        this.rooms = rooms;
        this.currentRoom = 0;
        this.powerUps = powerUps;
        this.player = player;
        this.door = door;
        setKey(new Random());
        this.showKeyFor = 0;
        resetTimeoutAfter();
        this.completed = false;
    }

    // Methods

    public int getWidth() {
        return width;
    }

    // EFFECT: Update the game width. New width should be positive.
    // MODIFIES: Game width.
    public void setWidth(int width) {
        if (width <= 0) throw new IllegalArgumentException();
        this.width = width;
        // TODO: Remove magic number
        this.door.setXPosition(width - 50);
    }

    public int getHeight() {
        return height;
    }

    // EFFECT: Update the game height. New height should be positive.
    // MODIFIES: Game height.
    public void setHeight(int height) {
        if (height <= 0) throw new IllegalArgumentException();
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

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void incCurrentRoom() {
        currentRoom++;
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

    public Key getKey() {
        return key;
    }

    public void setKey(Random random) {
        var room = getRooms()[getCurrentRoom()];
        var objects = room.getObjects();
        var randObj = objects.get(random.nextInt(objects.size()));
        this.key = new Key(randObj);
    }

    public int getShowKeyFor() {
        return showKeyFor;
    }

    public void setShowKeyFor(int showKeyFor) {
        this.showKeyFor = showKeyFor;
    }

    public void decShowKeyFor() {
        showKeyFor = Math.max(showKeyFor - 1, 0);
    }

    public long getTimeoutAfter() {
        return timeoutAfter;
    }

    public void resetTimeoutAfter() {
        // TODO: Remove magic numbers
        timeoutAfter = (int) ((getRooms()[getCurrentRoom()].getObjects().size() * 5 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    }

    public void decTimeoutAfter() {
        timeoutAfter = Math.max(timeoutAfter - 1, 0);
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted() {
        completed = true;
    }

    // Invariant Validity Check

    public boolean repOk() {
        // Width and height should be positive
        if (width <= 0 || height <= 0) return false;

        // The aliens list cannot be null
        if (aliens == null) return false;

        // The rooms list cannot be null or empty
        if (rooms == null || rooms.length == 0) return false;

        // Current room index should be a valid index
        if (currentRoom < 0 || currentRoom >= rooms.length) return false;
        // Make sure minimum object requirement is met
        for (var room : rooms) {
            if (room.getObjects().size() < room.getMinObjects()) return false;
        }

        // The powerups list cannot be null
        if (powerUps == null) return false;

        // The player cannot be null
        if (player == null) return false;

        // The door cannot be null
        if (door == null) return false;

        // The key cannot be null
        if (key == null) return false;

        // showKeyFor should be non-negative
        if (showKeyFor < 0) return false;

        // timeoutAfter should be non-negative
        return timeoutAfter >= 0;
    }
}
