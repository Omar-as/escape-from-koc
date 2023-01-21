package screens.scoreboard;

import managers.DataStoreManager;
import managers.ThemeManager;
import models.GameData;
import screens.Screen;
import screens.main.MainMenuController;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Scoreboard Screen
 * Display top scores in a nice list in monospaced font.
 */
public class ScoreboardScreen extends Screen {
    public ScoreboardScreen() {
        this.setLayout(new GridBagLayout());

        var mainColumn = new JPanel();
        mainColumn.setLayout(new GridLayout(0, 1));

        var title = new JLabel(ThemeManager.getTitle("Scoreboard"));
        title.setAlignmentX(CENTER_ALIGNMENT);
        mainColumn.add(title);

        // Get best timings
        var gameData = DataStoreManager.getInstance().getCollection(Constants.SCOREBOARD_COLLECTION_NAME, GameData.class);
        var top = gameData.stream()
                .sorted(Comparator.comparingInt(GameData::getTime))
                .limit(Constants.SCOREBOARD_MAX)
                .toArray(GameData[]::new);

        var maxUsernameLength = Arrays.stream(top)
                .mapToInt(i -> i.getUsername().length())
                .max()
                .orElse(0);

        for (var i : top) {
            var listItem = new JLabel(("%-" + maxUsernameLength + "s -> %d").formatted(i.getUsername(), i.getTime()));
            listItem.setFont(ThemeManager.getMonospacedFont());
            mainColumn.add(listItem);
        }

        var backButton = new JButton("Main Menu");
        backButton.addActionListener(MainMenuController::handleBackToMain);
        mainColumn.add(backButton);

        this.add(mainColumn);
    }
}
