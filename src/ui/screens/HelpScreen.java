package ui.screens;

import ui.Screen;
import ui.ScreenFactory;
import ui.ScreenManager;
import ui.ScreenType;
import utils.Constants;
import utils.ThemeManager;

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
                                
                • You can use the arrow keys to move. You can go east, west, north and south. You can not go through walls. You can open the exit door of a building if you find the keys
                • Your aim is to travel the building in the given order: CASE building, SOS building, SCI building, ENG building and SNA building finding keys one by one
                • To find the keys, you use a left click on the object on the building. If a key is present it appears for a second and the door is unlocked.
                • You need to be next to objects to check if key exists
                
                                
                Ringcraft Attributes
                                
                • The ringcraft attribute “Stamina” governs a boxer’s capacity to muster energy. Boxers with superior Stamina expend less energy when punching, and recover energy faster when resting during and between rounds.
                • The ringcraft attribute “Dexterity” governs a boxer’s hand/eye coordination. Boxers with superior Dexterity can utilise their full strength with their non-dominant arm, and are better at coupling together punches with an economy of effort. They can coordinate swifter counterpunches, and are adept at initiating clinches.
                • The ringcraft attribute “Agility” governs a boxer’s upper-body movement and suppleness. Boxers with superior Agility are confident with a proactive defence when boxing off the front foot: bobbing, weaving, slipping and rolling incoming shots at all ranges. They are defensively slippery, making them difficult to grasp onto in clinches.
                """;

        // Initialize components
        var titleLabel = new JLabel(ThemeManager.getTitle("Help"));
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


    public static void main(String[] args) {
        ScreenManager.getInstance().launch(
                Constants.FRAME_WIDTH,
                Constants.FRAME_HEIGHT,
                Constants.FRAME_TITLE,
                ScreenFactory.getScreen(ScreenType.HELP)
        );
    }
}
