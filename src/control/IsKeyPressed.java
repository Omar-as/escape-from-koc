package control;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class IsKeyPressed {
    private static volatile boolean wPressed = false;
    private static volatile boolean aPressed = false;
    private static volatile boolean sPressed = false;
    private static volatile boolean dPressed = false;
    public static boolean isWPressed() {
        synchronized (IsKeyPressed.class) {
            return wPressed;
        }
    }
    public static boolean isAPressed() {
        synchronized (IsKeyPressed.class) {
            return aPressed;
        }
    }
    public static boolean isSPressed() {
        synchronized (IsKeyPressed.class) {
            return sPressed;
        }
    }
    public static boolean isDPressed() {
        synchronized (IsKeyPressed.class) {
            return dPressed;
        }
    }
    public IsKeyPressed() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (IsKeyPressed.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            if (ke.getKeyCode() == KeyEvent.VK_W) {
                                wPressed = true;
                            }
                            else if (ke.getKeyCode() == KeyEvent.VK_A) {
                                aPressed = true;
                            }
                            else if (ke.getKeyCode() == KeyEvent.VK_S) {
                                sPressed = true;
                            }
                            else if (ke.getKeyCode() == KeyEvent.VK_D) {
                                dPressed = true;
                            }
                            break;

                        case KeyEvent.KEY_RELEASED:
                            if (ke.getKeyCode() == KeyEvent.VK_W) {
                                wPressed = false;
                            }
                            else if (ke.getKeyCode() == KeyEvent.VK_A) {
                                aPressed = false;
                            }
                            else if (ke.getKeyCode() == KeyEvent.VK_S) {
                                sPressed = false;
                            }
                            else if (ke.getKeyCode() == KeyEvent.VK_D) {
                                dPressed = false;
                            }
                            break;
                    }
                    return false;
                }
            }
        });
    }
}

