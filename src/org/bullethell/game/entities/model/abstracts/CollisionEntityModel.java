package org.bullethell.game.entities.model.abstracts;

import org.bullethell.game.entities.model.interfaces.CollisionEntity;
import org.bullethell.game.entities.physics.collision.hitbox.Hitbox;
import org.bullethell.game.entities.physics.collision.management.SpacePartitionParameters;

public abstract class CollisionEntityModel extends GameEntityModel implements CollisionEntity {
    protected Hitbox hitbox;
    protected SpacePartitionParameters collisionParameters;

    public CollisionEntityModel() {
        super();
    }

    @Override
    public void coreEntityLoop() {
        super.coreEntityLoop();
        if(isActive() && hitbox != null) {
            hitbox.setPosition(getCoordinates());
        }
    }

    @Override
    public SpacePartitionParameters getCollisionParametersComponent() {
        return collisionParameters;
    }

    @Override
    public void setCollisionParametersComponent(SpacePartitionParameters collisionParameters) {
        this.collisionParameters = collisionParameters;
    }

    @Override
    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }
}
