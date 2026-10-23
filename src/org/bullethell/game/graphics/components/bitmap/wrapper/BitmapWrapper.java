package org.bullethell.game.graphics.components.bitmap.wrapper;

import org.bullethell.game.graphics.components.GraphicsComponentWrapper;
import org.bullethell.game.graphics.components.bitmap.Bitmap;

import java.awt.image.BufferedImage;

public abstract class BitmapWrapper extends GraphicsComponentWrapper<Bitmap> implements Bitmap {
    public BitmapWrapper(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public String getKey() {
        return wrappedComponent.getKey();
    }

    @Override
    public BufferedImage getImage() {
        return wrappedComponent.getImage();
    }

    @Override
    public boolean isDirty() {
        return wrappedComponent.isDirty();
    }

    @Override
    public void setAngle(float angle) {
        wrappedComponent.setAngle(angle);
    }

    @Override
    public void setScale(float scale) {
        wrappedComponent.setScale(scale);
    }

    @Override
    public void setTransparency(float transparency) {
        wrappedComponent.setTransparency(transparency);
    }
}
