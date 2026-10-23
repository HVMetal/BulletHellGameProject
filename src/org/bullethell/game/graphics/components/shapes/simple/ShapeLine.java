package org.bullethell.game.graphics.components.shapes.simple;

import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.utils.Coordinates;

import java.awt.*;

/**Creates a line with its two endpoints at (x1,y1) and (x2,y2).**/
public class ShapeLine extends SimpleShape {
    private final Coordinates endPosition;

    public ShapeLine(float x1, float y1, float x2, float y2, Color shapeColor) {
        super(x1, y1, shapeColor);
        endPosition = new Coordinates(x2, y2);
    }

    public ShapeLine(Coordinates startPosititon, Coordinates endPosition, Color shapeColor) {
        this(startPosititon.getX(), startPosititon.getY(), endPosition.getX(), endPosition.getY(), shapeColor);
    }

    @Override
    public GraphicsComponent cloneComponent() {
        return new ShapeLine(getX(), getY(), getX2(), getY2(), shapeColor);
    }

    @Override
    public float getX() {
        return Math.min(position.getX(), endPosition.getX());
    }

    @Override
    public float getY() {
        return Math.min(position.getY(), endPosition.getY());
    }

    @Override
    public float getWidth() {
        return Math.abs(position.getX() - endPosition.getX());
    }

    @Override
    public float getHeight() {
        return Math.abs(position.getY() - endPosition.getY());
    }

    @Override
    public void moveComponent(float x, float y) {
        super.moveComponent(x, y);
        endPosition.add(x, y);
    }

    @Override
    public void setCoordinates(float x, float y) {
        endPosition.setX(x + getWidth());
        endPosition.setY(y + getHeight());
        super.setCoordinates(x, y);
    }

    public void setEndCoordinates(float x2, float y2) {
        endPosition.setX(x2);
        endPosition.setY(y2);
    }

    public void setEndCoordinates(Coordinates endPosition) {
        this.setEndCoordinates(endPosition.getX(), endPosition.getY());
    }

    public float getX1() {
        return position.getX();
    }

    public float getY1() {
        return position.getY();
    }

    public float getX2() {
        return endPosition.getX();
    }

    public float getY2() {
        return endPosition.getY();
    }

    @Override
    public void display(Graphics g) {
        super.display(g);
        g.drawLine((int) getX1(), (int) getY1(), (int) getX2(), (int) getY2());
    }
}
