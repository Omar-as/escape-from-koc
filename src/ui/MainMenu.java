package ui;

import utils.Constants;

import javax.swing.*;

import java.awt.*;

public class MainMenu extends Screen {
    // Labels



    // fields


    // buttons
    public static JButton playGameButton;
    public static JButton optionsButton;
    public static JButton creditsButton;
    public static JButton gameHistoryButton;

    public MainMenu() {
        JLabel title = new JLabel("MAIN MENU");

        title.setFont(new Font("Arial", Font.PLAIN, 40));
        this.add(title);
        title.setAlignmentX(CENTER_ALIGNMENT);




        playGameButton = new JButton("Play Game");
        creditsButton = new JButton("Credits");
        optionsButton = new JButton("Options");
        gameHistoryButton = new JButton("Game History");


        // panel


        // field size

        // boxes
        Box mainBox = new Box(BoxLayout.Y_AXIS);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(mainBox);
        mainBox.setBorder(BorderFactory.createEmptyBorder());
        mainBox.setAlignmentX(CENTER_ALIGNMENT);



        // Text box

        // button box

        mainBox.add(playGameButton);
        mainBox.add(creditsButton);
        mainBox.add(optionsButton);
        mainBox.add(gameHistoryButton);

        // signin button
        playGameButton.setBackground(Color.BLUE);
        playGameButton.setForeground(Color.CYAN);
        playGameButton.setFocusable(false);
        playGameButton.setVisible(true);

        optionsButton.setBackground(Color.BLUE);
        optionsButton.setForeground(Color.CYAN);
        optionsButton.setFocusable(false);
        optionsButton.setVisible(true);

        // signup button

        playGameButton.setBackground(Color.GRAY);
        playGameButton.setForeground(Color.BLACK);
        playGameButton.setFocusable(false);
        playGameButton.setVisible(true);

        gameHistoryButton.setBackground(Color.GRAY);
        gameHistoryButton.setForeground(Color.BLACK);
        gameHistoryButton.setFocusable(false);
        gameHistoryButton.setVisible(true);



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
