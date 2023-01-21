package screens.end;

import managers.ThemeManager;
import screens.Screen;
import screens.main.MainMenuController;

import javax.swing.*;
import java.awt.*;

/**
 * Game End Screen
 * Display result of a game when it finishes.
 */
public class GameEndScreen extends Screen {
    public GameEndScreen(boolean didWin) {
        this.setLayout(new GridBagLayout());

        var mainColumn = new JPanel();
        mainColumn.setLayout(new GridLayout(0, 1));

        var title = new JLabel(ThemeManager.getTitle(didWin ? "You Won!" : "You Lost :("));
        title.setAlignmentX(CENTER_ALIGNMENT);
        mainColumn.add(title);

        var backButton = new JButton("Main Menu");
        backButton.addActionListener(MainMenuController::handleBackToMain);
        mainColumn.add(backButton);

        this.add(mainColumn);
    }
}
