package utils;

public enum Assets {
    TRASH_BIN("trash_bin"),
    DOOR_OPEN("door_opened"),
    DOOR_CLOSED("door_closed");

    private String name;

    Assets(String name) {
        this.name = name;
    }
}
