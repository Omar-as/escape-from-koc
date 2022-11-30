import ui.ScreenManager;
import ui.SignInSignUpScreen;

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