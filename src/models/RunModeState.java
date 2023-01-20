package models;

import models.alien.Alien;
import models.powerUps.PowerUp;
import utils.Constants;

import java.util.ArrayList;
import java.util.Random;

public class RunModeState extends State {
    private int width;
    private int height;
    private ArrayList<Alien> aliens;
    private boolean isPaused;
    private Room[] rooms;
    private int currentRoom;
    private ArrayList<PowerUp> powerUps;
    private Player player;
    private Door door;
    private Key key;
    private ArrayList<Projectile> projectiles;
    private int showKeyFor;
    private int timeoutAfter;
    private int timeForNextAlien;
    private int timeForNextPowerUp;
    private boolean completed;
    private int hintEffectTimer;
    private int protectionVestEffectTimer;

    public RunModeState(ArrayList<Alien> aliens, boolean isPaused, Room[] rooms, ArrayList<PowerUp> powerUps, Player player, Door door, ArrayList<Projectile> projectiles) {
        this.width = 0;
        this.height = 0;
        this.aliens = aliens;
        this.isPaused = isPaused;
        this.rooms = rooms;
        this.currentRoom = 0;
        this.powerUps = powerUps;
        this.player = player;
        this.door = door;
        setKey();
        this.showKeyFor = 0;
        resetTimeoutAfter();
        resetTimeForNextAlien();
        resetTimeForNextPowerUp();
        this.completed = false;
        this.projectiles = projectiles;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        this.door.setXPosition(width - Constants.entityDim);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        this.door.setYPosition(height - Constants.entityDim);
    }

    public ArrayList<Alien> getAliens() {
        return aliens;
    }

    public void setAliens(ArrayList<Alien> aliens) {
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

    public ArrayList<PowerUp> getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(ArrayList<PowerUp> powerUps) {
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

    public void setKey() {
        var random = new Random();
        var room = getRooms()[getCurrentRoom()];
        var objects = room.getObjects();
        var randObj = objects.get(random.nextInt(objects.size()));
        this.key = new Key(randObj);
    }

    public void setKey(Key key) {
        this.key = key;
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

    public void incTimeoutAfter(int time){ timeoutAfter += ((time * Constants.SECOND_MILLS)/Constants.REPAINT_DELAY_MILLS); }

    public long getTimeForNextAlien() {
        return timeForNextAlien;
    }

    public void resetTimeForNextAlien() {
        // TODO: Remove magic numbers
        timeForNextAlien = (int) ((10 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    }

    public void decTimeForNextAlien() {
        timeForNextAlien = Math.max(timeForNextAlien - 1, 0);
    }

    public long getTimeForNextPowerUp() {
        return timeForNextPowerUp;
    }

    public void resetTimeForNextPowerUp() {
        // TODO: Remove magic numbers
        timeForNextPowerUp = (int) ((12 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    }

    public void decTimeForNextPowerUp() {
        timeForNextPowerUp = Math.max(timeForNextPowerUp - 1, 0);
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted() {
        completed = true;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }
    public void resetHintEffectTimer(){
        hintEffectTimer = (int) ((10 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    }
    public void resetProtectionVestEffectTimer(){
        protectionVestEffectTimer = (int) ((20 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    }
    public void decHintEffectTimer() {
        hintEffectTimer = Math.max(hintEffectTimer - 1, 0);
    }
    public void decProtectionVestEffectTimer() {
        protectionVestEffectTimer = Math.max(protectionVestEffectTimer - 1, 0);
    }

    public int getHintEffectTimer() {
        return hintEffectTimer;
    }

    public int getProtectionVestEffectTimer() {
        return protectionVestEffectTimer;
    }
}
