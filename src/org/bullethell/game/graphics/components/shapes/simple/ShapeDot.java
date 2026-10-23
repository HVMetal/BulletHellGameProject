package org.bullethell.game.graphics.components.shapes.simple;

import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.utils.Coordinates;

import java.awt.*;

/**Creates a dot CENTERED at (x,y) and with a given size.**/
public class ShapeDot extends SimpleShape {
    private float dotSize;

    public ShapeDot(float x, float y, float dotSize, Color shapeColor) {
        super(x, y, shapeColor);
        this.dotSize = dotSize;
    }

    public ShapeDot(Coordinates position, float dotSize, Color shapeColor) {
        this(position.getX(), position.getY(), dotSize, shapeColor);
    }

    @Override
    public GraphicsComponent cloneComponent() {
        return new ShapeDot(getX(), getY(), dotSize, shapeColor);
    }

    @Override
    public float getWidth() {
        return dotSize;
    }

    @Override
    public float getHeight() {
        return dotSize;
    }

    public void setDotSize(float dotSize) {
        this.dotSize = dotSize;
    }

    @Override
    public void display(Graphics g) {
        super.display(g);
        g.fillOval(getOffsetX(), getOffsetY(), (int) getWidth(), (int) getHeight());
    }
}
