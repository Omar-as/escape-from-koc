package screens.auth;

import managers.AccountManager;
import managers.DataStoreManager;
import managers.ScreenManager;
import screens.ScreenFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Sign In / Sign Up Controller
 * <p>
 * Handles events originating from the Sign In / Sign Up Screen.
 * <p>
 * Patterns:
 * 1. Controller: Handle events in the responsible classes instead of handling them in the UI.
 * <p>
 * Model-View Separation:
 * No logic implemented in the UI.
 */
public final class SignInSignUpController {
    private SignInSignUpController() {
    }

    public static void handleSignInTry(String username, char[] password) {
        var isLoginSuccessful = AccountManager.isValidAuthInput(username, password);

        var screenManager = ScreenManager.getInstance();
        if (isLoginSuccessful) {
            AccountManager.setUsername(username);
            screenManager.setScreen(ScreenFactory.getMainScreen());
        } else screenManager.showErrorDialog("Incorrect username or password.");
    }

    public static void handleSignUpTry(String username, char[] password) {
        var isFieldMissing = username.isEmpty() || password.length == 0;
        var doesAccountExist = AccountManager.doesAccountExist(username);

        if (!isFieldMissing && !doesAccountExist) AccountManager.createNewAccount(username, password);

        var screenManager = ScreenManager.getInstance();
        if (isFieldMissing) screenManager.showErrorDialog("Missing username or password.");
        else if (doesAccountExist) screenManager.showErrorDialog("User already has an account.");
        else screenManager.showInformationDialog("Account created successfully!");
    }

    public static void handleDataStoreChoiceChange(ActionEvent e) {
        var select = (JComboBox) e.getSource();
        var newDataStoreType = (DataStoreManager.DataStoreType) select.getSelectedItem();
        DataStoreManager.setDataStoreType(newDataStoreType);
    }
}
