package org.bullethell.game.entities.model.abstracts;

import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.entities.model.interfaces.EnemyBullet;
import org.bullethell.game.entities.physics.collision.CollisionVisitor;

public abstract class EnemyBulletModel extends CollisionEntityModel implements EnemyBullet {
    private boolean isHitByPlayer;

    public EnemyBulletModel() {
        super();
    }

    @Override
    public final ObjectType getObjectType() {
        return ObjectType.ENEMY_BULLET;
    }

    @Override
    public boolean shouldBeDestroyed() {
        return getCoordinates().getX() < -getWidth()
                || getCoordinates().getX() > gameManager.getPlayfieldWidth() + getWidth()
                || getCoordinates().getY() < -getHeight()
                || getCoordinates().getY() > gameManager.getPlayfieldHeight() + getHeight()
                || isHitByPlayer
                || super.shouldBeDestroyed();
    }

    @Override
    public void accept(CollisionVisitor visitor) {
        visitor.collide(this);
    }

    @Override
    public void collide(PlayerModel player) {
        onPlayerCollision();
        isHitByPlayer = true;
    }
}
