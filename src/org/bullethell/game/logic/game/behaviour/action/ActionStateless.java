package org.bullethell.game.logic.game.behaviour.action;

import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.logic.game.behaviour.Action;
import org.bullethell.game.logic.game.behaviour.StatelessAction;

/**
 * An implementation of {@link Action} that invokes
 * one-and-done actions through the {@link StatelessAction} functional
 * interface.
 */
public class ActionStateless implements Action {
    private final StatelessAction action;

    private boolean isFinished;

    public ActionStateless(StatelessAction action) {
        this.action = action;
    }

    @Override
    public Cloneable cloneComponent() {
        return new ActionStateless(action.cloneComponent());
    }

    @Override
    public void execute(GameEntity entity) {
        if (!isFinished) {
            action.execute(entity);
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
