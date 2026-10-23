package org.bullethell.game.graphics.components.shapes.wrapper;

import org.bullethell.game.graphics.components.GraphicsComponentWrapper;
import org.bullethell.game.graphics.components.shapes.Shape;

import java.awt.*;

public abstract class ShapeComponentWrapper extends GraphicsComponentWrapper<Shape> implements Shape {
    public ShapeComponentWrapper(Shape wrappedComponent) {
        super(wrappedComponent);
    }

    @Override
    public Color getColor() {
        return wrappedComponent.getColor();
    }

    @Override
    public void setColor(Color color) {
        wrappedComponent.setColor(color);
    }
}
