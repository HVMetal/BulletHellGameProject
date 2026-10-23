package org.bullethell.game.entities;

import org.bullethell.game.entities.model.interfaces.CollisionEntity;
import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.physics.collision.hitbox.HitboxVisitor;
import org.bullethell.game.entities.physics.collision.management.SpatialPartition;
import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.logic.game.GameManager;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class EntityCollisionManager {
    private final GameManager gameManager;

    public EntityCollisionManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void execute() {
        updateSpatialPartition();
        assertCollisions(ObjectType.PLAYER);
        assertCollisions(ObjectType.ENEMY);
    }

    private void updateSpatialPartition() {
        final SpatialPartition<CollisionEntity> spatialPartition = gameManager.getSpatialPartition();
        for(ObjectType objectType : ObjectType.values()) {
            final List<GameEntity> gameEntities = gameManager.getEntities(objectType);

            for(GameEntity gameEntity : gameEntities) {
                if(gameEntity instanceof CollisionEntity collisionEntity && collisionEntity.getHitbox() != null) {
                    spatialPartition.update(collisionEntity);
                }
            }
        }
    }

    private void assertCollisions(ObjectType objectType) {
        final List<GameEntity> gameEntities = gameManager.getEntities(objectType);

        for(GameEntity gameEntity : gameEntities) {
            boolean isCurrentEntityDestroyed = false;
            if(!(gameEntity instanceof CollisionEntity collisionEntity) || collisionEntity.getHitbox() == null) {
                continue;
            }

            final Set<CollisionEntity> nearbyEntities = gameManager.getSpatialPartition().getObjectsNearby(collisionEntity);
            for(Iterator<CollisionEntity> j = nearbyEntities.iterator(); j.hasNext() && !isCurrentEntityDestroyed;) {
                final CollisionEntity currentNearby = j.next();

                if(currentNearby.getHitbox() != null
                        && collisionEntity.getHitbox().accept((HitboxVisitor) currentNearby.getHitbox())) {

                    collisionEntity.accept(currentNearby);
                    if(!collisionEntity.isActive()) {
                        isCurrentEntityDestroyed = true;
                    }

                    currentNearby.accept(collisionEntity);
                    if(!currentNearby.isActive()) {
                        j.remove();
                    }
                }
            }
        }
    }
}
