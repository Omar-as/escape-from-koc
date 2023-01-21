import screens.ScreenFactory;
import managers.ScreenManager;
import screens.ScreenType;
import utils.Constants;

public class Main {
    /**
     * Entry point of the program.
     */
    public static void main(String[] args) {
        // Launch game on the "Sign In / Sign Up" screen
        ScreenManager.getInstance().launch(
                Constants.FRAME_WIDTH,
                Constants.FRAME_HEIGHT,
                Constants.FRAME_TITLE,
                ScreenFactory.getScreen(ScreenType.SIGN_IN_SIGN_UP)
        );
    }
}