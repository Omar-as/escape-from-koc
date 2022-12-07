package ui;

import control.DummyController;
import control.GameController;
import models.DummyState;
import models.Game;
import models.Player;
import models.PowerUp;
import models.alien.Alien;

import javax.swing.*;

import java.awt.*;

import java.io.*;
import java.util.Scanner;

public class SignInSignUpScreen extends Screen {
    // Labels
    public static JLabel usernameLabel;
    public static JLabel passwordLabel;

    public static JLabel resultLabel;

    // fields
    public static JTextField usernameField;
    public static JPasswordField passwordField;

    // buttons
    public static JButton signUpButton;
    public static JButton signInButton;
    public SignInSignUpScreen() {

        usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField(16);

        passwordLabel = new JLabel("Password:  ");
        passwordField = new JPasswordField(16);

        resultLabel = new JLabel("");

        signInButton = new JButton("Sign In");
        signUpButton = new JButton("Sign Up");

        // panel
        JPanel loginPanel = new JPanel();

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

        signInButton.addActionListener(a -> {
            try {
                File Dir = new File(String.format("%s/.config/escapefromkoc/", System.getProperty("user.home")));

                (new File(String.format("%s/", Dir))).mkdirs();
                (new File(String.format("%s/accounts.txt", Dir))).createNewFile();

                File accounts = new File(String.format("%s/accounts.txt", Dir));

                Scanner myReader = new Scanner(accounts);
                boolean check = false;
                String inputData = String.format("%s %s",usernameField.getText(),passwordField.getText());
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                     check = data.equals(inputData);
                     if(check) break;
                }
                resultLabel.setText(check ? "Correct" : "Incorrect Username or Password");
                resultLabel.setForeground(check ? Color.green : Color.red);
                myReader.close();
                if (check) {
                    ScreenManager.getInstance().setScreen(new RunModeScreen(new Game(false, new Player(5, 0, 0, 0)), new GameController()));
                }
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        });

        signUpButton.addActionListener(a -> {
            try {
                File Dir = new File(String.format("%s/.config/escapefromkoc/", System.getProperty("user.home")));

                (new File(String.format("%s/", Dir))).mkdirs();
                (new File(String.format("%s/accounts.txt", Dir))).createNewFile();

                File accounts = new File(String.format("%s/accounts.txt", Dir));

                Scanner myReader = new Scanner(accounts);
                boolean check = false;
                String username = usernameField.getText();
                String password = passwordField.getText();

                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine().split(" ")[0];
                    check = data.equals(username);
                    if(check) break;
                }

                if(check) {
                    resultLabel.setText("models.User already has an account");
                } else if (username.isEmpty() || password.isEmpty()) {
                    resultLabel.setText("Please enter a username or password");
                } else {
                    FileWriter f = new FileWriter(accounts, true);
                    BufferedWriter b = new BufferedWriter(f);
                    PrintWriter p = new PrintWriter(b);

                    p.println(String.format("%s %s",username,password));
                    p.close();
                    b.close();
                    f.close();
                    resultLabel.setText("Account created successfully!!");
                }

                resultLabel.setForeground((check || username.isEmpty() || password.isEmpty()) ? Color.red : Color.green);
                myReader.close();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        });
    }
}
