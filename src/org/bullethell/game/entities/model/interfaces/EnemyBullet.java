package org.bullethell.game.entities.model.interfaces;

public interface EnemyBullet extends CollisionEntity {
    default void onPlayerCollision() {
        onDestruction();
    }
}
