package org.bullethell.game.graphics.components.bitmap;

import org.bullethell.game.utils.Coordinates;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class Sprite implements Bitmap {
    private final BufferedImage image;
    private final String imageKey;
    private final Coordinates coordinates;

    public Sprite(String imageKey, BufferedImage image) {
        this.imageKey = imageKey;
        this.image = image;

        coordinates = new Coordinates();
    }

    @Override
    public Bitmap cloneComponent() {
        return this;
    }

    @Override
    public String getKey() {
        return imageKey;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public float getX() {
        return coordinates.getX();
    }

    @Override
    public float getY() {
        return coordinates.getY();
    }

    @Override
    public float getWidth() {
        return image.getWidth();
    }

    @Override
    public float getHeight() {
        return image.getHeight();
    }

    @Override
    public void moveComponent(float x, float y) {
        coordinates.add(x, y);
    }

    @Override
    public void setCoordinates(float x, float y) {
        coordinates.setX(x);
        coordinates.setY(y);
    }

    @Override
    public void display(Graphics g) {
        g.drawImage(image, getOffsetX(), getOffsetY(), null);
    }
}
