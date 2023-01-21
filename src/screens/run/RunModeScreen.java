package screens.run;

import managers.DataStoreManager;
import managers.ScreenManager;
import models.RunModeState;
import screens.AnimatedScreen;
import screens.Backend;
import screens.Canvas;
import screens.ScreenFactory;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RunModeScreen extends AnimatedScreen<RunModeState> {
    private final JLabel roomNameLabel;
    private final JLabel timeLabel;
    private final JLabel liveLabel;
    private final JLabel powerUps;
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
        timeLabel = new JLabel();
        liveLabel = new JLabel();
        powerUps = new JLabel();

        var pauseResumeButton = new JButton("Pause");
        pauseResumeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                state.setPause(!state.isPaused());
                pauseResumeButton.setText(state.isPaused() ? "Resume" : "Pause");
            }
        });
        var saveGameButton = new JButton("Save");
        saveGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DataStoreManager.getInstance().addToCollection(Constants.SAVED_GAMES_COLLECTION_NAME, state, RunModeState.class);
            }
        });
        var exitBtn = new JButton("Exit");
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScreenManager.getInstance().setScreen(ScreenFactory.getMainScreen());
                state.setCompleted();
            }
        });

        bar.add(timeLabel);
        bar.add(Box.createGlue());
        bar.add(liveLabel);
        bar.add(Box.createGlue());
        bar.add(powerUps);
        bar.add(Box.createGlue());
        bar.add(roomNameLabel);
        bar.add(Box.createGlue());
        bar.add(pauseResumeButton);
        bar.add(saveGameButton);
        bar.add(exitBtn);

        canvas = new Canvas<>(state, frontend);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Left-click picks lifts object / picks up key
                if (SwingUtilities.isLeftMouseButton(e)) backend.pickupKey(state, e.getX(), e.getY());
                    // Right-click picks up power-up
                else if (SwingUtilities.isRightMouseButton(e)) backend.pickupPowerUp(state, e.getX(), e.getY());
            }
        });
        mainColumn.add(canvas);
    }

    @Override
    protected void drawFrame(RunModeState state, Backend<RunModeState> backend) {
        if (!state.isPaused()) {
            state.setWidth(Math.max(canvas.getWidth(), 1));
            state.setHeight(Math.max(canvas.getHeight(), 1));
            backend.updateState(state);
            roomNameLabel.setText(state.getRooms()[state.getCurrentRoom()].getName());
            long remainingSeconds = (state.getTimeoutAfter() * Constants.REPAINT_DELAY_MILLS) / Constants.SECOND_MILLS;
            timeLabel.setText("Remaining: %d s".formatted(remainingSeconds));
            liveLabel.setText("Lives: %d".formatted(state.getPlayer().getLives()));
            powerUps.setText(state.getPlayer().getBagAsString());
            canvas.repaint();
        }
    }
}
