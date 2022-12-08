package ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class GraphicsManager {

    private static GraphicsManager instance = null;

    public static GraphicsManager getInstance() {
        if (instance == null) instance = new GraphicsManager();
        return instance;
    }

    private HashMap<String, Image> imageCache;

    private GraphicsManager() {

    }

    public Image buffImages(String name, int width, int height) {
        try {
            if (imageCache.containsKey(name)) return imageCache.get(name);
            var buffImage = ImageIO.read(new File("assets/" + name + ".png"));
            var image = buffImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            imageCache.put(name, image);
            return image;
        } catch(Exception e) {
            System.exit(-1);
            return null;
        }
    }
}
