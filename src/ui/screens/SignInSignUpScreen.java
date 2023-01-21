package ui.screens;

import controllers.SignInSignUpController;
import ui.Screen;
import managers.DataStoreManager;
import utils.ThemeManager;

import javax.swing.*;
import java.awt.*;

/**
 * Sign In / Sign Up Screen
 * <p>
 * Patterns:
 * 1. Observer  : Listen for events of interest, and act when they happen.
 * 2. Controller: Pass control to a controller instead of handling events in the UI.
 * <p>
 * Model-View Separation:
 * No logic implemented in the UI.
 */
public class SignInSignUpScreen extends Screen {
    public SignInSignUpScreen() {
        this.setLayout(new GridBagLayout());

        var mainColumn = Box.createVerticalBox();
        this.add(mainColumn);

        var title = new JLabel(ThemeManager.getTitle("Sign In / Sign Up"));
        title.setAlignmentX(CENTER_ALIGNMENT);
        mainColumn.add(title);

        var form = new JPanel();
        form.setLayout(new GridLayout(0, 1));
        mainColumn.add(form);

        var usernameLabel = new JLabel("Username");
        var usernameField = new JTextField();
        var passwordLabel = new JLabel("Password");
        var passwordField = new JPasswordField();

        usernameLabel.setLabelFor(usernameField);
        passwordLabel.setLabelFor(passwordField);

        var signInButton = new JButton("Sign In");
        // Observer Pattern
        signInButton.addActionListener(event -> {
            var username = usernameField.getText();
            var password = passwordField.getPassword();
            // Controller Pattern
            SignInSignUpController.handleSignInTry(username, password);
        });

        var signUpButton = new JButton("Sign Up");
        // Observer Pattern
        signUpButton.addActionListener(a -> {
            var username = usernameField.getText();
            var password = passwordField.getPassword();
            // Controller Pattern
            SignInSignUpController.handleSignUpTry(username, password);
        });

        var dataStoreLabel = new JLabel("Data Store");
        var dataStoreSelect = new JComboBox(DataStoreManager.DataStoreType.values());
        dataStoreSelect.setSelectedIndex(0);
        // Observer Pattern
        dataStoreSelect.addActionListener(e -> {
            // Controller Pattern
            SignInSignUpController.handleDataStoreChoiceChange((DataStoreManager.DataStoreType) dataStoreSelect.getSelectedItem());
        });

        form.add(usernameLabel);
        form.add(usernameField);
        form.add(passwordLabel);
        form.add(passwordField);
        form.add(signInButton);
        form.add(signUpButton);
        form.add(dataStoreLabel);
        form.add(dataStoreSelect);
    }
}
