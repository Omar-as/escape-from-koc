package ui;

import control.DummyController;
import models.DummyState;
import utils.Constants;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class HelpMenu extends Screen {
    // Labels
    public static JLabel titleLabel;

    // TextArea
    public static JTextArea textArea;

    // Button
    public static JButton backButton;

    public HelpMenu() {
        titleLabel = new JLabel("Help Screen");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.RED);


        // Dummy Help Screen Text
        String helpText = "Game Navigation\n" +
                "\n" +
                "• On many in-game screens, tapping the date in the top right corner will return you to the top of the " +
                "current stack of screens.\n" +
                "\n" +
                "• Many instances of bold text can be tapped to take you to a corresponding detail screen. " +
                "For instance tapping on the Fight Record on a boxer’s profile will display that boxer’s bout history.\n" +
                "\n" +
                "• You can mute any sound effects from within the OPTIONS screen, located from the main menu.\n" +
                "\n" +
                "• When viewing the profile of a boxer tapping on their name will allow you to edit their name, " +
                "nickname and nationality (they cannot belong to another player’s gym).\n" +
                "\n" +
                "• When editing a boxer’s name and nationality, if you’d prefer them not to have a " +
                "nickname fill the textbox with a single space.\n" +
                "\n" +
                "• To give yourself more/less of a challenge you can adjust many settings " +
                "governing game difficulty from within the OPTIONS screen, located from the main menu.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Ringcraft Attributes\n" +
                "\n" +
                "• The ringcraft attribute “Stamina” governs a boxer’s capacity to muster " +
                "energy. Boxers with superior Stamina expend less energy when punching, and " +
                "recover energy faster when resting during and between rounds.\n" +
                "\n" +
                "• The ringcraft attribute “Dexterity” governs a boxer’s hand/eye coordination. " +
                "Boxers with superior Dexterity can utilise their full strength with their " +
                "non-dominant arm, and are better at coupling together punches with an economy of effort. They can coordinate swifter counterpunches, and are adept at initiating clinches.\n" +
                "\n" +
                "• The ringcraft attribute “Agility” governs a boxer’s upper-body movement and " +
                "suppleness. Boxers with superior Agility are confident with a proactive defence " +
                "when boxing off the front foot: bobbing, weaving, slipping and rolling incoming shots " +
                "at all ranges. They are defensively slippery, making them difficult to grasp onto in clinches.";


        textArea = new JTextArea(helpText);
        textArea.append(helpText);
        textArea.setFont(new Font("Arial", Font.PLAIN, 10));

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(20,60));
        backButton.setForeground(Color.BLUE);



        // field size
/*        usernameField.setMaximumSize(new Dimension(500, 50));
        passwordField.setMaximumSize(new Dimension(500, 50));*/


        this.setLayout(new BorderLayout());
   /*     titlePanel.add(titleBox);*/
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(textArea, BorderLayout.CENTER);
        this.add(backButton,BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        ScreenManager.getInstance().launch(
                Constants.FRAME_WIDTH,
                Constants.FRAME_HEIGHT,
                Constants.FRAME_TITLE,
                new HelpMenu()
        );
    }

}

