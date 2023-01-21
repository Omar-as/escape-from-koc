package managers;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Key Manager
 * <p>
 * Handles keyboard key presses.
 * <p>
 * Patterns:
 * 1. Low Coupling : This class does not have unnecessary dependencies on other classes.
 * 2. High Cohesion: This class has one responsibility and does it well.
 * 2. Singleton    : Only one instance of this class can be created.
 */
public class KeyManager {
    private static KeyManager instance = null;
    private final HashMap<Integer, Boolean> keyStates;

    private KeyManager() {
        keyStates = new HashMap<>();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(ke -> {
            synchronized (KeyManager.class) {
                if (ke.getID() == KeyEvent.KEY_PRESSED) keyStates.put(ke.getKeyCode(), true);
                else if (ke.getID() == KeyEvent.KEY_RELEASED) keyStates.put(ke.getKeyCode(), false);
                return false;
            }
        });
    }

    public static KeyManager getInstance() {
        if (instance == null) instance = new KeyManager();
        return instance;
    }

    public boolean isKeyPressed(char key) {
        synchronized (KeyManager.class) {
            return keyStates.getOrDefault(KeyEvent.getExtendedKeyCodeForChar(key), false);
        }
    }

    public boolean isKeyPressed(int keyCode) {
        synchronized (KeyManager.class) {
            return keyStates.getOrDefault(keyCode, false);
        }
    }

    // Method for testing
    // Simulates key presses
    public void setKeyPressed(int keyCode, boolean isPressed) {
        synchronized (KeyManager.class) {
            keyStates.put(keyCode, isPressed);
        }
    }
}
