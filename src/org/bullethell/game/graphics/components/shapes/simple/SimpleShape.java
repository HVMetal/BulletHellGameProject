package org.bullethell.game.graphics.components.shapes.simple;

import org.bullethell.game.graphics.components.shapes.Shape;
import org.bullethell.game.utils.Coordinates;

import java.awt.*;

public abstract class SimpleShape implements Shape {
    protected Color shapeColor;
    protected final Coordinates position;

    public SimpleShape(float x, float y, Color shapeColor) {
        position = new Coordinates(x, y);
        this.shapeColor = shapeColor;
    }

    @Override
    public float getX() {
        return position.getX();
    }

    @Override
    public float getY() {
        return position.getY();
    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }

    @Override
    public void moveComponent(float x, float y) {
        position.add(x, y);
    }

    @Override
    public void setCoordinates(float x, float y) {
        position.setX(x);
        position.setY(y);
    }

    @Override
    public void display(Graphics g) {
        g.setColor(shapeColor);
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(
                2f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER,
                10f
        ));
    }

    @Override
    public Color getColor() {
        return shapeColor;
    }

    @Override
    public void setColor(Color color) {
        shapeColor = color;
    }
}
