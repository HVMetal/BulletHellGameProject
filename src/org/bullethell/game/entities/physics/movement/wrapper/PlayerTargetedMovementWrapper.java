package org.bullethell.game.entities.physics.movement.wrapper;

import org.bullethell.game.entities.model.interfaces.Player;
import org.bullethell.game.entities.physics.movement.Movable;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;
import org.bullethell.game.utils.MathUtils;

public final class PlayerTargetedMovementWrapper extends MovementWrapper {
    private final GameManager gameManager;
    private boolean hasAcquiredTarget;

    public PlayerTargetedMovementWrapper(MovementComponent movementComponent, GameManager gameManager) {
        super(movementComponent);
        this.gameManager = gameManager;
    }

    @Override
    public MovementComponent cloneComponent() {
        return new PlayerTargetedMovementWrapper(super.cloneComponent(), gameManager);
    }

    @Override
    public void calculateAndPosition(Movable movableObject) {
        if(!hasAcquiredTarget) {
            hasAcquiredTarget = true;
            acquireTarget(movableObject);
        }

        super.calculateAndPosition(movableObject);
    }

    @Override
    public void setDirection(float direction) {}

    private void acquireTarget(Movable movableObject) {
        final Player player = (Player) gameManager.getEntities(ObjectType.PLAYER).getFirst();
        final float angle = (float) Math.toDegrees(MathUtils.atan2(movableObject.getCoordinates(), player.getCoordinates()));
        super.setDirection(angle);
    }
}
