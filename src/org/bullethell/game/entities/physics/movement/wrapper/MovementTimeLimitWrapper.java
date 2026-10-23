package org.bullethell.game.entities.physics.movement.wrapper;

import org.bullethell.game.entities.physics.movement.Movable;
import org.bullethell.game.entities.physics.movement.MovementComponent;

public final class MovementTimeLimitWrapper extends MovementWrapper {
    private final int timeLimit;
    private int time;

    public MovementTimeLimitWrapper(int timeLimit, MovementComponent movementComponent) {
        super(movementComponent);
        this.timeLimit = timeLimit;
    }

    @Override
    public MovementComponent cloneComponent() {
        return new MovementTimeLimitWrapper(timeLimit, super.cloneComponent());
    }

    @Override
    public void calculateAndPosition(Movable movableObject) {
        if(time < timeLimit) {
            time++;
        }

        super.calculateAndPosition(movableObject);
    }

    @Override
    public boolean hasFinished() {
        return time >= timeLimit || super.hasFinished();
    }
}
