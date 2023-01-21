package screens;

import models.State;
import utils.Constants;

/**
 * Animated Screen
 * A screen with abstract animation logic.
 */
public abstract class AnimatedScreen<T extends State> extends Screen implements Runnable {
    private final T state;
    private final Backend<T> backend;

    public AnimatedScreen(T state, Backend<T> backend) {
        this.state = state;
        this.backend = backend;
    }

    @Override
    public void addNotify() { // Called when this screen is added to the frame
        super.addNotify();

        Thread animator = new Thread(this);
        animator.start();
    }

    protected abstract void drawFrame(T state, Backend<T> backend);

    @Override
    public void run() {
        while (true) {
            long startTime = System.currentTimeMillis();

            drawFrame(state, backend);

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
            } catch (InterruptedException ignored) {
            }
        }
    }
}
