package org.bullethell.game.graphics.components.shapes.simple;

import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.utils.Coordinates;

import java.awt.*;

/**Creates a circle CENTERED at (x, y) with a given radius.**/
public class ShapeCircle extends SimpleShape {
    private float radius;

    public ShapeCircle(float x, float y, float radius, Color shapeColor) {
        super(x, y, shapeColor);
        this.radius = radius;
    }

    public ShapeCircle(Coordinates position, float radius, Color shapeColor) {
        this(position.getX(), position.getY(), radius, shapeColor);
    }

    @Override
    public GraphicsComponent cloneComponent() {
        return new ShapeCircle(new Coordinates(position), radius, shapeColor);
    }

    @Override
    public float getWidth() {
        return 2f * radius;
    }

    @Override
    public float getHeight() {
        return 2f * radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public void display(Graphics g) {
        super.display(g);
        g.drawOval(getOffsetX(), getOffsetY(), (int) getWidth(), (int) getHeight());
    }
}
