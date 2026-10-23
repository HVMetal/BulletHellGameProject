package org.bullethell.game.graphics.components.bitmap;

import org.bullethell.game.graphics.components.GraphicsComponent;

import java.awt.image.BufferedImage;

public interface Bitmap extends GraphicsComponent {
    String getKey();
    BufferedImage getImage();

    default boolean isDirty() {
        return false;
    }
    default void setAngle(float angle) {}
    default void setScale(float scale) {}
    default void setTransparency(float transparency) {}
}
