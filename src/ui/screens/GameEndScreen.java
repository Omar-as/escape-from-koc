package ui.screens;

import ui.Screen;

import javax.swing.*;
import java.awt.*;

public class GameEndScreen extends Screen {
    public GameEndScreen(boolean didWin) {
        this.setLayout(new GridBagLayout());

        var resultLabel = new JLabel(didWin ? "You Won!" : "You Lost :(");
        // TODO: Remove magic numbers
        resultLabel.setFont(new Font("Arial", Font.BOLD, 40));
        resultLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(resultLabel);
    }
}
