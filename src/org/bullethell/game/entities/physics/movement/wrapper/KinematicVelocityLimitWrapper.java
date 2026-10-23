package org.bullethell.game.entities.physics.movement.wrapper;

import org.bullethell.game.entities.physics.movement.Kinematic;
import org.bullethell.game.entities.physics.movement.Movable;
import org.bullethell.game.entities.physics.movement.MovementComponent;

public final class KinematicVelocityLimitWrapper extends MovementWrapper {
    private final float maxVelocity;

    public KinematicVelocityLimitWrapper(MovementComponent movementComponent, float maxVelocity) {
        super(movementComponent);
        this.maxVelocity = maxVelocity;
    }

    @Override
    public MovementComponent cloneComponent() {
        return new KinematicVelocityLimitWrapper((MovementComponent) movementComponent.cloneComponent(), maxVelocity);
    }

    @Override
    public void calculateAndPosition(Movable movableObject) {
        super.calculateAndPosition(movableObject);

        if(movementComponent instanceof Kinematic kinematic) {
            if(kinematic.getAcceleration() > 0 && kinematic.getVelocity() >= maxVelocity) {
                kinematic.setAcceleration(0);
                kinematic.setVelocity(maxVelocity);
            }
            if(kinematic.getAcceleration() < 0 && kinematic.getVelocity() <= maxVelocity) {
                kinematic.setAcceleration(0);
                kinematic.setVelocity(maxVelocity);
            }
        }
    }
}
