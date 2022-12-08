package ui;

import control.Backend;
import control.BuildModeBackend;
import models.BuildModeState;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuildModeScreen extends AnimatedScreen<BuildModeState> {
    private BuildModeFrontend frontend;
    private Box mainBox;
    private Box bar;
    private JPanel canvas;

    public BuildModeScreen(BuildModeState state, BuildModeBackend backend) {
        super(state, backend);

        this.setLayout(new GridLayout(0, 1));

        mainBox = Box.createVerticalBox();
        this.add(mainBox);
        frontend = new BuildModeFrontend();

        bar     = Box.createHorizontalBox();
        canvas  = new Canvas<>(state, frontend);

        var label = new JLabel(state.getRooms()[state.getCurrentRoom()].getName());

        mainBox.add(bar);
        mainBox.add(canvas);

        JPopupMenu popupMenu = new JPopupMenu("XX");

        JMenuItem menuItemCreateSpringProject = new JMenuItem("Spring Project");
        popupMenu.add(menuItemCreateSpringProject);

        JMenuItem menuItemCreateHibernateProject = new JMenuItem("Hibernate Project");
        popupMenu.add(menuItemCreateHibernateProject);

        JMenuItem menuItemCreateStrutsProject = new JMenuItem("Struts Project");
        popupMenu.add(menuItemCreateStrutsProject);

        JButton btn = new JButton("Insert");

        btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                popupMenu.show(btn , btn.getX(), btn.getY() + btn.getHeight());
            }
        });

        JButton next = new JButton("Next");

        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int currentRoom = state.getCurrentRoom();
                state.setCurrentRoom(currentRoom != state.getRooms().length - 1 ? currentRoom + 1 : 0);
                label.setText(state.getRooms()[state.getCurrentRoom()].getName());
            }
        });

        JButton prev = new JButton("Prev");

        prev.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int currentRoom = state.getCurrentRoom();
                state.setCurrentRoom(currentRoom != 0 ? currentRoom - 1 : state.getRooms().length - 1);
                label.setText(state.getRooms()[state.getCurrentRoom()].getName());
            }
        });

        bar.add(btn);
        bar.add(next);
        bar.add(prev);
        bar.add(Box.createGlue());
        bar.add(label);
        bar.add(Box.createGlue());

        bar.setMaximumSize(new Dimension(Constants.FRAME_WIDTH, 10));
    }

    @Override
    void drawFrame(BuildModeState state, Backend<BuildModeState> backend) {
        backend.updateState(state);
        canvas.repaint();
    }
}
