package screens;

import models.State;

import java.awt.*;

/**
 * Frontend Interface
 * Update canvas for animated screens.
 * Model-View Separation.
 */
public interface Frontend<T extends State> {
    void drawState(T state, Graphics canvas);
}
