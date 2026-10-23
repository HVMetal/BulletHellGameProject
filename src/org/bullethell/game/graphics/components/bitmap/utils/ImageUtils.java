package org.bullethell.game.graphics.components.bitmap.utils;

import org.bullethell.game.utils.PropertyProvider;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class ImageUtils {
    private final static Logger LOGGER = Logger.getGlobal();

    /**Loads a single image.
     *
     * @param filename The filename of the image WITHOUT its extension.
     *                 Please note that the only accepted extension is ".png".**/
    public static BufferedImage loadImage(String filename) {
        final File file = new File(PropertyProvider.getProperty("GRAPHICS_FOLDER_PATH") + filename + ".png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        }
        catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }

        return image;
    }

    /**Loads a sequence of images. Each image must have the same base name
     * and a number starting from 1 at the end separated with an underscore character.
     * That number represents the image's order in the animation.
     *
     * @param filename The filename of the image WITHOUT its extension or number.
     *                 Please note that the only accepted extension is ".png".
     * @param animationFrames The total number of images to load.**/
    public static List<BufferedImage> loadAnimation(String filename, int animationFrames) {
        final ArrayList<BufferedImage> animation = new ArrayList<>();
        for(int i = 1; i <= animationFrames; i++) {
            final String actualFilename = filename + "_" + i;
            animation.add(loadImage(actualFilename));
        }

        return animation;
    }

    public static BufferedImage scale(BufferedImage originalImage, float scale) {
        final float newWidth = originalImage.getWidth() * scale;
        final float newHeight = originalImage.getHeight() * scale;

        final BufferedImage scaledImage = new BufferedImage((int) newWidth, (int) newHeight, originalImage.getType());
        final Graphics2D g2D = scaledImage.createGraphics();
        final AffineTransform at = AffineTransform.getScaleInstance(scale, scale);

        g2D.drawRenderedImage(originalImage, at);
        g2D.dispose();

        return scaledImage;
    }

    public static BufferedImage rotate(BufferedImage originalImage, float angle) {
        final float sinVal = Math.abs((float) Math.sin(angle));
        final float cosVal = Math.abs((float) Math.cos(angle));

        final float newWidth = originalImage.getWidth() * cosVal + originalImage.getHeight() * sinVal;
        final float newHeight = originalImage.getWidth() * sinVal + originalImage.getHeight() * cosVal;

        final BufferedImage rotatedImage = new BufferedImage((int) newWidth, (int) newHeight, originalImage.getType());
        final Graphics2D g2D = rotatedImage.createGraphics();
        final AffineTransform at = AffineTransform.getRotateInstance(angle, newWidth / 2f, newHeight / 2f);

        at.translate((newWidth - originalImage.getWidth()) / 2, (newHeight - originalImage.getHeight()) / 2);
        g2D.drawRenderedImage(originalImage, at);
        g2D.dispose();

        return rotatedImage;
    }

    public static BufferedImage makeTransparent(BufferedImage originalImage, float transparency) {
        final BufferedImage transparentImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());
        final Graphics2D g2D = transparentImage.createGraphics();

        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, transparency));
        g2D.drawRenderedImage(originalImage, null);
        g2D.dispose();

        return transparentImage;
    }
}
