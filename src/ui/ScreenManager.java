package ui;

import javax.swing.*;

public class ScreenManager {
    private static ScreenManager instance = null;

    public static ScreenManager getInstance() {
        if (instance == null) instance = new ScreenManager();
        return instance;
    }
    private ScreenManager() { }

    private JFrame frame;

    public void launch(int width, int height, String title, Screen initialScreen) {
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.setFocusable(false);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setScreen(initialScreen);
        frame.setVisible(true);
    }

    public void setScreen(Screen screen) {
        frame.setContentPane(screen);
        frame.revalidate();
    }
}
