package managers;

import utils.Constants;

import java.awt.*;

/**
 * Theme Manager
 * Provides theming and styling utilities.
 */
public class ThemeManager {
    public static final int TITLE_FONT_SIZE_MULTIPLIER = 3;

    public static String getTitle(String text) {
        return "<html><h1 style='font-size: %dem;'>%s</h1></html>".formatted(TITLE_FONT_SIZE_MULTIPLIER, text);
    }

    public static Font getMonospacedFont() {
        return new Font("Monospaced", Font.PLAIN, Constants.MONOSPACE_FONT_SIZE);
    }
}
