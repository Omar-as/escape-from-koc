package ui;

import utils.Asset;

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
        cache = new Image[Asset.values().length];
    }

    public Image getImage(Asset asset, int width, int height) {
        try {
            int idx = asset.ordinal();
            if (cache[idx] == null) {
                // TODO: Remove magic path
                var bufferedImage = ImageIO.read(new File("assets/%s.png".formatted(asset.name)));
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
