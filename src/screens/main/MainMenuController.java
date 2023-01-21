package screens.main;

import managers.AccountManager;
import managers.DataStoreManager;
import managers.ScreenManager;
import models.RunModeState;
import screens.ScreenFactory;
import utils.Constants;

import java.awt.event.ActionEvent;
import java.util.Objects;

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
        ScreenManager.getInstance().setScreen(ScreenFactory.getBuildModeScreen());
    }

    public static void handleLoadGameBtn(ActionEvent e) {
        // Find saved games
        var games = DataStoreManager.getInstance().getCollection(Constants.SAVED_GAMES_COLLECTION_NAME, RunModeState.class);
        var myGames = games.stream().filter(g -> Objects.equals(g.getUsername(), AccountManager.getUsername())).toArray(RunModeState[]::new);
        if (myGames.length == 0) {
            // No saved games
            ScreenManager.getInstance().showErrorDialog("No saved games.");
            return;
        }
        // Load saved game
        ScreenManager.getInstance().setScreen(ScreenFactory.getRunModeScreen(myGames[myGames.length - 1]));
    }

    public static void handleCreditsBtn(ActionEvent e) {
        ScreenManager.getInstance().setScreen(ScreenFactory.getCreditsScreen());
    }

    public static void handleScoreboardBtn(ActionEvent e) {
        ScreenManager.getInstance().setScreen(ScreenFactory.getScoreboardScreen());
    }

    public static void handleHelpBtn(ActionEvent e) {
        ScreenManager.getInstance().setScreen(ScreenFactory.getHelpScreen());
    }

    public static void handleBackToMain(ActionEvent e) {
        ScreenManager.getInstance().setScreen(ScreenFactory.getMainScreen());
    }
}
