package screens;

import screens.auth.SignInSignUpScreen;
import screens.build.BuildModeScreen;
import screens.credits.CreditsScreen;
import screens.end.GameEndScreen;
import screens.help.HelpScreen;
import screens.main.MainScreen;
import screens.scoreboard.ScoreboardScreen;

import static org.junit.jupiter.api.Assertions.*;

class ScreenFactoryTest {
    @org.junit.jupiter.api.Test
    void getScreen() {
        // Test Case 1 - From Spec:
        // Constructs and returns a SignInSignUpScreen
        var signInSignUpScreen = ScreenFactory.getSignInSignUpScreen();
        assertInstanceOf(SignInSignUpScreen.class, signInSignUpScreen);
        assertNotNull(signInSignUpScreen);

        // Test Case 2 - From Spec:
        // Constructs and returns a MainScreen
        var mainScreen = ScreenFactory.getMainScreen();
        assertInstanceOf(MainScreen.class, mainScreen);
        assertNotNull(mainScreen);

        // Test Case 3 - From Spec:
        // Constructs and returns a SignInSignUpScreen
        var helpScreen = ScreenFactory.getHelpScreen();
        assertInstanceOf(HelpScreen.class, helpScreen);
        assertNotNull(helpScreen);

        // Test Case 4 - From Spec:
        // Constructs and returns a ScoreboardScreen
        var scoreboardScreen = ScreenFactory.getScoreboardScreen();
        assertInstanceOf(ScoreboardScreen.class, scoreboardScreen);
        assertNotNull(scoreboardScreen);

        // Test Case 5 - From Spec:
        // Constructs and returns a CreditsScreen
        var creditsScreen = ScreenFactory.getCreditsScreen();
        assertInstanceOf(CreditsScreen.class, creditsScreen);
        assertNotNull(creditsScreen);

        // Test Case 6 - From Spec:
        // Constructs and returns a BuildModeScreen
        var buildModeScreen = ScreenFactory.getBuildModeScreen();
        assertInstanceOf(BuildModeScreen.class, buildModeScreen);
        assertNotNull(buildModeScreen);

        // Test Case 7 - From Spec:
        // Constructs and returns a GameEndScreen
        var gameEndScreen = ScreenFactory.getGameEndScreen(true);
        assertInstanceOf(GameEndScreen.class, gameEndScreen);
        assertNotNull(gameEndScreen);
    }
}