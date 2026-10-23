package org.bullethell.game.logic.game.behaviour;

import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.logic.game.behaviour.action.ActionStateless;

/**
 * An Action type useable with the {@link ActionStateless} class. Implementing
 * this interface in a stateful manner WILL likely cause errors,
 * and they will NOT be MY problem.
 */
public interface StatelessAction extends Cloneable {
    void execute(GameEntity entity);

    @Override
    default StatelessAction cloneComponent() {
        return this;
    }
}
