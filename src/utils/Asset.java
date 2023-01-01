package utils;

public enum Asset {
    PLAYER("player"),
    KEY("key"),
    ALIEN_BLIND("alien_blind"),
    ALIEN_SHOOTER("alien_shooter"),
    ALIEN_TIME_WASTING("alien_time_wasting"),
    TRASH_BIN("trash_bin"),
    CHALK_BOARD("chalk_board"),
    BOOKSHELF("bookshelf"),
    DOOR_OPEN("door_open"),
    DOOR_CLOSED("door_closed"),
    Background("background");

    public final String name;

    Asset(String name) {
        this.name = name;
    }
}
