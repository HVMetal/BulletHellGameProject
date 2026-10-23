package org.bullethell.game.entities.physics.collision;

import org.bullethell.game.entities.physics.collision.hitbox.Hitbox;
import org.bullethell.game.entities.physics.collision.management.SpacePartitionParameters;

public interface Collidable {
    void accept(CollisionVisitor visitor);

    SpacePartitionParameters getCollisionParametersComponent();
    void setCollisionParametersComponent(SpacePartitionParameters spacePartitionParameters);

    Hitbox getHitbox();
    void setHitbox(Hitbox hitbox);
}
