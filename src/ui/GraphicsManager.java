package ui;

import control.KeyManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsManager {

    private static GraphicsManager instance = null;

    public static GraphicsManager getInstance() {
        if (instance == null) instance = new GraphicsManager();
        return instance;
    }

    public static Image buffImages(String name, int width, int height) throws IOException {
        BufferedImage image_imp = ImageIO.read(new File("assets/" + name + ".png"));
        Image image = image_imp.getScaledInstance(width, height, Image.SCALE_FAST);
        return image;
    }
}
