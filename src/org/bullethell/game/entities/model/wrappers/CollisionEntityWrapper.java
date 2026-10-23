package org.bullethell.game.entities.model.wrappers;

import org.bullethell.game.entities.model.abstracts.*;
import org.bullethell.game.entities.model.interfaces.CollisionEntity;
import org.bullethell.game.entities.physics.collision.CollisionVisitor;
import org.bullethell.game.entities.physics.collision.hitbox.Hitbox;
import org.bullethell.game.entities.physics.collision.management.SpacePartitionParameters;

public abstract class CollisionEntityWrapper<T extends CollisionEntity> extends GameEntityWrapper<T> implements CollisionEntity {
    public CollisionEntityWrapper(T entity) {
        super(entity);
    }

    @Override
    public void accept(CollisionVisitor visitor) {
        wrappedEntity.accept(visitor);
    }

    @Override
    public SpacePartitionParameters getCollisionParametersComponent() {
        return wrappedEntity.getCollisionParametersComponent();
    }

    @Override
    public void setCollisionParametersComponent(SpacePartitionParameters spacePartitionParameters) {
        wrappedEntity.setCollisionParametersComponent(spacePartitionParameters);
    }

    @Override
    public Hitbox getHitbox() {
        return wrappedEntity.getHitbox();
    }

    @Override
    public void setHitbox(Hitbox hitbox) {
        wrappedEntity.setHitbox(hitbox);
    }

    @Override
    public void collide(PlayerModel player) {
        wrappedEntity.collide(player);
    }

    @Override
    public void collide(PlayerBulletModel playerBullet) {
        wrappedEntity.collide(playerBullet);
    }

    @Override
    public void collide(EnemyModel enemy) {
        wrappedEntity.collide(enemy);
    }

    @Override
    public void collide(EnemyBulletModel enemyBullet) {
        wrappedEntity.collide(enemyBullet);
    }

    @Override
    public void collide(BonusModel bonus) {
        wrappedEntity.collide(bonus);
    }
}
