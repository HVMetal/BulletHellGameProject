package org.bullethell.game.attackpatterns.wrapper;

import org.bullethell.game.attackpatterns.AttackPattern;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

public final class AttackDurationWrapper extends AttackPatternWrapper {
    private final int duration;
    private int timer;

    public AttackDurationWrapper(AttackPattern wrappedComponent, int duration) {
        super(wrappedComponent);
        this.duration = duration;
    }

    @Override
    public AttackPattern cloneComponent() {
        return new AttackDurationWrapper(super.cloneComponent(), duration);
    }

    @Override
    public void requestFire(GameManager gameManager, Coordinates coordinates) {
        if(timer < duration) {
            timer++;
            super.requestFire(gameManager, coordinates);
        }
    }
}
