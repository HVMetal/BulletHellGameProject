package org.bullethell.game.logic.game.behaviour;

import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.entities.EntityUtils;
import org.bullethell.game.entities.model.interfaces.GameEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcurrentBehaviours implements Behaviour {
    private final List<Behaviour> behaviours = new ArrayList<>();

    private boolean isFinished;

    public ConcurrentBehaviours(Behaviour... behaviours) {
        this.behaviours.addAll(Arrays.asList(behaviours));
    }

    @Override
    public Cloneable cloneComponent() {
        final List<Behaviour> clonedBehaviours = EntityUtils.cloneList(behaviours);
        return new ConcurrentBehaviours(clonedBehaviours.toArray(new Behaviour[0]));
    }

    @Override
    public void execute(GameEntity entity) {
        behaviours.forEach(behaviour -> behaviour.execute(entity));
        if (!isFinished) {
            isFinished = true;

            for (Behaviour behaviour : behaviours) {
                if (!behaviour.isFinished()) {
                    isFinished = false;
                }
            }
        }
    }

    @Override
    public void reset() {
        behaviours.forEach(Behaviour::reset);
        isFinished = false;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
