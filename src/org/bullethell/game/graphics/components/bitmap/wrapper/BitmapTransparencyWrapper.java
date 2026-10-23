package org.bullethell.game.graphics.components.bitmap.wrapper;

import org.bullethell.game.graphics.components.bitmap.Bitmap;
import org.bullethell.game.graphics.components.bitmap.utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class BitmapTransparencyWrapper extends BitmapWrapper {
    private BufferedImage transformedImage;
    private float transparency;

    private boolean isDirty;

    public BitmapTransparencyWrapper(Bitmap bitmap) {
        this(bitmap, 1f);
    }

    public BitmapTransparencyWrapper(Bitmap bitmap, float transparency) {
        super(bitmap);
        this.transparency = transparency;

        transformedImage = transform(super.getImage());
    }

    @Override
    public Bitmap cloneComponent() {
        return new BitmapTransparencyWrapper((Bitmap) super.cloneComponent());
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
    public void setTransparency(float transparency) {
        if(this.transparency != transparency) {
            isDirty = true;
        }
        this.transparency = transparency;
        super.setTransparency(transparency);
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
        return ImageUtils.makeTransparent(image, transparency);
    }
}
