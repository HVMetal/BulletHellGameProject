package org.bullethell.game.logic.game.behaviour;

import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.entities.model.interfaces.GameEntity;

public interface Action extends Cloneable {
    void execute(GameEntity entity);
    boolean isFinished();
}
