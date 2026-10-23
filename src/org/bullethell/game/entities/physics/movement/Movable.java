package org.bullethell.game.entities.physics.movement;

import org.bullethell.game.utils.Coordinates;

public interface Movable {
    void setMovementComponent(MovementComponent movementComponent);
    MovementComponent getMovementComponent();

    Coordinates getCoordinates();

    default void moveObject(float x, float y) {
        getCoordinates().add(x, y);
    }
    default void moveObject(Coordinates coordinates) {
        this.moveObject(coordinates.getX(), coordinates.getY());
    }

    default void setCoordinates(float x, float y) {
        getCoordinates().setX(x);
        getCoordinates().setY(y);
    }
    default void setCoordinates(Coordinates coordinates) {
        this.setCoordinates(coordinates.getX(), coordinates.getY());
    }
}
