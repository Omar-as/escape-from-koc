package ui;

import javax.swing.*;
import java.awt.*;

public class BuildModeScreen extends Screen {
    // Labels
    public static JLabel usernameLabel;

    public BuildModeScreen() {

        usernameLabel = new JLabel("XYZNNN: ");

        Box mainBox = new Box(BoxLayout.Y_AXIS);
        Box usernameBox = new Box(BoxLayout.X_AXIS);

        this.setLayout(new GridBagLayout());
        usernameBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(mainBox);
        usernameBox.add(usernameLabel);
        mainBox.add(usernameBox);
    }
}
