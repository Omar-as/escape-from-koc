package ui.screens;

import ui.Screen;
import ui.ScreenFactory;
import ui.ScreenManager;
import ui.ScreenType;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

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
        var signUpButton = new JButton("Sign Up");

        signInButton.addActionListener(event -> {
            var username = usernameField.getText();
            var password = passwordField.getPassword();

            var isLoginSuccessful = AccountManager.isValidAuthInput(username, password);

            var screenManager = ScreenManager.getInstance();
            if (isLoginSuccessful) {
                AccountManager.setUsername(username);
                screenManager.setScreen(ScreenFactory.getScreen(ScreenType.MAIN));
            }
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

        var dataStoreLabel   = new JLabel("Data Store");
        var dataStoreSelect  = new JComboBox(DataStoreManager.DataStoreType.values());
        dataStoreSelect.setSelectedIndex(0);
        dataStoreSelect.addActionListener(e -> DataStoreManager.setDataStoreType((DataStoreManager.DataStoreType) dataStoreSelect.getSelectedItem()));

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
