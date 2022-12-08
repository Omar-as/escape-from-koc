import control.BuildModeBackend;
import models.BuildModeState;
import ui.BuildModeScreen;
import ui.ScreenManager;
import utils.Constants;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Launch game on the "Sign In / Sign Up" screen
        var backend = new BuildModeBackend();
        var screen  = new BuildModeScreen(new BuildModeState(Stream.of(
                "Student Center",
                "CASE Building",
                "SOS Building",
                "SCI Building",
                "ENG Building",
                "SNA Building"
        )), backend);

        ScreenManager.getInstance().launch(
                Constants.FRAME_WIDTH,
                Constants.FRAME_HEIGHT,
                Constants.FRAME_TITLE,
                screen
        );
    }
}