package ui.screens;

import ui.Screen;
import ui.ScreenFactory;
import ui.ScreenManager;
import ui.ScreenType;
import utils.AccountManager;

import javax.swing.*;
import java.awt.*;

public class SignInSignUpScreen extends Screen {

    public SignInSignUpScreen() {
        this.setLayout(new GridBagLayout());

        var mainColumn = Box.createVerticalBox();
        this.add(mainColumn);

        var title = new JLabel("Sign In / Sign Up");
        // TODO: Remove magic numbers
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setAlignmentX(CENTER_ALIGNMENT);
        mainColumn.add(title);

        var form = new JPanel();
        form.setLayout(new GridLayout(0, 1));

        // TODO: Remove magic numbers
        var usernameLabel = new JLabel("Username");
        var usernameField = new JTextField(16);
        var passwordLabel = new JLabel("Password");
        var passwordField = new JPasswordField(16);
        form.add(usernameLabel);
        form.add(usernameField);
        form.add(passwordLabel);
        form.add(passwordField);

        var feedbackLabel = new JLabel("");

        var signInButton = new JButton("Sign In");
        var signUpButton = new JButton("Sign Up");
        // TODO: Set uniform style
        // signInButton.setBackground(Color.GRAY);
        // signInButton.setForeground(Color.BLACK);
        // signInButton.setBackground(Color.GRAY);
        // signInButton.setForeground(Color.BLACK);
        signInButton.addActionListener(event -> {
            var username = usernameField.getText();
            var password = passwordField.getPassword();
            if (AccountManager.isValidAuthInput(username, password)) {
                ScreenManager.getInstance().setScreen(ScreenFactory.getScreen(ScreenType.MAIN));
            } else {
                feedbackLabel.setText("Incorrect username or password.");
                feedbackLabel.setForeground(Color.RED);
            }
        });

        signUpButton.addActionListener(a -> {
            var username = usernameField.getText();
            var password = passwordField.getPassword();

            if (username.isEmpty() || password.length == 0) {
                feedbackLabel.setText("Missing username or password.");
                feedbackLabel.setForeground(Color.RED);
            } else if (AccountManager.doesAccountExist(username)) {
                feedbackLabel.setText("User already has an account.");
                feedbackLabel.setForeground(Color.RED);
            } else {
                AccountManager.createNewAccount(username, password);
                feedbackLabel.setText("Account created successfully!");
                feedbackLabel.setForeground(Color.GREEN);
            }
        });

        form.add(signInButton);
        form.add(signUpButton);
        form.add(feedbackLabel);

        mainColumn.add(form);
    }

}
