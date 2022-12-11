package ui.screens;

import control.Backend;
import control.BuildModeBackend;
import control.RunModeBackend;
import models.*;
import models.alien.Alien;
import models.alien.AlienType;
import models.objects.ObjectType;
import ui.AnimatedScreen;
import ui.frontends.BuildModeFrontend;
import ui.Canvas;
import ui.ScreenManager;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuildModeScreen extends AnimatedScreen<BuildModeState> {
    private final Canvas<BuildModeState> canvas;

    public BuildModeScreen(BuildModeState state, BuildModeBackend backend) {
        super(state, backend);

        this.setLayout(new GridLayout(0, 1));

        var mainColumn = Box.createVerticalBox();
        this.add(mainColumn);

        var frontend = new BuildModeFrontend();

        var bar = Box.createHorizontalBox();
        // TODO: Remove magic number
        bar.setMaximumSize(new Dimension(Constants.FRAME_WIDTH, 10));
        mainColumn.add(bar);

        var roomLabel = new JLabel(state.getRooms()[state.getCurrentRoom()].getName());

        var insertMenu = new JPopupMenu();
        addInsertMenuItem("Trash Bin", insertMenu, backend, state, ObjectType.TRASH_BIN);
        addInsertMenuItem("Chalk Board", insertMenu, backend, state, ObjectType.CHALK_BOARD);

        var insertBtn = new JButton("Insert");
        insertBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                insertMenu.show(insertBtn , insertBtn.getX(), insertBtn.getY() + insertBtn.getHeight());
            }
        });

        var nextBtn = new JButton("Next Room");
        nextBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int currentRoom = state.getCurrentRoom();
                state.setCurrentRoom(currentRoom != state.getRooms().length - 1 ? currentRoom + 1 : 0);
                roomLabel.setText(state.getRooms()[state.getCurrentRoom()].getName());
            }
        });

        var prevBtn = new JButton("Prev Room");
        prevBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int currentRoom = state.getCurrentRoom();
                state.setCurrentRoom(currentRoom != 0 ? currentRoom - 1 : state.getRooms().length - 1);
                roomLabel.setText(state.getRooms()[state.getCurrentRoom()].getName());
            }
        });

        var finishBtn = new JButton("Finish");
        finishBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                var aliens = new Alien[] {
                        new Alien(AlienType.BLIND, 100, 100,50,50),
                        new Alien(AlienType.SHOOTER, 200, 200,50,50),
                        new Alien(AlienType.TIME_WASTING, 300, 300,50,50)
                };
                var player = new Player(5, 0, 0, 0,100,100);
                var runModeState = new RunModeState(aliens, false, state.getRooms(), null, player, state.getDoor());
                var backend = new RunModeBackend();
                var screen = new RunModeScreen(runModeState, backend);
                ScreenManager.getInstance().setScreen(screen);
            }
        });

        var exitBtn = new JButton("Exit");
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ScreenManager.getInstance().setScreen(new MainScreen());
            }
        });

        bar.add(insertBtn);
        bar.add(prevBtn);
        bar.add(nextBtn);
        bar.add(Box.createGlue());
        bar.add(roomLabel);
        bar.add(Box.createGlue());
        bar.add(finishBtn);
        bar.add(exitBtn);

        canvas  = new Canvas<>(state, frontend);
        mainColumn.add(canvas);

        var dragListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                backend.liftObject(state, e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                backend.dropObject(state);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                backend.moveObject(state, e.getX(), e.getY());
            }
        };
        canvas.addMouseListener(dragListener);
        canvas.addMouseMotionListener(dragListener);
    }

    @Override
    protected void drawFrame(BuildModeState state, Backend<BuildModeState> backend) {
        state.setWidth(canvas.getWidth());
        state.setHeight(canvas.getHeight());
        backend.updateState(state);
        canvas.repaint();
    }


    private void addInsertMenuItem(String text, JPopupMenu insertMenu, BuildModeBackend backend, BuildModeState state, ObjectType type) {
        var menuItem = new JMenuItem(text);
        menuItem.addActionListener(e -> backend.insertRandomObject(state, type));
        insertMenu.add(menuItem);
    }
}
