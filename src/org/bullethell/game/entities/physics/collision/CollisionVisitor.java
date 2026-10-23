package org.bullethell.game.entities.physics.collision;

import org.bullethell.game.entities.model.abstracts.*;

public interface CollisionVisitor {
    default void collide(PlayerModel player) {}
    default void collide(PlayerBulletModel playerBullet) {}
    default void collide(EnemyModel enemy) {}
    default void collide(EnemyBulletModel enemyBullet) {}
    default void collide(BonusModel bonus) {}
}
