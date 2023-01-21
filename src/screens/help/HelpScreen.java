package screens.help;

import managers.ThemeManager;
import screens.Screen;
import screens.main.MainMenuController;
import utils.Constants;

import javax.swing.*;
import java.awt.*;

public class HelpScreen extends Screen {
    public HelpScreen() {
        this.setLayout(new GridBagLayout());

        var mainColumn = Box.createVerticalBox();

        var helpText = """
                AIM
                                
                • You are in KOC University Campus.
                • You need to find a sequence of keys in the campus buildings.
                • The game starts when you enter one of the buildings.
                • You need to be next to objects to check if key exists
                • There is a timeout until you can find the keys
                • You can find hints around the campus map
                • When you find the key the building will be marked as completed
                • You fail if you can not find the key in the given time limit
                • If you manage to find all the keys you win the game !!!!!
                                
                GAME NAVIGATION
                                
                • You can use the arrow keys to move. You can go east, west, north and south. You can not go through walls. You can open the exit door of a building if you find the keys
                • Your aim is to travel the building in the given order: CASE building, SOS building, SCI building, ENG building and SNA building finding keys one by one
                • To find the keys, you use a left click on the object on the building. If a key is present it appears for a second and the door is unlocked.
                • You need to be next to objects to check if key exists
                                
                POWER-UPS
                                
                • You can use power-ups to escape aliens and find keys easily.
                • The power ups appear randomly in every 12 seconds in a random location.
                • Power-up disappear if you not collect them in 6 seconds. To collect power-ups you can right click with mouse on them.
                • You are not required to be next to power-ups to collect them.
                • Other than extra time and extra life power-ups, power-ups can be stored in your bag for later use.
                • Extra time power-up adds extra time to your timer.
                              
                EXTRA TIME POWER-UP
                    
                • Extra Life power-up adds one extra life to your life. Like extra time power-up the addition of extra life is automatic.
                                
                • Hint power-up gives information about the location of the key to you. Once you collect it is held in your bag.
                • You can use it as soon as you get it or you can save it for next levels.
                • You can use Hint power-up by pressing H on your keyboard. Then, the key is highlighted on the map and you have 1 second before it disappears.
                                
                PROTECTION VEST
                                
                • Protection Vest power-up protects you from being shot by the shooter alien.
                • To use Protection Vest power-up you can use P on your keyboard.
                • You can use it as soon as you get it or you can save it for next levels. When you activate it its protection last 20 seconds
                                
                PLASTIC BOTTLE
                                
                • Plastic Bottle power-up is used to fool blind alien.
                • To use the power-up click B button and then press one of the buttons A, D, W, or X
                • A: West, D: East, W: north, X: South.
                                
                ALIENS
                                
                • While walking around you encounter some aliens who try to kill you or prevent you from finding the keys
                • All aliens appear randomly in the buildings every 10 seconds and the type of the alien appearing is selected randomly.
                • Alien stays in the current building even if you find the key and exit.
                                
                SHOOTER ALIEN
                                
                • Shooter alien appears in a random location in the building.\s
                • If you are closer than 4 squares to the shooter alien 1 of your lives. At the beginning of the game you have 3 lives and you can collect extra lives during the game.
                • If you wear a protection vest you can get close to shooter alien without losing your lives.
                                
                BLIND ALIEN
                 
                • Blind alien tries to kill you. In order to kill you it should be right next to you.
                • It is sensitive to voices and you can fool the blind alien by throwing plastic bottles to the opposite direction where you want to go.
                • Protection vest does not protect you from the blind alien.
                                
                BUILDING MODE
                                
                • Game starts with the building mode. In building mode you design the inside of the buildings by placing objects with minimum criteria for each building.
                • There must be at least 5 objects in the Student Center.
                • There must be at least 7 objects in the CASE building.
                • There must be at least 10 objects in the SOS building.
                • There must be at least 14 objects in the SCI building.
                • There must be at least 19 objects in the ENG building.
                • There must be at least 25 objects in the SNA building.
                                
                TIME-WASTING ALIEN
                                
                 • Time-wasting alien does not kill you
                 • It changes the location of the key randomly every 5 seconds.
                 • It does not walk around but just resides wherever it appears and you cannot see how it changes the location of the key
                                
                RUNNING MODE
                                
                • When you finish building mode, the game starts running. Your initial location will be random
                • Number of lives you currently have and your remaining time is displayed on the screen.
                • The items you have on your bag are visible on the screen as well.
                                
                PAUSE/RESUME
                                
                • At any point in the game you can pause and resume the game by pressing ESC on your keyboard.
                """;

        // Initialize components
        var titleLabel = new JLabel(ThemeManager.getTitle("Help"));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        mainColumn.add(titleLabel);

        var helpTextArea = new JTextArea();
        helpTextArea.setEditable(false);
        helpTextArea.append(helpText);
        helpTextArea.setFont(ThemeManager.getMonospacedFont());
        // Show help text from the beginning
        helpTextArea.setCaretPosition(0);
        mainColumn.add(helpTextArea);

        // Allow help text to scroll
        var scrollView = new JScrollPane(helpTextArea);
        helpTextArea.setAutoscrolls(true);
        scrollView.setPreferredSize(new Dimension(Constants.HELP_TEXT_WIDTH, Constants.HELP_TEXT_HEIGHT));
        mainColumn.add(scrollView);

        var backButton = new JButton("Back");
        backButton.addActionListener(MainMenuController::handleBackToMain);
        mainColumn.add(backButton);

        this.add(mainColumn);
    }
}
