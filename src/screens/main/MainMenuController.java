package screens.main;

import managers.DataStoreManager;
import managers.ScreenManager;
import models.RunModeState;
import screens.ScreenFactory;
import screens.ScreenType;
import utils.Constants;

import java.awt.event.ActionEvent;

/**
 * Main Menu Controller
 * <p>
 * Handles events originating from the Main Screen.
 * <p>
 * Patterns:
 * 1. Controller: Handle events in the responsible classes instead of handling them in the UI.
 * <p>
 * Model-View Separation:
 * No logic implemented in the UI.
 */
public final class MainMenuController {
    private MainMenuController() {
    }

    public static void handleNewGameBtn(ActionEvent e) {
        ScreenManager.getInstance().setScreen(ScreenFactory.getScreen(ScreenType.BUILD_MODE));
    }

    public static void handleLoadGameBtn(ActionEvent e) {
        // Find saved games
        var games = DataStoreManager.getInstance().getCollection(Constants.SAVED_GAMES_COLLECTION_NAME, RunModeState.class);
        if (games.isEmpty()) {
            // No saved games
            ScreenManager.getInstance().showErrorDialog("No saved games.");
            return;
        }
        // Load saved game
        ScreenManager.getInstance().setScreen(ScreenFactory.getRunModeScreen(games.get(games.size() - 1)));
    }

    public static void handleCreditsBtn(ActionEvent e) {
        ScreenManager.getInstance().setScreen(ScreenFactory.getScreen(ScreenType.CREDITS));
    }

    public static void handleScoreboardBtn(ActionEvent e) {
        ScreenManager.getInstance().setScreen(ScreenFactory.getScreen(ScreenType.SCOREBOARD));
    }

    public static void handleHelpBtn(ActionEvent e) {
        ScreenManager.getInstance().setScreen(ScreenFactory.getScreen(ScreenType.HELP));
    }
}
