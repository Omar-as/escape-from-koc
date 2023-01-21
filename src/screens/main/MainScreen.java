package screens.main;

import managers.ThemeManager;
import screens.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Main Menu Screen
 * <p>
 * Patterns:
 * 1. Observer  : Listen for events of interest, and act when they happen.
 * 2. Controller: Pass control to a controller instead of handling events in the UI.
 * <p>
 * Model-View Separation:
 * No logic implemented in the UI.
 */
public class MainScreen extends Screen {
    public MainScreen() {
        this.setLayout(new GridBagLayout());

        var mainColumn = Box.createVerticalBox();
        this.add(mainColumn);

        var title = new JLabel(ThemeManager.getTitle("Main Menu"));
        title.setAlignmentX(CENTER_ALIGNMENT);
        mainColumn.add(title);

        var mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(0, 1));

        // Observer + Controller Pattern
        addMainMenuButton(mainMenu, "New Game", MainMenuController::handleNewGameBtn);
        addMainMenuButton(mainMenu, "Load Game", MainMenuController::handleLoadGameBtn);
        addMainMenuButton(mainMenu, "Scoreboard", MainMenuController::handleScoreboardBtn);
        addMainMenuButton(mainMenu, "Credits", MainMenuController::handleCreditsBtn);
        addMainMenuButton(mainMenu, "Help", MainMenuController::handleHelpBtn);

        mainColumn.add(mainMenu);
    }

    private void addMainMenuButton(JComponent mainMenu, String text, ActionListener listener) {
        var btn = new JButton(text);
        btn.addActionListener(listener);
        mainMenu.add(btn);
    }
}