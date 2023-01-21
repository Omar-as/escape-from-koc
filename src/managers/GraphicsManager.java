package managers;

import utils.Asset;
import utils.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Graphics Manager
 * <p>
 * Handles loading and caching graphics for best performance.
 * <p>
 * Patterns:
 * 1. Low Coupling : This class does not have unnecessary dependencies on other classes.
 * 2. High Cohesion: This class has one responsibility and does it well.
 * 2. Singleton    : Only one instance of this class can be created.
 */
public class GraphicsManager {
    private static GraphicsManager instance = null;
    private final Image[] imageCache;
    private final int[] widthCache;
    private final int[] heightCache;

    private GraphicsManager() {
        imageCache = new Image[Asset.values().length];
        widthCache = new int[Asset.values().length];
        heightCache = new int[Asset.values().length];
    }

    public static GraphicsManager getInstance() {
        if (instance == null) instance = new GraphicsManager();
        return instance;
    }

    public Image getImage(Asset asset, int width, int height) {
        try {
            int idx = asset.ordinal();
            if (imageCache[idx] == null || widthCache[idx] != width || heightCache[idx] != height) {
                // Image not found in cash, or requested with different dimensions.
                // Load image
                var bufferedImage = ImageIO.read(new File(Constants.ASSET_IMAGE_PATH.formatted(asset.name)));
                var image = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
                // Cache image
                imageCache[idx] = image;
                widthCache[idx] = width;
                heightCache[idx] = height;
            }
            // Return image from cache
            return imageCache[idx];
        } catch (IOException e) {
            System.exit(-1);
            return null;
        }
    }
}
