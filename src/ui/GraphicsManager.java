package ui;

import control.KeyManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class GraphicsManager {

    private static GraphicsManager instance = null;

    public static GraphicsManager getInstance() {
        if (instance == null) instance = new GraphicsManager();
        return instance;
    }

    HashMap<String, Image> imageCache;

    private GraphicsManager() {

    }

    public Image buffImages(String name, int width, int height) throws IOException {
        if(imageCache.containsKey(name)){
            return imageCache.get(name);
        }
        BufferedImage image_imp = ImageIO.read(new File("assets/" + name + ".png"));
        Image image = image_imp.getScaledInstance(width, height, Image.SCALE_FAST);
        imageCache.put(name, image);
        return image;
    }
}
