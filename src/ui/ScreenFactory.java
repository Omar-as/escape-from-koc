package ui;

import control.BuildModeBackend;
import control.RunModeBackend;
import models.*;
import models.alien.Alien;
import models.powerUps.PowerUp;
import ui.screens.*;

import java.util.ArrayList;

public class ScreenFactory {
    public static Screen getScreen(ScreenType type) {
        // EFFECTS:
        // If type is "SIGN_IN_SIGN_UP" returns getSignInSignUpScreen()
        // If type is "MAIN"            returns getMainScreen()
        // If type is "HELP"            returns getHelpScreen()
        // If type is "BUILD_MODE"      returns getBuildModeScreen()
        // If type is "RUN_MODE"        throws  IllegalArgumentException()
        // If type is "GAME_END"        throws  IllegalArgumentException()
        return switch (type) {
            case SIGN_IN_SIGN_UP -> getSignInSignUpScreen();
            case MAIN -> getMainScreen();
            case HELP -> getHelpScreen();
            case BUILD_MODE -> getBuildModeScreen();
            case SCOREBOARD -> getScoreboardScreen();
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

    private static ScoreboardScreen getScoreboardScreen() {
        return new ScoreboardScreen();
    }

    private static BuildModeScreen getBuildModeScreen() {
        var backend = new BuildModeBackend();
        // TODO: Make array constant
        return new BuildModeScreen(new BuildModeState(new Room[]{
                new Room("Student Center", 5),
                new Room("CASE Building", 7),
                new Room("SOS Building", 10),
                new Room("SCI Building", 14),
                new Room("ENG Building", 19),
                new Room("SNA Building", 25)
        }), backend);
    }

    public static RunModeScreen getRunModeScreen(BuildModeState buildModeState) {
        var player = new Player(5, 0, 0, 0, 64, 64);
        player.editBag("H",0);
        player.editBag("PV",0);
        player.editBag("PB",0);
        var state = new RunModeState(new ArrayList<Alien>() , false, buildModeState.getRooms(), new ArrayList<PowerUp>(), player, buildModeState.getDoor(), new ArrayList<Projectile>());
        return getRunModeScreen(state);
    }

    public static RunModeScreen getRunModeScreen(RunModeState state) {
        var backend = new RunModeBackend();
        return new RunModeScreen(state, backend);
    }

    public static GameEndScreen getGameEndScreen(boolean didWin) {
        return new GameEndScreen(didWin);
    }
}
