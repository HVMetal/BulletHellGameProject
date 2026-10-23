package org.bullethell.game.logic.game.behaviour.action;

import org.bullethell.game.attackpatterns.AttackPattern;
import org.bullethell.game.entities.model.interfaces.Enemy;
import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.logic.game.behaviour.Action;

public class ActionSetAttackPattern implements Action {
    private final AttackPattern attackPattern;

    private boolean isFinished;

    public ActionSetAttackPattern(AttackPattern attackPattern) {
        this.attackPattern = attackPattern;
    }

    @Override
    public Action cloneComponent() {
        return new ActionSetAttackPattern((AttackPattern) attackPattern.cloneComponent());
    }

    @Override
    public void execute(GameEntity entity) {
        if (!isFinished && entity instanceof Enemy) {
            ((Enemy) entity).setAttackPattern(attackPattern);
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
