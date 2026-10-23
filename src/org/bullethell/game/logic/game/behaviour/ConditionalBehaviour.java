package org.bullethell.game.logic.game.behaviour;

import org.bullethell.game.entities.model.interfaces.GameEntity;

public class ConditionalBehaviour implements Behaviour {
    private final Condition condition;
    private final Action action;

    private boolean isFinished;

    public ConditionalBehaviour(Action action) {
        this((_) -> true, action);
    }

    public ConditionalBehaviour(Condition condition, Action action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public Behaviour cloneComponent() {
        return new ConditionalBehaviour(condition.cloneComponent(), (Action) action.cloneComponent());
    }

    @Override
    public void execute(GameEntity entity) {
        if(condition.check(entity)) {
            action.execute(entity);

            if(action.isFinished()) {
                isFinished = true;
            }
        }
    }

    @Override
    public void reset() {
        isFinished = false;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
