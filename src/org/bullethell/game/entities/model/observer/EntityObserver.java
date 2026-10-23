package org.bullethell.game.entities.model.observer;

import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

public interface EntityObserver {
    default boolean isDestroyed() {
        return false;
    }

    default void notifyOnGameReception(GameManager gameManager) {}
    default void notifyOnDestruction(GameManager gameManager) {}
    default void notifyOnMovement(Coordinates coordinates) {}
    default void notifyOnHitTaken() {}
}
