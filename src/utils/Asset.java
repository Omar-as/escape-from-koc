package utils;

public enum Asset {
    PLAYER_IDLE("player_idle"),
    PLAYER_MOVE_RIGHT1("player_mr1"),
    PLAYER_MOVE_RIGHT2("player_mr2"),
    PLAYER_MOVE_LEFT1("player_ml1"),
    PLAYER_MOVE_LEFT2("player_ml2"),
    PLAYER_MOVE_DOWN1("player_md1"),
    PLAYER_MOVE_DOWN2("player_md2"),
    PLAYER_MOVE_UP1("player_mu1"),
    PLAYER_MOVE_UP2("player_mu2"),

    KEY("key"),
    ALIEN_BLIND_IDLE("alien_blind_idle"),
    ALIEN_SHOOTER("alien_shooter"),
    ALIEN_TIME_WASTING("alien_time_wasting"),
    TRASH_BIN("trash_bin"),
    CHALK_BOARD("chalk_board"),
    BOOKSHELF("bookshelf"),
    DESK("desk"),
    PRINTER("printer"),
    DOOR_OPEN("door_open"),
    DOOR_CLOSED("door_closed"),
    BACKGROUND("background");

    public final String name;

    Asset(String name) {
        this.name = name;
    }
}
