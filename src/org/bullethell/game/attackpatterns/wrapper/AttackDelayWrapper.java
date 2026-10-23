package org.bullethell.game.attackpatterns.wrapper;

import org.bullethell.game.attackpatterns.AttackPattern;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

public final class AttackDelayWrapper extends AttackPatternWrapper {
    private final int delay;
    private int timer;

    public AttackDelayWrapper(AttackPattern wrappedComponent, int delay) {
        super(wrappedComponent);
        this.delay = delay;
    }

    @Override
    public AttackPattern cloneComponent() {
        return new AttackDelayWrapper(super.cloneComponent(), delay);
    }

    @Override
    public void requestFire(GameManager gameManager, Coordinates coordinates) {
        if (timer < delay) {
            timer++;
        } else {
            super.requestFire(gameManager, coordinates);
        }
    }
}
