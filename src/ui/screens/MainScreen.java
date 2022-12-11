package ui.screens;

import control.BuildModeBackend;
import models.BuildModeState;
import ui.Screen;
import ui.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.stream.Stream;

public class MainScreen extends Screen {
    public MainScreen() {
        this.setLayout(new GridBagLayout());

        var mainColumn = Box.createVerticalBox();
        this.add(mainColumn);

        var title = new JLabel("Main Menu");
        // TODO: Remove magic numbers
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setAlignmentX(CENTER_ALIGNMENT);
        mainColumn.add(title);

        var mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(0, 1));
        addMainMenuButton(mainMenu, "Play Game", e -> {
            // Launch Build Mode
            var backend = new BuildModeBackend();
            var screen  = new BuildModeScreen(new BuildModeState(Stream.of(
                    "Student Center",
                    "CASE Building",
                    "SOS Building",
                    "SCI Building",
                    "ENG Building",
                    "SNA Building"
            )), backend);
            ScreenManager.getInstance().setScreen(screen);
        });
        addMainMenuButton(mainMenu, "Credits", e -> {});
        addMainMenuButton(mainMenu, "Help", e -> {
            ScreenManager.getInstance().setScreen(new HelpScreen());
        });
        addMainMenuButton(mainMenu, "Game History", e -> {});
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