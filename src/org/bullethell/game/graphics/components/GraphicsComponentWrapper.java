package org.bullethell.game.graphics.components;

import org.bullethell.game.utils.Coordinates;

import java.awt.*;

public abstract class GraphicsComponentWrapper<T extends GraphicsComponent> implements GraphicsComponent {
    protected final T wrappedComponent;

    public GraphicsComponentWrapper(T wrappedComponent) {
        this.wrappedComponent = wrappedComponent;
    }

    @Override
    public GraphicsComponent cloneComponent() {
        return (GraphicsComponent) wrappedComponent.cloneComponent();
    }

    @Override
    public float getX() {
        return wrappedComponent.getX();
    }

    @Override
    public float getY() {
        return wrappedComponent.getY();
    }

    @Override
    public float getWidth() {
        return wrappedComponent.getWidth();
    }

    @Override
    public float getHeight() {
        return wrappedComponent.getHeight();
    }

    @Override
    public void moveComponent(float x, float y) {
        wrappedComponent.moveComponent(x, y);
    }

    @Override
    public void moveComponent(Coordinates coordinates) {
        wrappedComponent.moveComponent(coordinates);
    }

    @Override
    public void setCoordinates(float x, float y) {
        wrappedComponent.setCoordinates(x, y);
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        wrappedComponent.setCoordinates(coordinates);
    }

    @Override
    public int getOffsetX() {
        return wrappedComponent.getOffsetX();
    }

    @Override
    public int getOffsetY() {
        return wrappedComponent.getOffsetY();
    }

    @Override
    public void display(Graphics g) {
        wrappedComponent.display(g);
    }
}
