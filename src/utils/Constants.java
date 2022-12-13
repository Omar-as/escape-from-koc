package utils;

public final class Constants {
    // Config / Data Directory
    public static final String HOME_DIRECTORY = System.getProperty("user.home");
    public static final String CONFIG_DIR_PATH = "%s/.config/escape_from_koç/";
    public static final String CONFIG_FILE_PATH = CONFIG_DIR_PATH + "%s/";
    public static final String ACCOUNTS_FILE_NAME = "accounts.txt";
    public static final int FRAME_WIDTH = 1366;
    public static final int FRAME_HEIGHT = 768;
    public static final String FRAME_TITLE = "Escape From Koç";
    // Frame Rate: 67 FPS
    public static final long REPAINT_DELAY_MILLS = 15;
    public static final int SECOND_MILLS = 1000;
    // Run Mode
    public static final int PLAYER_SPEED = 10;

    // This class should not be instanced
    private Constants() {
    }
}
