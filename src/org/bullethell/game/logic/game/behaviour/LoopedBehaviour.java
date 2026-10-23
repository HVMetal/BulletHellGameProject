package org.bullethell.game.logic.game.behaviour;

import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.entities.model.interfaces.GameEntity;

public class LoopedBehaviour implements Behaviour {
    private final Behaviour behaviour;
    private final int loops;

    private int loopCounter;
    private boolean isFinished;

    public LoopedBehaviour(int loops, Behaviour behaviour) {
        this.loops = loops;
        this.behaviour = behaviour;
    }

    @Override
    public Cloneable cloneComponent() {
        return new LoopedBehaviour(loops, (Behaviour) behaviour.cloneComponent());
    }

    @Override
    public void execute(GameEntity entity) {
        if (loopCounter < loops) {
            behaviour.execute(entity);

            if (behaviour.isFinished()) {
                behaviour.reset();
                loopCounter++;
            }
        } else {
            isFinished = true;
        }
    }

    @Override
    public void reset() {
        behaviour.reset();
        isFinished = false;
        loopCounter = 0;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
