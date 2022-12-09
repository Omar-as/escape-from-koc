package ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class GraphicsManager {

    public static Image buffImages(String name, int width, int height) {
        try {
            var buffImage = ImageIO.read(new File("assets/" + name + ".png"));
            var image = buffImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            return image;
        } catch(IOException e) {
            System.exit(-1);
            return null;
        }
    }
}
