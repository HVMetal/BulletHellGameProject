package org.bullethell.game.entities.physics.movement;

import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.utils.Coordinates;

public interface MovementComponent extends Cloneable {
    /**
     * Calculates and positions the {@link Movable} object.
     * **/
    void calculateAndPosition(Movable movableObject);

    /**
     * Checks if the movement has finished.

     * @return True if the movement has finished, false otherwise.
     * **/
    default boolean hasFinished() {
        return false;
    }

    /**
     * Retrieves or calculates the direction of the movement.
     *
     * @return The direction of the movement.
     * **/
    default float getDirection() {
        return 0;
    }

    /**
     * Sets the direction of the movement.
     *
     * @param direction The new direction of the movement.
     * **/
    default void setDirection(float direction) {}

    default float getVelocity() { return 0; }

    default float getXVelocity() {
        final float direction = (float) Math.toRadians(getDirection());
        return getVelocity() * (float) Math.cos(direction);
    }

    default float getYVelocity() {
        final float direction = (float) Math.toRadians(getDirection());
        return getVelocity() * (float) Math.sin(direction);
    }

    default void advanceParameters() {}
}
