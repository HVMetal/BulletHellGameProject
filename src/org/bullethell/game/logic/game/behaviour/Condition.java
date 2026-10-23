package org.bullethell.game.logic.game.behaviour;

import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.entities.model.interfaces.GameEntity;

public interface Condition extends Cloneable {
    boolean check(GameEntity entity);

    @Override
    default Condition cloneComponent() {
        return this;
    }
}
