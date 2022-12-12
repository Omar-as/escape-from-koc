package ui.screens;

import ui.Screen;

import javax.swing.*;
import java.awt.*;

public class HelpScreen extends Screen {
    public HelpScreen() {
        this.setLayout(new GridBagLayout());

        var mainColumn = Box.createVerticalBox();
        this.add(mainColumn);

        // TODO: Fix help text
        var helpText = """
                Game Navigation
                                
                • On many in-game screens, tapping the date in the top right corner will return you to the top of the current stack of screens.
                • Many instances of bold text can be tapped to take you to a corresponding detail screen. For instance tapping on the Fight Record on a boxer’s profile will display that boxer’s bout history.
                • You can mute any sound effects from within the OPTIONS screen, located from the main menu.
                • When viewing the profile of a boxer tapping on their name will allow you to edit their name, nickname and nationality (they cannot belong to another player’s gym).
                • When editing a boxer’s name and nationality, if you’d prefer them not to have a nickname fill the textbox with a single space.
                • To give yourself more/less of a challenge you can adjust many settings governing game difficulty from within the OPTIONS screen, located from the main menu.
                                
                Ringcraft Attributes
                                
                • The ringcraft attribute “Stamina” governs a boxer’s capacity to muster energy. Boxers with superior Stamina expend less energy when punching, and recover energy faster when resting during and between rounds.
                • The ringcraft attribute “Dexterity” governs a boxer’s hand/eye coordination. Boxers with superior Dexterity can utilise their full strength with their non-dominant arm, and are better at coupling together punches with an economy of effort. They can coordinate swifter counterpunches, and are adept at initiating clinches.
                • The ringcraft attribute “Agility” governs a boxer’s upper-body movement and suppleness. Boxers with superior Agility are confident with a proactive defence when boxing off the front foot: bobbing, weaving, slipping and rolling incoming shots at all ranges. They are defensively slippery, making them difficult to grasp onto in clinches.
                """;

        // Initialize components
        var titleLabel = new JLabel("Help");
        // TODO: Remove magic numbers
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        mainColumn.add(titleLabel);

        var helpTextArea = new JTextArea(helpText);
        helpTextArea.setEditable(false);
        // TODO: Remove magic numbers
        helpTextArea.append(helpText);
        helpTextArea.setFont(new Font("Arial", Font.PLAIN, 10));
        mainColumn.add(helpTextArea);

        var backButton = new JButton("Back");
        // TODO: Remove magic numbers
        backButton.setPreferredSize(new Dimension(20, 60));
        backButton.setForeground(Color.BLUE);
        mainColumn.add(backButton);
    }
}
