package org.bullethell.game.entities.implementation.player.behaviour.observer;

import org.bullethell.game.entities.model.interfaces.Destroyable;
import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.model.observer.EntityObserver;
import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.logic.game.GameManager;

import java.util.List;

public final class OnPlayerHitClearBullets implements EntityObserver {
    private final GameManager gameManager;

    public OnPlayerHitClearBullets(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void notifyOnHitTaken() {
        List<GameEntity> enemyBullets = gameManager.getEntities(ObjectType.ENEMY_BULLET);
        enemyBullets.forEach(Destroyable::onDestruction);
    }
}
