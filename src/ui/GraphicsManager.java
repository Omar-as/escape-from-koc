package ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GraphicsManager {
    public Image getImage(String name, int width, int height) {
        try {
            var bufferedImage = ImageIO.read(new File("assets/%s.png".formatted(name)));
            return bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        } catch(IOException e) {
            System.exit(-1);
            return null;
        }
    }
}
