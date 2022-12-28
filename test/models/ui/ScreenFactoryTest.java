package ui;

import com.sun.tools.javac.Main;
import ui.screens.*;
import utils.Constants;

import static org.junit.jupiter.api.Assertions.*;

class ScreenFactoryTest {

    @org.junit.jupiter.api.Test
     void getScreen() {

        // Test Case 1:
        // From Spec
        // If type is "SIGN_IN_SIGN_UP" returns getSignInSignUpScreen()
        var signInSignUpScreen = ScreenFactory.getScreen(ScreenType.SIGN_IN_SIGN_UP);
        assertInstanceOf(SignInSignUpScreen.class, signInSignUpScreen);

        // Test Case 2:
        // From Spec
        // If type is "MAIN" returns getMainScreen()
        var MainScreen = ScreenFactory.getScreen(ScreenType.MAIN);
        assertInstanceOf(MainScreen.class, MainScreen);

        // Test Case 3:
        // From Spec
        // If type is "HELP" returns getHelpScreen()
        var HelpScreen = ScreenFactory.getScreen(ScreenType.HELP);
        assertInstanceOf(ui.screens.HelpScreen.class, HelpScreen);

        // Test Case 4:
        // From Spec
        // If type is "BUILD_MODE" returns getBuildModeScreen()
        var BuildModeScreen = ScreenFactory.getScreen(ScreenType.BUILD_MODE);
        assertInstanceOf(ui.screens.BuildModeScreen.class, BuildModeScreen);

        // Test Case 5:
        // From Spec
        // If type is "RUN_MODE" throws IllegalArgumentException()
        assertThrows(IllegalArgumentException.class, () -> {
            ScreenFactory.getScreen(ScreenType.RUN_MODE);
        });

        // Test Case 6:
        // From Spec
        // If type is "GAME_END" throws IllegalArgumentException()
        assertThrows(IllegalArgumentException.class, () -> {
            ScreenFactory.getScreen(ScreenType.GAME_END);
        });
    }
}