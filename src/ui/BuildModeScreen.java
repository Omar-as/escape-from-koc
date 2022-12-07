package ui;

import control.Controller;
import models.Game;

import javax.swing.*;
import java.awt.*;

public class BuildModeScreen extends AnimatedScreen<Game> {
    public BuildModeScreen(Game state, Controller<Game> controller) {
        super(state, controller);
    }

    @Override
    void drawState(Game state, Graphics canvas) {

    }
}
