import control.RunModeBackend;
import models.*;
import models.alien.Alien;
import models.alien.AlienType;
import ui.RunModeScreen;
import ui.ScreenManager;
import ui.SignInSignUpScreen;
import utils.Constants;
import control.Backend;
import models.Game;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Launch game on the "Sign In / Sign Up" screen
        ScreenManager.getInstance().launch(
                Constants.FRAME_WIDTH,
                Constants.FRAME_HEIGHT,
                Constants.FRAME_TITLE,
                new RunModeScreen(new Game(
                        new Alien[]{new Alien(AlienType.BLIND, 100, 100,50,50),
                                new Alien(AlienType.SHOOTER, 100, 100,50,50),
                                new Alien(AlienType.TIME_WASTING, 100, 100,50,50)},
                        false,null,
                        new Room[]{new Room(new Key(100,100,false),
                                null,
                                new Door(100, 100, 300, 300))},
                        null,
                        new Player(5, 0, 0, 0,100,100)),
                        new RunModeBackend())
        );
    }
}