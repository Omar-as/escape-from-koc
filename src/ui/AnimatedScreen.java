package ui;

import models.State;
import control.Controller;
import utils.Constants;

import java.awt.*;

public abstract class AnimatedScreen<T extends State> extends Screen implements Runnable {
    private T state;
    private Controller<T> controller;

    public AnimatedScreen(T state, Controller<T> controller) {
        this.state = state;
        this.controller = controller;
    }

    abstract void drawState(T state, Graphics canvas);

    @Override
    public void addNotify() { // Called when this screen is added to the frame
        super.addNotify();

        Thread animator = new Thread(this);
        animator.start();
    }

    @Override
    public void run() {
        while (true) {
            long startTime = System.currentTimeMillis();

            controller.updateState(state);
            repaint();

            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;

            // If the condition holds, then we are exactly on schedule or late
            // In this case, we start drawing the next frame immediately
            if (timeTaken >= Constants.REPAINT_DELAY_MILLS) continue;

            // If this gets executed, it means that the condition did not hold
            // In this case, we finished drawing the frame early
            // So we sleep until the time for the next frame comes, to keep the frame rate consistent
            try {
                Thread.sleep(Constants.REPAINT_DELAY_MILLS - timeTaken);
            } catch (InterruptedException ignored) { }
        }
    }

    @Override
    public void paintComponent(Graphics canvas) {
        super.paintComponent(canvas);
        drawState(state, canvas);
    }
}
