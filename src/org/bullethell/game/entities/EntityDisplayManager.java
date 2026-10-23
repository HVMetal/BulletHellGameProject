package org.bullethell.game.entities;

import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.logic.game.GameManager;

import java.awt.Graphics;
import java.util.List;

public final class EntityDisplayManager {
    private final GameManager gameManager;

    public EntityDisplayManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void display(Graphics g) {
        displayEntities(g);
    }

    private void displayEntities(Graphics g) {
        for(ObjectType objectType : ObjectType.values()) {
            final List<GameEntity> entities = gameManager.getEntities(objectType);

            for(GameEntity gameEntity : entities) {
                gameEntity.display(g);
            }
        }
    }
}
