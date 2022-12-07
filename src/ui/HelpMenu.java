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
    public static JLabel usernameLabel;
    public static JLabel passwordLabel;

    public static JLabel resultLabel;

    /*public static JLabel titleLabel;*/

    // fields
    public static JTextField usernameField;
    public static JPasswordField passwordField;

    // buttons
    public static JButton signUpButton;
    public static JButton signInButton;
    public HelpMenu() {
        String title = "Help Menu";
        TitledBorder border = BorderFactory.createTitledBorder(title);

        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        border.setTitleFont(new Font("Arial", Font.BOLD, 40));
        this.setBorder(border);


        usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField(16);

        passwordLabel = new JLabel("Password:  ");
        passwordField = new JPasswordField(16);

        resultLabel = new JLabel("");

        signInButton = new JButton("Sign out");
        signUpButton = new JButton("Sign Up");

        // field size
        usernameField.setMaximumSize(new Dimension(500, 50));
        passwordField.setMaximumSize(new Dimension(500, 50));

        // boxes
        Box mainBox = new Box(BoxLayout.Y_AXIS);
        Box loginBox = new Box(BoxLayout.Y_AXIS);
        Box usernameBox = new Box(BoxLayout.X_AXIS);
        Box passwordBox = new Box(BoxLayout.X_AXIS);

        Box textBox = new Box(BoxLayout.X_AXIS);
        Box buttonBox = new Box(BoxLayout.X_AXIS);

        this.setLayout(new GridBagLayout());
        this.add(mainBox);

        // login box
        mainBox.add(loginBox);
        mainBox.add(textBox);
        mainBox.add(buttonBox);
        loginBox.add(usernameBox);
        loginBox.add(passwordBox);
        usernameBox.add(usernameLabel);
        usernameBox.add(usernameField);
        passwordBox.add(passwordLabel);
        passwordBox.add(passwordField);

        // Text box
        textBox.add(resultLabel);

        // button box
        buttonBox.add(signInButton);
        buttonBox.add(signUpButton);

        // signin button
        signInButton.setBackground(Color.GRAY);
        signInButton.setForeground(Color.BLACK);
        signInButton.setFocusable(false);
        signInButton.setVisible(true);

        // signup button

        signInButton.setBackground(Color.GRAY);
        signInButton.setForeground(Color.BLACK);
        signInButton.setFocusable(false);
        signInButton.setVisible(true);


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

