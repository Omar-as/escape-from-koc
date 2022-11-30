package control;

import models.DummyState;

public class DummyController implements Controller<DummyState> {
    @Override
    public void updateState(DummyState state) {
        state.setXPosition(state.getPosition().getX() + 10);
        state.setYPosition(state.getPosition().getY() + 10);

    }
}
