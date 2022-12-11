package ui;

import control.BuildModeBackend;
import control.RunModeBackend;
import models.BuildModeState;
import models.Player;
import models.RunModeState;
import models.alien.Alien;
import ui.screens.*;

import java.util.stream.Stream;

public class ScreenFactory {
    public static Screen getScreen(ScreenType type) {
        return switch (type) {
            case SIGN_IN_SIGN_UP -> getSignInSignUpScreen();
            case MAIN -> getMainScreen();
            case HELP -> getHelpScreen();
            case BUILD_MODE -> getBuildModeScreen();
            case RUN_MODE, GAME_END -> throw new IllegalArgumentException();
        };
    }

    private static SignInSignUpScreen getSignInSignUpScreen() {
        return new SignInSignUpScreen();
    }

    private static MainScreen getMainScreen() {
        return new MainScreen();
    }

    private static HelpScreen getHelpScreen() {
        return new HelpScreen();
    }

    private static BuildModeScreen getBuildModeScreen() {
        var backend = new BuildModeBackend();
        // TODO: Make array constant
        return new BuildModeScreen(new BuildModeState(Stream.of(
                "Student Center",
                "CASE Building",
                "SOS Building",
                "SCI Building",
                "ENG Building",
                "SNA Building"
        )), backend);
    }

    public static RunModeScreen getRunModeScreen(BuildModeState buildModeState) {
        var player  = new Player(5, 0, 0, 0,100,100);
        var state   = new RunModeState(null, false, buildModeState.getRooms(), null, player, buildModeState.getDoor());
        var backend = new RunModeBackend();
        return new RunModeScreen(state, backend);
    }

    public static GameEndScreen getGameEndScreen(boolean didWin) {
        return new GameEndScreen(didWin);
    }
}
