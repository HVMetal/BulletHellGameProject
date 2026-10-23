package org.bullethell.game.entities;

import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.logic.game.GameManager;

import java.util.List;

public final class EntityCoreEventManager {
    private final GameManager gameManager;

    public EntityCoreEventManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void execute() {
        for(ObjectType objectType : ObjectType.values()) {
            final List<GameEntity> gameEntities = gameManager.getEntities(objectType);

            for(int i = gameEntities.size() - 1; i >= 0; i--) {
                final GameEntity gameEntity = gameEntities.get(i);
                if(gameEntity.isActive()) {
                    gameEntity.coreEntityLoop();
                }
                if(!gameEntity.isActive()) {
                    gameEntity.onDestruction();
                    gameManager.remove(gameEntity);
                }
            }
        }
    }
}
