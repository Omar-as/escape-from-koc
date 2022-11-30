package ui;

import control.Controller;
import models.DummyState;

import java.awt.*;

public class RunModeScreen extends AnimatedScreen<DummyState>{
    public RunModeScreen(DummyState state, Controller<DummyState> controller) {
        super(state, controller);
    }

    @Override
    void drawState(DummyState state, Graphics canvas) {
        canvas.clearRect(0, 0, 1024, 800);
        canvas.setColor(Color.BLACK);
        canvas.drawRect(0, 0, 100, 100);
        canvas.fillRect(state.getPosition().getX(), state.getPosition().getY(), 10, 10);
    }
}
