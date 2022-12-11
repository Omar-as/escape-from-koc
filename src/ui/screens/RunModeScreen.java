package ui.screens;

import control.Backend;
import control.RunModeBackend;
import models.RunModeState;
import ui.*;
import ui.Canvas;
import ui.frontends.RunModeFrontend;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RunModeScreen extends AnimatedScreen<RunModeState> {
    private final JLabel roomNameLabel;
    private final JLabel timeLabel;
    private final Canvas<RunModeState> canvas;

    public RunModeScreen(RunModeState state, RunModeBackend backend) {
        super(state, backend);

        this.setLayout(new GridLayout(0, 1));

        var mainColumn = Box.createVerticalBox();
        this.add(mainColumn);

        var frontend = new RunModeFrontend();

        var bar = Box.createHorizontalBox();
        // TODO: Remove magic number
        bar.setMaximumSize(new Dimension(Constants.FRAME_WIDTH, 10));
        mainColumn.add(bar);

        roomNameLabel = new JLabel(state.getRooms()[state.getCurrentRoom()].getName());
        timeLabel     = new JLabel();

        var pauseResumeButton = new JButton("Pause");
        pauseResumeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                state.setPause(!state.isPaused());
                pauseResumeButton.setText(state.isPaused() ? "Resume" : "Pause");
            }
        });
        var exitBtn = new JButton("Exit");
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScreenManager.getInstance().setScreen(ScreenFactory.getScreen(ScreenType.MAIN));
            }
        });

        bar.add(timeLabel);
        bar.add(Box.createGlue());
        bar.add(roomNameLabel);
        bar.add(Box.createGlue());
        bar.add(pauseResumeButton);
        bar.add(exitBtn);

        canvas  = new Canvas<>(state, frontend);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backend.pickupKey(state, e.getX(), e.getY());
            }
        });
        mainColumn.add(canvas);
    }

    @Override
    protected void drawFrame(RunModeState state, Backend<RunModeState> backend) {
        if (!state.isPaused()) {
            state.setWidth(canvas.getWidth());
            state.setHeight(canvas.getHeight());
            backend.updateState(state);
            roomNameLabel.setText(state.getRooms()[state.getCurrentRoom()].getName());
            long remainingSeconds = (state.getTimeoutAfter() * Constants.REPAINT_DELAY_MILLS) / Constants.SECOND_MILLS;
            timeLabel.setText("Remaining: %d s".formatted(remainingSeconds));
            canvas.repaint();
        }
    }
}
