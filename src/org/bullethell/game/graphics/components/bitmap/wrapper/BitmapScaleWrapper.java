package org.bullethell.game.graphics.components.bitmap.wrapper;

import org.bullethell.game.graphics.components.bitmap.Bitmap;
import org.bullethell.game.graphics.components.bitmap.utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class BitmapScaleWrapper extends BitmapWrapper {
    private BufferedImage transformedImage;
    private float scale;

    private boolean isDirty;

    public BitmapScaleWrapper(Bitmap bitmap) {
        this(bitmap, 1f);
    }

    public BitmapScaleWrapper(Bitmap bitmap, float scale) {
        super(bitmap);
        this.scale = scale;

        transformedImage = transform(super.getImage());
    }

    @Override
    public Bitmap cloneComponent() {
        return new BitmapScaleWrapper((Bitmap) super.cloneComponent());
    }

    @Override
    public float getWidth() {
        return transformedImage.getWidth();
    }

    @Override
    public float getHeight() {
        return transformedImage.getHeight();
    }

    @Override
    public void display(Graphics g) {
        transformedImage = getImage();
        g.drawImage(transformedImage, getOffsetX(), getOffsetY(), null);
    }

    @Override
    public void setScale(float scale) {
        if(this.scale != scale) {
            isDirty = true;
        }
        this.scale = scale;
        super.setScale(scale);
    }

    @Override
    public BufferedImage getImage() {
        if(isDirty()) {
            isDirty = false;
            return transform(super.getImage());
        } else {
            return transformedImage;
        }
    }

    @Override
    public boolean isDirty() {
        return this.isDirty || super.isDirty();
    }

    private BufferedImage transform(BufferedImage image) {
        return ImageUtils.scale(image, scale);
    }
}
