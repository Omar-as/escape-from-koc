package ui.screens;

import control.Backend;
import models.RunModeState;
import ui.AnimatedScreen;
import ui.Canvas;
import ui.GraphicsManager;
import ui.ScreenManager;
import ui.frontends.RunModeFrontend;
import utils.Asset;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RunModeScreen extends AnimatedScreen<RunModeState> {
    private final Canvas<RunModeState> canvas;

    public RunModeScreen(RunModeState state, Backend<RunModeState> backend) {
        super(state, backend);

        this.setLayout(new GridLayout(0, 1));

        var mainColumn = Box.createVerticalBox();
        this.add(mainColumn);

        var frontend = new RunModeFrontend();

        var bar = Box.createHorizontalBox();
        // TODO: Remove magic number
        bar.setMaximumSize(new Dimension(Constants.FRAME_WIDTH, 10));
        mainColumn.add(bar);

        var roomLabel = new JLabel(state.getRooms()[state.getCurrentRoom()].getName());

        var exitBtn = new JButton("Exit");
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ScreenManager.getInstance().setScreen(new MainScreen());
            }
        });

        bar.add(Box.createGlue());
        bar.add(roomLabel);
        bar.add(Box.createGlue());
        bar.add(exitBtn);

        canvas  = new Canvas<>(state, frontend);
        mainColumn.add(canvas);
    }

    @Override
    protected void drawFrame(RunModeState state, Backend<RunModeState> backend) {
        state.setWidth(canvas.getWidth());
        state.setHeight(canvas.getHeight());
        backend.updateState(state);
        canvas.repaint();
    }
}
