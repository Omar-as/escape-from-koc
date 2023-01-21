package screens.credits;

import managers.ThemeManager;
import screens.Screen;
import screens.main.MainMenuController;

import javax.swing.*;
import java.awt.*;

/**
 * Credits Screen
 * Display credits in a nice monospaced font.
 */
public class CreditsScreen extends Screen {
    public CreditsScreen() {
        this.setLayout(new GridBagLayout());

        var mainColumn = new JPanel();
        mainColumn.setLayout(new GridLayout(0, 1));

        var title = new JLabel(ThemeManager.getTitle("Credits"));
        title.setAlignmentX(CENTER_ALIGNMENT);
        mainColumn.add(title);

        var label001 = new JLabel("labmem001 -> Ameer Taweel");
        var label002 = new JLabel("labmem002 -> Ahmad Jareer");
        var label003 = new JLabel("labmem003 -> Omar  Al-Asaad");
        var label004 = new JLabel("labmem004 -> Orhan Yıldırım");
        var label005 = new JLabel("labmem005 -> Arda  Poyraz");

        label001.setFont(ThemeManager.getMonospacedFont());
        label002.setFont(ThemeManager.getMonospacedFont());
        label003.setFont(ThemeManager.getMonospacedFont());
        label004.setFont(ThemeManager.getMonospacedFont());
        label005.setFont(ThemeManager.getMonospacedFont());

        mainColumn.add(label001);
        mainColumn.add(label002);
        mainColumn.add(label003);
        mainColumn.add(label004);
        mainColumn.add(label005);

        var backButton = new JButton("Main Menu");
        backButton.addActionListener(MainMenuController::handleBackToMain);
        mainColumn.add(backButton);

        this.add(mainColumn);
    }
}