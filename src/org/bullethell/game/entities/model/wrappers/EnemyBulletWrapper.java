package org.bullethell.game.entities.model.wrappers;

import org.bullethell.game.entities.model.interfaces.EnemyBullet;

public abstract class EnemyBulletWrapper extends CollisionEntityWrapper<EnemyBullet> implements EnemyBullet {
    public EnemyBulletWrapper(EnemyBullet entity) {
        super(entity);
    }

    @Override
    public void onPlayerCollision() {
        wrappedEntity.onPlayerCollision();
    }
}
