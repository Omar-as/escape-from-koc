package managers;

import screens.Screen;

import javax.swing.*;

/**
 * Screen Manager
 * <p>
 * Handles changing screens, and provides useful dialog utilities.
 * <p>
 * Patterns:
 * 1. Low Coupling : This class does not have unnecessary dependencies on other classes.
 * 2. High Cohesion: This class has one responsibility and does it well.
 * 2. Singleton    : Only one instance of this class can be created.
 */
public class ScreenManager {
    private static ScreenManager instance = null;
    private JFrame frame;

    private ScreenManager() {
        try {
            // Make program look like a native application
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    public static ScreenManager getInstance() {
        if (instance == null) instance = new ScreenManager();
        return instance;
    }

    public void launch(int width, int height, String title, Screen initialScreen) {
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle(title);
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
