package frontends;

import models.State;

import java.awt.*;

public interface Frontend<T extends State> {
    void drawState(T state, Graphics canvas);
}
