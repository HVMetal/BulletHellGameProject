package org.bullethell.game.entities.model.interfaces;

import org.bullethell.game.entities.physics.movement.Movable;
import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.logic.game.GameManager;

public interface GameEntity extends Movable, DisplayableEntity, Destroyable, Cloneable {
    /**
     * Returns the type of object the caller is. Used for optimization and look-ups.
     * IT IS STRONGLY ADVISED TO RETURN THE CORRECT TYPE. Failure to do so WILL cause runtime errors.
     *
     * @return The type of object the caller is.
     * **/
    ObjectType getObjectType();

    void coreEntityLoop();

    /**
     * Checks if the object is actively performing tasks.
     *
     * @return True if the object is active, false otherwise. In case it's false, the game will dispose of
     * the object.
     * **/
    boolean isActive();

    /**
     * Sets the object's activity state.
     *
     * @param active true to activate, false to deactivate the entity.
     **/
    void setActive(boolean active);

    /**
     * Provides a reference to the game manager, allowing the entity to interact with global game logic.
     *
     * @return The game manager instance.
     **/
    GameManager getGameManager();

    /**
     * Links the entity to the game manager.
     *
     * @param gameManager the game manager instance to set.
     **/
    void setGameManager(GameManager gameManager);
}
