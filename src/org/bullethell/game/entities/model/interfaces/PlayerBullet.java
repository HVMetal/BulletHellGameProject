package org.bullethell.game.entities.model.interfaces;

public interface PlayerBullet extends CollisionEntity {
    default void onEnemyCollision() {
        onDestruction();
    }

    float getBulletDamage();
}
