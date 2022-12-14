package ui.screens;

import ui.Screen;
import ui.ScreenFactory;
import ui.ScreenManager;
import ui.ScreenType;
import utils.ThemeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
        addMainMenuButton(mainMenu, "Play Game", e -> ScreenManager.getInstance().setScreen(ScreenFactory.getScreen(ScreenType.BUILD_MODE)));
        addMainMenuButton(mainMenu, "Credits", e -> {
        });
        addMainMenuButton(mainMenu, "Help", e -> ScreenManager.getInstance().setScreen(ScreenFactory.getScreen(ScreenType.HELP)));
        addMainMenuButton(mainMenu, "Game History", e -> {
        });
        mainColumn.add(mainMenu);
    }

    private void addMainMenuButton(JComponent mainMenu, String text, ActionListener listener) {
        var btn = new JButton(text);
        // TODO: Set uniform style
        // btn.setBackground(Color.GRAY);
        // btn.setForeground(Color.BLACK);
        btn.addActionListener(listener);
        mainMenu.add(btn);
    }
}