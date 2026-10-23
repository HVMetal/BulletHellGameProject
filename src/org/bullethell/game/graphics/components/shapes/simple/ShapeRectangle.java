package org.bullethell.game.graphics.components.shapes.simple;

import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.utils.Coordinates;

import java.awt.*;

/**Creates a rectangle with its upper-left corner at (x,y) and with a given width and height.**/
public class ShapeRectangle extends SimpleShape {
    private float width, height;

    public ShapeRectangle(float x, float y, float width, float height, Color shapeColor) {
        super(x, y, shapeColor);
        this.width = width;
        this.height = height;
    }

    public ShapeRectangle(Coordinates position, float width, float height, Color shapeColor) {
        this(position.getX(), position.getY(), width, height, shapeColor);
    }

    @Override
    public GraphicsComponent cloneComponent() {
        return new ShapeRectangle(getX(), getY(), getWidth(), getHeight(), shapeColor);
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void display(Graphics g) {
        super.display(g);
        g.drawRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }
}
