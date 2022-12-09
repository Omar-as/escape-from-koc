package ui;

import utils.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GraphicsManager {
    private static GraphicsManager instance = null;

    public static GraphicsManager getInstance() {
        if (instance == null) instance = new GraphicsManager();
        return instance;
    }

    private final Image[] cache;

    private GraphicsManager() {
        cache = new Image[Constants.ASSET_NAMES.length];
    }

    public Image getImage(int idx, int width, int height) {
        try {
            if (cache[idx] == null) {
                var bufferedImage = ImageIO.read(new File("assets/%s.png".formatted(Constants.ASSET_NAMES[idx])));
                var image  = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
                cache[idx] = image;
            }
            return cache[idx];
        } catch(IOException e) {
            System.exit(-1);
            return null;
        }
    }
}
