package ui;

import control.Backend;
import control.BuildModeBackend;
import models.BuildModeState;
import models.objects.Obj;
import models.objects.TrashBin;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

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

        JMenuItem menuItemCreateSpringProject = new JMenuItem("Trash Bin");
        popupMenu.add(menuItemCreateSpringProject);

        menuItemCreateSpringProject.addActionListener(e -> {
            positionRandomly(state, new TrashBin(0, 0));
        });


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

        var listener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                for (var obj : state.getRooms()[state.getCurrentRoom()].getObjects()) {
                    int pressX = e.getX();
                    int pressY = e.getY();
                    int objX   = obj.getPosition().getX();
                    int objY   = obj.getPosition().getY();
                    if (pressX >= objX && pressX <= objX + obj.getWidth() && pressY >= objY && pressY <= objY + obj.getHeight()) {
                        state.setSelectedObject(obj);
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                var selected = state.getSelectedObject();
                if (selected == null) return;
                var x = selected.getPosition().getX();
                var y = selected.getPosition().getY();
                var w = selected.getWidth();
                var h = selected.getHeight();
                if (x < 0 || x + w >= state.getWidth() || y < 0 || y + h >= state.getHeight()) {
                    var objects = state.getRooms()[state.getCurrentRoom()].getObjects();
                    objects.remove(selected);
                }
                state.setSelectedObject(null);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                var selected = state.getSelectedObject();
                if (selected == null) return;
                var objects = state.getRooms()[state.getCurrentRoom()].getObjects();
                var backupPosition = selected.getPosition();
                selected.setPosition(e.getX(), e.getY());
                for (var obj : objects) if (obj != selected && selected.intersects(obj)) {
                    selected.setPosition(backupPosition.getX(), backupPosition.getY());
                    break;
                }
            }
        };
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

    }

    @Override
    void drawFrame(BuildModeState state, Backend<BuildModeState> backend) {
        state.setWidth(canvas.getWidth());
        state.setHeight(canvas.getHeight());
        backend.updateState(state);
        canvas.repaint();
    }

    private void positionRandomly(BuildModeState state, Obj newObj) {
        var objects  = state.getRooms()[state.getCurrentRoom()].getObjects();
        var random   = new Random();
        var done     = false;

        while (!done) {
            int x = random.nextInt(state.getWidth()  - newObj.getWidth());
            int y = random.nextInt(state.getHeight() - newObj.getHeight());

            newObj.setPosition(x, y);

            int collisions = objects.stream()
                    .map(newObj::intersects)
                    .mapToInt(b -> b ? 1 : 0)
                    .sum();

            done = collisions == 0;
        }

        objects.add(newObj);
    }
}
