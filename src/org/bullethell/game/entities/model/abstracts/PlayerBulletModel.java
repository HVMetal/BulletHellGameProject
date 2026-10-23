package org.bullethell.game.entities.model.abstracts;

import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.entities.model.interfaces.PlayerBullet;
import org.bullethell.game.entities.physics.collision.CollisionVisitor;

public abstract class PlayerBulletModel extends CollisionEntityModel implements PlayerBullet {
    protected final float bulletDamage;

    public PlayerBulletModel() {
        this(1);
    }

    public PlayerBulletModel(float bulletDamage) {
        super();
        this.bulletDamage = bulletDamage;
    }

    @Override
    public final ObjectType getObjectType() {
        return ObjectType.PLAYER_BULLET;
    }

    @Override
    public boolean shouldBeDestroyed() {
        return coordinates.getX() < -getWidth()
                || coordinates.getX() > gameManager.getPlayfieldWidth()
                || coordinates.getY() < -getHeight()
                || coordinates.getY() > gameManager.getPlayfieldHeight();
    }

    @Override
    public void accept(CollisionVisitor visitor) {
        visitor.collide(this);
    }

    @Override
    public void collide(EnemyModel enemy) {
        onEnemyCollision();
        onDestruction();
    }

    @Override
    public float getBulletDamage() {
        return bulletDamage;
    }
}
