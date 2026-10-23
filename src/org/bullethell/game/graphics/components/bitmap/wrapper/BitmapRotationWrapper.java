package org.bullethell.game.graphics.components.bitmap.wrapper;

import org.bullethell.game.graphics.components.bitmap.Bitmap;
import org.bullethell.game.graphics.components.bitmap.utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class BitmapRotationWrapper extends BitmapWrapper {
    private BufferedImage transformedImage;
    private float angle;

    private boolean isDirty;

    public BitmapRotationWrapper(Bitmap bitmap) {
        this(bitmap, 0);
    }

    public BitmapRotationWrapper(Bitmap bitmap, float angle) {
        super(bitmap);
        this.angle = angle;

        transformedImage = transform(super.getImage());
    }

    @Override
    public Bitmap cloneComponent() {
        return new BitmapRotationWrapper((Bitmap) super.cloneComponent());
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
    public void setAngle(float angle) {
        if(this.angle != angle) {
            isDirty = true;
        }
        this.angle = (float) Math.toRadians(angle);
        super.setAngle(this.angle);
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
        return ImageUtils.rotate(image, angle);
    }
}
