package screens;

import com.google.gson.Gson;
import models.BuildModeState;
import models.Room;
import models.RunModeState;
import screens.auth.SignInSignUpScreen;
import screens.build.BuildModeBackend;
import screens.build.BuildModeScreen;
import screens.credits.CreditsScreen;
import screens.end.GameEndScreen;
import screens.help.HelpScreen;
import screens.main.MainScreen;
import screens.run.RunModeBackend;
import screens.run.RunModeScreen;
import screens.scoreboard.ScoreboardScreen;
import utils.Constants;

/**
 * Screen Factory
 * <p>
 * Handles creation logic for all screen types.
 * <p>
 * Patterns:
 * 1. Information Expert: This class has all the needed information to create any type of screen.
 * 2. Low Coupling      : Other parts of the code don't need to rely on specific creation logic.
 * 3. High Cohesion     : One responsibility done well.
 * 4. Factory           : Factory GoF pattern.
 */
public class ScreenFactory {
    public static SignInSignUpScreen getSignInSignUpScreen() {
        // EFFECTS:
        // Constructs and returns a SignInSignUpScreen
        return new SignInSignUpScreen();
    }

    public static MainScreen getMainScreen() {
        // EFFECTS:
        // Constructs and returns a MainScreen
        return new MainScreen();
    }

    public static HelpScreen getHelpScreen() {
        // EFFECTS:
        // Constructs and returns a HelpScreen
        return new HelpScreen();
    }

    public static ScoreboardScreen getScoreboardScreen() {
        // EFFECTS:
        // Constructs and returns a ScoreboardScreen
        return new ScoreboardScreen();
    }

    public static CreditsScreen getCreditsScreen() {
        // EFFECTS:
        // Constructs and returns a CreditScreen
        return new CreditsScreen();
    }

    public static BuildModeScreen getBuildModeScreen() {
        // EFFECTS:
        // Constructs and returns a BuildModeScreen
        var backend = new BuildModeBackend();
        var rooms = new Gson().fromJson(Constants.DEFAULT_ROOMS, Room[].class);
        return new BuildModeScreen(new BuildModeState(rooms), backend);
    }

    public static RunModeScreen getRunModeScreen(BuildModeState state) {
        // EFFECTS:
        // Constructs and returns a RunModeScreen
        return getRunModeScreen(new RunModeState(state.getRooms()));
    }

    public static RunModeScreen getRunModeScreen(RunModeState state) {
        // EFFECTS:
        // Constructs and returns a RunModeScreen
        var backend = new RunModeBackend();
        return new RunModeScreen(state, backend);
    }

    public static GameEndScreen getGameEndScreen(boolean didWin) {
        // EFFECTS:
        // Constructs and returns a GameEndScreen
        return new GameEndScreen(didWin);
    }
}
