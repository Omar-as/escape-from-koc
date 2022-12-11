import ui.ScreenManager;
import ui.screens.SignInSignUpScreen;
import utils.Constants;

public class Main {
    public static void main(String[] args) {
        // Launch game on the "Sign In / Sign Up" screen
        ScreenManager.getInstance().launch(
                Constants.FRAME_WIDTH,
                Constants.FRAME_HEIGHT,
                Constants.FRAME_TITLE,
                new SignInSignUpScreen()
        );
    }
}