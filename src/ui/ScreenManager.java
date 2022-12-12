package ui;

import javax.swing.*;

public class ScreenManager {
    private static ScreenManager instance = null;
    private JFrame frame;

    private ScreenManager() {
    }

    public static ScreenManager getInstance() {
        if (instance == null) instance = new ScreenManager();
        return instance;
    }

    public void launch(int width, int height, String title, Screen initialScreen) {
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.setFocusable(false);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Launch JFrame in the center of the screen
        frame.setLocationRelativeTo(null);
        setScreen(initialScreen);
        frame.setVisible(true);
    }

    public void setScreen(Screen screen) {
        frame.setContentPane(screen);
        frame.revalidate();
    }

    public void showErrorDialog(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public void showInformationDialog(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "INFO", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showDialog(String msg) {
        JOptionPane.showMessageDialog(frame, msg);
    }
}
