package ui;

import models.BuildModeState;

import java.awt.*;

public class BuildModeFrontend implements Frontend<BuildModeState> {
    @Override
    public void drawState(BuildModeState state, Graphics canvas) {

        canvas.setColor(Color.BLACK);
        canvas.fillRect(0, 0, 2000, 2000);
        canvas.drawString(String.valueOf(state.getCurrentRoom()), 0, 0);
    }
}
