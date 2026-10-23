package org.bullethell.game.entities.implementation.enemybullet.behaviour.wrapper;

import org.bullethell.game.entities.model.interfaces.EnemyBullet;
import org.bullethell.game.entities.model.wrappers.EnemyBulletWrapper;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.graphics.components.bitmap.Bitmap;

public final class DirectionalWrapper extends EnemyBulletWrapper {
    private final Type type;

    public enum Type {
        SET_DIRECTION_ONLY_ON_START,
        ALWAYS_SET_DIRECTION
    }

    public DirectionalWrapper(EnemyBullet entity) {
        this(entity, Type.SET_DIRECTION_ONLY_ON_START);
    }

    public DirectionalWrapper(EnemyBullet entity, Type type) {
        super(entity);
        this.type = type;
    }

    @Override
    public EnemyBullet cloneComponent() {
        return new DirectionalWrapper((EnemyBullet) super.cloneComponent(), type);
    }

    @Override
    public void coreEntityLoop() {
        super.coreEntityLoop();

        if(type == Type.ALWAYS_SET_DIRECTION
                && getGraphicsComponent() != null
                && getGraphicsComponent() instanceof Bitmap bitmap
                && getMovementComponent() != null) {

            bitmap.setAngle(getMovementComponent().getDirection());
        }
    }

    @Override
    public void setMovementComponent(MovementComponent movementComponent) {
        super.setMovementComponent(movementComponent);

        if(type == Type.SET_DIRECTION_ONLY_ON_START
                && getGraphicsComponent() != null
                && getGraphicsComponent() instanceof Bitmap bitmap) {

            bitmap.setAngle(getMovementComponent().getDirection());
        }
    }

    @Override
    public void setGraphicsComponent(GraphicsComponent graphicsComponent) {
        super.setGraphicsComponent(graphicsComponent);

        if(type == Type.SET_DIRECTION_ONLY_ON_START
                && getGraphicsComponent() instanceof Bitmap bitmap
                && getMovementComponent() != null) {

            bitmap.setAngle(getMovementComponent().getDirection());
        }
    }
}
