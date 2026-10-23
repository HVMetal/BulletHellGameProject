package org.bullethell.game.graphics.components.shapes.simple;

import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.utils.Coordinates;

import java.awt.*;

public class ShapeFilledRectangle extends ShapeRectangle {
    public ShapeFilledRectangle(float x, float y, float width, float height, Color shapeColor) {
        super(x, y, width, height, shapeColor);
    }

    public ShapeFilledRectangle(Coordinates position, float width, float height, Color shapeColor) {
        super(position, width, height, shapeColor);
    }

    @Override
    public GraphicsComponent cloneComponent() {
        return new ShapeFilledRectangle(getX(), getY(), getWidth(), getHeight(), shapeColor);
    }

    @Override
    public void display(Graphics g) {
        super.display(g);
        g.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }
}
