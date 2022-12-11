package ui.screens;

import ui.Screen;
import ui.ScreenManager;

import javax.swing.*;

import java.awt.*;

import java.io.*;
import java.util.Scanner;

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
            var check = false;
            var match = "%s %s".formatted(usernameField.getText(), passwordField.getText());
            var accountScanner = getAccountsFile();
            while (accountScanner.hasNextLine()) {
                var data = accountScanner.nextLine();
                check = data.equals(match);
                if (check) break;
            }
            accountScanner.close();
            if (check) {
                ScreenManager.getInstance().setScreen(new MainMenu());
            } else {
                feedbackLabel.setText("Incorrect username or password.");
                feedbackLabel.setForeground(Color.RED);
            }
        });
        signUpButton.addActionListener(a -> {
                var check = false;
                var username = usernameField.getText();
                var password = passwordField.getText();
                var accountScanner = getAccountsFile();
                while (accountScanner.hasNextLine()) {
                    var data = accountScanner.nextLine().split(" ")[0];
                    check = data.equals(username);
                    if(check) break;
                }
                accountScanner.close();

                if(check) {
                    feedbackLabel.setText("User already has an account.");
                    feedbackLabel.setForeground(Color.RED);
                } else if (username.isEmpty() || password.isEmpty()) {
                    feedbackLabel.setText("Missing username or password.");
                    feedbackLabel.setForeground(Color.RED);
                } else {
                    addNewAccount(username, password);
                    feedbackLabel.setText("Account created successfully!");
                    feedbackLabel.setForeground(Color.GREEN);
                }
        });
        form.add(signInButton);
        form.add(signUpButton);

        form.add(feedbackLabel);

        mainColumn.add(form);
    }

    private void createAccountsFileIfNotFound() {
        // TODO: Remove magic paths
        var configDir = new File("%s/.config/escapefromkoc/".formatted(System.getProperty("user.home")));
        configDir.mkdir();
        var accountsFile = new File("%s/accounts.txt".formatted(configDir));
        try {
            accountsFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Scanner getAccountsFile() {
        createAccountsFileIfNotFound();
        // TODO: Remove magic paths
        var configDir = new File("%s/.config/escapefromkoc/".formatted(System.getProperty("user.home")));
        var accountsFile = new File("%s/accounts.txt".formatted(configDir));

        try {
            return new Scanner(accountsFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void addNewAccount(String username, String password) {
        // TODO: Remove magic paths
        var configDir = new File("%s/.config/escapefromkoc/".formatted(System.getProperty("user.home")));
        var accountsFile = new File("%s/accounts.txt".formatted(configDir));
        try {
            var f = new FileWriter(accountsFile, true);
            var b = new BufferedWriter(f);
            var p = new PrintWriter(b);

            p.println("%s %s".formatted(username, password));

            p.close();
            b.close();
            f.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
