package org.bullethell.game.logic.game.behaviour;

import org.bullethell.game.entities.EntityUtils;
import org.bullethell.game.entities.model.interfaces.GameEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequentialBehaviours implements Behaviour {
    private final List<Behaviour> behaviours = new ArrayList<>();

    private int currentBehaviour;
    private boolean isFinished;

    public SequentialBehaviours(Behaviour... behaviours) {
        this.behaviours.addAll(Arrays.asList(behaviours));
    }

    @Override
    public Behaviour cloneComponent() {
        final List<Behaviour> clonedBehaviours = EntityUtils.cloneList(behaviours);
        return new SequentialBehaviours(clonedBehaviours.toArray(new Behaviour[0]));
    }

    @Override
    public void execute(GameEntity entity) {
        if (currentBehaviour < behaviours.size()) {
            behaviours.get(currentBehaviour).execute(entity);
            if (behaviours.get(currentBehaviour).isFinished()) {
                currentBehaviour++;
            }
        } else {
            isFinished = true;
        }
    }

    @Override
    public void reset() {
        behaviours.forEach(Behaviour::reset);
        isFinished = false;
        currentBehaviour = 0;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
