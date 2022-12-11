package utils;

public enum Asset {
    PLAYER("player"),
    ALIEN_BLIND("alien_blind"),
    ALIEN_SHOOTER("alien_shooter"),
    ALIEN_TIME_WASTING("alien_time_wasting"),
    TRASH_BIN("trash_bin"),
    CHALK_BOARD("chalk_board"),
    DOOR_OPEN("door_opened"),
    DOOR_CLOSED("door_closed");

    public final String name;

    Asset(String name) {
        this.name = name;
    }
}
