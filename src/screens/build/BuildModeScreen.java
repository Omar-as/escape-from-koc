package screens.build;

import managers.ScreenManager;
import models.BuildModeState;
import models.objects.ObjectType;
import screens.AnimatedScreen;
import screens.Backend;
import screens.Canvas;
import screens.ScreenFactory;
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
        addInsertMenuItem("Bookshelf", insertMenu, backend, state, ObjectType.BOOKSHELF);
        addInsertMenuItem("Desk", insertMenu, backend, state, ObjectType.DESK);
        addInsertMenuItem("Printer", insertMenu, backend, state, ObjectType.PRINTER);

        var insertBtn = new JButton("Insert");
        insertBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                insertMenu.show(insertBtn, insertBtn.getX(), insertBtn.getY() + insertBtn.getHeight());
            }
        });

        var nextBtn = new JButton("Next Room");
        nextBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int currentRoom = state.getCurrentRoom();
                state.setCurrentRoom(currentRoom != state.getRooms().length - 1 ? currentRoom + 1 : 0);
                roomLabel.setText(state.getRooms()[state.getCurrentRoom()].getName());
            }
        });
        var randomizeRoomBtn = new JButton("Randomize Room");
        randomizeRoomBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backend.fillOneRoomRandomly(state, state.getRooms()[state.getCurrentRoom()]);

            }
        });
        var randomizeAllBtn = new JButton("Randomize All");
        randomizeAllBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backend.fillAllRoomsRandomly(state);
            }
        });
        var prevBtn = new JButton("Prev Room");
        prevBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int currentRoom = state.getCurrentRoom();
                state.setCurrentRoom(currentRoom != 0 ? currentRoom - 1 : state.getRooms().length - 1);
                roomLabel.setText(state.getRooms()[state.getCurrentRoom()].getName());
            }
        });

        var finishBtn = new JButton("Finish");
        finishBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (var room : state.getRooms()) {
                    if (room.getObjects().size() < room.getMinObjects()) {
                        ScreenManager.getInstance().showDialog("%s should have at least %d objects.".formatted(room.getName(), room.getMinObjects()));
                        return;
                    }
                }
                ScreenManager.getInstance().setScreen(ScreenFactory.getRunModeScreen(state));
            }
        });

        var exitBtn = new JButton("Exit");
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScreenManager.getInstance().setScreen(ScreenFactory.getMainScreen());
            }
        });

        bar.add(insertBtn);
        bar.add(prevBtn);
        bar.add(nextBtn);
        bar.add(randomizeRoomBtn);
        bar.add(randomizeAllBtn);
        bar.add(Box.createGlue());
        bar.add(roomLabel);
        bar.add(Box.createGlue());
        bar.add(finishBtn);
        bar.add(exitBtn);

        canvas = new Canvas<>(state, frontend);
        mainColumn.add(canvas);

        var dragListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                backend.liftObject(state, e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                backend.dropObject(state);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
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
        menuItem.addActionListener(e -> backend.insertRandomObjectInCurrentRoom(state, type));
        insertMenu.add(menuItem);
    }
}
