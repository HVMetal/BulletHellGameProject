package org.bullethell.game.logic.game.behaviour;

import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.entities.model.interfaces.GameEntity;

public interface Behaviour extends Cloneable {
    void execute(GameEntity entity);
    void reset();
    boolean isFinished();
}
