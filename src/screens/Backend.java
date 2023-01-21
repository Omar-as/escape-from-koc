package screens;

import models.State;

/**
 * Backend Interface
 * Update state for animated screens.
 * Model-View Separation.
 */
public interface Backend<T extends State> {
    void updateState(T state);
}
