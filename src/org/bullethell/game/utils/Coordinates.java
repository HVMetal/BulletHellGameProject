package org.bullethell.game.utils;

/**
 * Represents a point in the 2D space.
 */
public final class Coordinates {
    private float x, y;

    public Coordinates() {
        this(0, 0);
    }

    public Coordinates(Coordinates c) {
        this(c.x, c.y);
    }

    public Coordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setCoordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setCoordinates(Coordinates c) {
        this.setCoordinates(c.x, c.y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void add(Coordinates c) {
        this.add(c.x, c.y);
    }

    public boolean equal(float x, float y) {
        return this.x == x && this.y == y;
    }

    public boolean equal(Coordinates c) {
        return this.equal(c.x, c.y);
    }
}
