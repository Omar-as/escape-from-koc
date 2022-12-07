package ui;

import utils.Constants;

import javax.swing.*;

import java.awt.*;

public class MainMenu extends Screen {
    // Labels


    public static JLabel resultLabel;

    // fields


    // buttons
    public static JButton playGameButton;
    public static JButton optionsButton;
    public static JButton creditsButton;
    public static JButton gameHistoryButton;

    public MainMenu() {
        this.setAlignmentX(Component.CENTER_ALIGNMENT);


        resultLabel = new JLabel("");

        playGameButton = new JButton("Play Game");
        creditsButton = new JButton("Credits");

        // panel


        // field size

        // boxes
        Box mainBox = new Box(BoxLayout.Y_AXIS);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        mainBox.setBorder(BorderFactory.createTitledBorder("EscapeFromKoc"));
        this.add(mainBox);



        // Text box

        // button box

        mainBox.add(playGameButton);
        mainBox.add(creditsButton);
        mainBox.setAlignmentX(CENTER_ALIGNMENT);

        // signin button
        playGameButton.setBackground(Color.BLUE);
        playGameButton.setForeground(Color.CYAN);
        playGameButton.setFocusable(false);
        playGameButton.setVisible(true);

        // signup button

        playGameButton.setBackground(Color.GRAY);
        playGameButton.setForeground(Color.BLACK);
        playGameButton.setFocusable(false);
        playGameButton.setVisible(true);



    }

    public static void main(String[] args) {
        ScreenManager.getInstance().launch(
                Constants.FRAME_WIDTH,
                Constants.FRAME_HEIGHT,
                Constants.FRAME_TITLE,
                new MainMenu()
        );
    }
}
