package control;

import models.State;

public interface Controller<T extends State> {
    void updateState(T state);
}
