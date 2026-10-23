package org.bullethell.game.logic.game;

import org.bullethell.game.entities.model.interfaces.CollisionEntity;
import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.physics.collision.management.SpatialPartition;
import org.bullethell.game.logic.game.creation.LevelCreator;
import org.bullethell.game.ui.components.background.GameBackgroundManager;
import org.bullethell.game.entities.storage.ObjectType;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.List;

public interface GameManager {
    int getPlayfieldWidth();
    int getPlayfieldHeight();

    void add(GameEntity entity);
    void remove(GameEntity entity);
    List<GameEntity> getEntities(ObjectType entityType);

    GameBackgroundManager getBackgroundManager();
    SpatialPartition<CollisionEntity> getSpatialPartition();

    void coreObjectEvents();
    void repaintPlayfield(Graphics g);

    void gameOver();
    void prepareNextLevelInitialization();
    void initializeNextLevel(LevelCreator levelCreator);
    void endGame();

    default void addListener(KeyListener keyListener) {}
    default void removeListener(KeyListener keyListener) {}

    void clear();
}
