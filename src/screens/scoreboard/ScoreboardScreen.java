package screens.scoreboard;

import models.GameData;
import screens.ScreenFactory;
import screens.Screen;
import managers.ScreenManager;
import screens.ScreenType;
import utils.Constants;
import managers.DataStoreManager;
import managers.ThemeManager;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

public class ScoreboardScreen extends Screen {
    public ScoreboardScreen() {
        this.setLayout(new GridBagLayout());

        var mainColumn = Box.createVerticalBox();
        this.add(mainColumn);

        var titleLabel = new JLabel(ThemeManager.getTitle("ScoreBoard"));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        mainColumn.add(titleLabel);

        var gameData = DataStoreManager.getInstance().getCollection(Constants.SCOREBOARD_COLLECTION_NAME, GameData.class);
        var topScores = String.join("\n", gameData.stream()
                .sorted(Comparator.comparingInt(GameData::getTime))
                .map(d -> "%s - %d".formatted(d.getUsername(), d.getTime()))
                .toArray(String[]::new));
        var topScoresField = new JTextArea(topScores);
        mainColumn.add(topScoresField);

        var backButton = new JButton("Back");
        backButton.addActionListener(e -> ScreenManager.getInstance().setScreen(ScreenFactory.getScreen(ScreenType.MAIN)));
        mainColumn.add(backButton);
    }
}
