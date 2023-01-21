package utils;

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
    public static final int ENTITY_DIM = 64;
    public static final int OBJ_DIM = 32;
    public static final int MIN_DISTANCE = 100;

    // Animation
    // Frame Rate: 67 FPS
    public static final long REPAINT_DELAY_MILLS = 15;

    // MISC
    public static final String HOME_DIRECTORY = System.getProperty("user.home");
    public static final int SECOND_MILLS = 1000;

    // This class should not be instanced
    private Constants() {
    }
}
