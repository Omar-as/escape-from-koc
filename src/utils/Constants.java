package utils;

import com.google.gson.Gson;
import models.Room;

/**
 * Useful Application-Wide Constants
 * Saves us from throwing magic numbers all around the code.
 */
public final class Constants {
    // File Data Store Directory
    public static final String CONFIG_DIR_PATH = "%s/.config/escape_from_koç/";
    public static final String CONFIG_FILE_PATH = CONFIG_DIR_PATH + "%s/";

    // Data Store Collections
    public static final String ACCOUNTS_COLLECTION_NAME = "accounts";
    public static final String SAVED_GAMES_COLLECTION_NAME = "games";
    public static final String SCOREBOARD_COLLECTION_NAME = "scoreboard";

    // Assets
    public static final String ASSET_IMAGE_PATH = "assets/%s.png";

    // Frame
    public static final int FRAME_WIDTH = 1366;
    public static final int FRAME_HEIGHT = 768;
    public static final String FRAME_TITLE = "Escape From Koç";

    // In-Game
    public static final int PLAYER_SPEED = 5;
    public static final int BLIND_SPEED = 3;
    public static final int MIN_DISTANCE = 100;
    public static final String DEFAULT_ROOMS = new Gson().toJson(new Room[]{
            new Room("Student Center", 5),
            new Room("CASE Building", 7),
            new Room("SOS Building", 10),
            new Room("SCI Building", 14),
            new Room("ENG Building", 19),
            new Room("SNA Building", 25)
    });
    public static final int STARTING_LIVES = 3;
    public static final int STARTING_X = 0;
    public static final int STARTING_Y = 0;
    public static final int PLAYER_DIM = 64;
    public static final int DOOR_DIM = 64;
    public static final int ALIEN_DIM = 64;
    public static final int OBJ_DIM = 32;
    public static final int BLOCK_DIM = 32;
    public static final int POWER_UP_DIM = 32;
    public static final int SHOOTER_ALIEN_DEADLY_RANGE = 4;
    public static final int BLIND_ALIEN_DEADLY_RANGE = 2;
    public static final int SECONDS_PER_OBJECT = 5;
    public static final float PERCENT_PASSED_FRIENDLY = 0.3f;
    public static final float PERCENT_PASSED_AGGRESSIVE = 0.7f;


    // Animation
    // Frame Rate: 67 FPS
    public static final long REPAINT_DELAY_MILLS = 15;

    // Theme
    public static final int MONOSPACE_FONT_SIZE = 16;
    public static final int HELP_TEXT_WIDTH = 1000;
    public static final int HELP_TEXT_HEIGHT = 500;

    // MISC
    public static final String HOME_DIRECTORY = System.getProperty("user.home");
    public static final int SECOND_MILLS = 1000;
    public static final int SCOREBOARD_MAX = 5;

    // This class should not be instanced
    private Constants() {
    }
}
