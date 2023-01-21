package utils;

/**
 * Asset Definitions
 * Access assets with ease without repetition.
 */
public enum Asset {
    // Player Sprites
    PLAYER_IDLE("player_idle"),
    PLAYER_MOVE_RIGHT1("player_mr1"),
    PLAYER_MOVE_RIGHT2("player_mr2"),
    PLAYER_MOVE_LEFT1("player_ml1"),
    PLAYER_MOVE_LEFT2("player_ml2"),
    PLAYER_MOVE_DOWN1("player_md1"),
    PLAYER_MOVE_DOWN2("player_md2"),
    PLAYER_MOVE_UP1("player_mu1"),
    PLAYER_MOVE_UP2("player_mu2"),
    ARMOUR_PLAYER_IDLE("arm_player_idle"),
    ARMOUR_PLAYER_MOVE_RIGHT1("arm_player_mr1"),
    ARMOUR_PLAYER_MOVE_RIGHT2("arm_player_mr2"),
    ARMOUR_PLAYER_MOVE_LEFT1("arm_player_ml1"),
    ARMOUR_PLAYER_MOVE_LEFT2("arm_player_ml2"),
    ARMOUR_PLAYER_MOVE_DOWN1("arm_player_md1"),
    ARMOUR_PLAYER_MOVE_DOWN2("arm_player_md2"),
    ARMOUR_PLAYER_MOVE_UP1("arm_player_mu1"),
    ARMOUR_PLAYER_MOVE_UP2("arm_player_mu2"),

    // Blind Alien Sprites
    ALIEN_BLIND_IDLE("alien_blind"),
    ALIEN_BLIND_MOVE_RIGHT1("alien_blind_mr1"),
    ALIEN_BLIND_MOVE_RIGHT2("alien_blind_mr2"),
    ALIEN_BLIND_MOVE_LEFT1("alien_blind_ml1"),
    ALIEN_BLIND_MOVE_LEFT2("alien_blind_ml2"),
    ALIEN_BLIND_MOVE_DOWN1("alien_blind_md1"),
    ALIEN_BLIND_MOVE_DOWN2("alien_blind_md2"),
    ALIEN_BLIND_MOVE_UP1("alien_blind_mu1"),
    ALIEN_BLIND_MOVE_UP2("alien_blind_mu2"),

    // Shooter Alien Sprites
    ALIEN_SHOOTER("alien_shooter"),
    PROJECTILE("projectile"),

    // Time Wasting Alien Sprites
    ALIEN_FRIENDLY_TIME_WASTING("alien_friendly_time_wasting"),
    ALIEN_CONFUSED_TIME_WASTING("alien_confused_time_wasting"),
    ALIEN_AGGRESSIVE_TIME_WASTING("alien_aggressive_time_wasting"),

    // PowerUps
    PLASTIC_BOTTLE("power_up_plastic_bottle"),
    PROTECTION_VEST("power_up_protection_vest"),
    EXTRA_LIFE("power_up_extra_life"),
    HINT("power_up_hint"),
    EXTRA_TIME("power_up_extra_time"),

    // Objects
    TRASH_BIN("trash_bin"),
    CHALK_BOARD("chalk_board"),
    BOOKSHELF("bookshelf"),
    DESK("desk"),
    PRINTER("printer"),

    // Door States
    DOOR_OPEN("door_open"),
    DOOR_CLOSED("door_closed"),

    // MISC
    KEY("key"),
    BACKGROUND("background");

    public final String name;

    Asset(String name) {
        this.name = name;
    }
}
