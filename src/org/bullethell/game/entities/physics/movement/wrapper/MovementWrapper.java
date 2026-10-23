package org.bullethell.game.entities.physics.movement.wrapper;

import org.bullethell.game.entities.physics.movement.Movable;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.utils.Coordinates;

public abstract class MovementWrapper implements MovementComponent {
    protected MovementComponent movementComponent;

    public MovementWrapper(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    @Override
    public MovementComponent cloneComponent() {
        return (MovementComponent) movementComponent.cloneComponent();
    }

    @Override
    public void calculateAndPosition(Movable movableObject) {
        movementComponent.calculateAndPosition(movableObject);
    }

    @Override
    public float getDirection() {
        return movementComponent.getDirection();
    }

    @Override
    public float getVelocity() {
        return movementComponent.getVelocity();
    }

    @Override
    public void setDirection(float direction) {
        movementComponent.setDirection(direction);
    }

    @Override
    public boolean hasFinished() {
        return movementComponent.hasFinished();
    }
}
