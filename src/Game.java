import javax.swing.*;

public class Game {
    private Alien[] aliens;
    private pauseMenu pausemenu;
    private gameData data;
    private Room rooms;
    private PowerUp[] powerUps;
    private Player player;

    public Game(Alien[] aliens, pauseMenu pausemenu, gameData data, Room rooms, PowerUp[] powerUps, Player player) {
        this.aliens = aliens;
        this.pausemenu = pausemenu;
        this.data = data;
        this.rooms = rooms;
        this.powerUps = powerUps;
        this.player = player;
    }

    public Alien[] getAliens() {
        return aliens;
    }

    public void setAliens(Alien[] aliens) {
        this.aliens = aliens;
    }

    public pauseMenu getPausemenu() {
        return pausemenu;
    }

    public void setPausemenu(pauseMenu pausemenu) {
        this.pausemenu = pausemenu;
    }

    public gameData getData() {
        return data;
    }

    public void setData(gameData data) {
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
