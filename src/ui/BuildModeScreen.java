package ui;

import control.Backend;
import models.Game;

import java.awt.*;

public class BuildModeScreen extends AnimatedScreen<Game> {
    public BuildModeScreen(Game state, Backend<Game> backend) {
        super(state, backend);
    }

    @Override
    void drawState(Game state, Graphics canvas) {

    }
}
