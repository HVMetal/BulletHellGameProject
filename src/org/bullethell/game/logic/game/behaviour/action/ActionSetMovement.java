package org.bullethell.game.logic.game.behaviour.action;

import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.logic.game.behaviour.Action;

public class ActionSetMovement implements Action {
    private final MovementComponent movementComponent;
    private boolean isFinished;

    public ActionSetMovement(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    @Override
    public Action cloneComponent() {
        return new ActionSetMovement((MovementComponent) movementComponent.cloneComponent());
    }

    @Override
    public void execute(GameEntity entity) {
        if (!isFinished) {
            entity.setMovementComponent(movementComponent);
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
