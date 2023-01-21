package models;

public class GameData {
    private final String username;
    private final int time;

    public GameData(String username, int time) {
        this.username = username;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public int getTime() {
        return time;
    }
}
