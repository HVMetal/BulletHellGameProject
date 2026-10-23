package org.bullethell.game.graphics.components;

import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.utils.Coordinates;

public interface GraphicsComponent extends Displayable, Cloneable {
    float getX();
    float getY();
    float getWidth();
    float getHeight();

    void moveComponent(float x, float y);
    default void moveComponent(Coordinates coordinates) {
        this.moveComponent(coordinates.getX(), coordinates.getY());
    }

    void setCoordinates(float x, float y);
    default void setCoordinates(Coordinates coordinates) {
        this.setCoordinates(coordinates.getX(), coordinates.getY());
    }

    default int getOffsetX() {
        return (int) (getX() - getWidth() / 2);
    }
    default int getOffsetY() {
        return (int) (getY() - getHeight() / 2);
    }
}
