package screens;

import models.State;

import javax.swing.*;
import java.awt.*;

/**
 * Canvas
 * Draw frames of animated screens.
 */
public class Canvas<T extends State> extends JPanel {
    private final T state;
    private final Frontend<T> frontend;

    public Canvas(T state, Frontend<T> frontend) {
        this.state = state;
        this.frontend = frontend;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        frontend.drawState(state, g);
    }
}
