import control.RunModeBackend;
import models.*;
import models.alien.Alien;
import models.alien.AlienType;
import ui.RunModeScreen;
import control.BuildModeBackend;
import models.BuildModeState;
import ui.BuildModeScreen;
import ui.ScreenManager;
import utils.Constants;
import models.Game;

import java.io.IOException;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
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
        
       // new RunModeScreen(new Game(
       // new Alien[]{new Alien(AlienType.BLIND, 100, 100,50,50),
       //         new Alien(AlienType.SHOOTER, 200, 200,50,50),
       //         new Alien(AlienType.TIME_WASTING, 300, 300,50,50)},
       // false,null,
       //  new Room[]{new Room(new Key(100,100,false),
       //         null,
       //         new Door("closedDoor","openedDoor",100, 100, 300, 300))},
       //  null,
       //  new Player(5, 0, 0, 0,100,100,"player")),
       // new RunModeBackend())
    }
}