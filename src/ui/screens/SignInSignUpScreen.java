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
        mainColumn.add(form);

        // TODO: Remove magic numbers
        var usernameLabel = new JLabel("Username");
        var usernameField = new JTextField(16);
        var passwordLabel = new JLabel("Password");
        var passwordField = new JPasswordField(16);

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

            var isLoginSuccessful = AccountManager.isValidAuthInput(username, password);

            var screenManager = ScreenManager.getInstance();
            if (isLoginSuccessful) screenManager.setScreen(ScreenFactory.getScreen(ScreenType.MAIN));
            else screenManager.showErrorDialog("Incorrect username or password.");
        });
        signUpButton.addActionListener(a -> {
            var username = usernameField.getText();
            var password = passwordField.getPassword();

            var isFieldMissing   = username.isEmpty() || password.length == 0;
            var doesAccountExist = AccountManager.doesAccountExist(username);

            if (!isFieldMissing && !doesAccountExist) AccountManager.createNewAccount(username, password);

            var screenManager = ScreenManager.getInstance();
            if (isFieldMissing) screenManager.showErrorDialog("Missing username or password.");
            else if (doesAccountExist) screenManager.showErrorDialog("User already has an account.");
            else screenManager.showInformationDialog("Account created successfully!");
        });

        form.add(usernameLabel);
        form.add(usernameField);
        form.add(passwordLabel);
        form.add(passwordField);
        form.add(signInButton);
        form.add(signUpButton);
    }
}
