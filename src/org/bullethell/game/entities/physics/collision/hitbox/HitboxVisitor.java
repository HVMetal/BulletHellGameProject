package org.bullethell.game.entities.physics.collision.hitbox;

public interface HitboxVisitor {
    default boolean intersects(CircularHitbox hitbox) { return false; }
}
