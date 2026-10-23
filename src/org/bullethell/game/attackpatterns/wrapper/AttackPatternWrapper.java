package org.bullethell.game.attackpatterns.wrapper;

import org.bullethell.game.attackpatterns.AttackPattern;
import org.bullethell.game.entities.model.interfaces.EnemyBullet;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

public abstract class AttackPatternWrapper implements AttackPattern {
    private final AttackPattern wrappedComponent;

    public AttackPatternWrapper(AttackPattern wrappedComponent) {
        this.wrappedComponent = wrappedComponent;
    }

    @Override
    public AttackPattern cloneComponent() {
        return (AttackPattern) wrappedComponent.cloneComponent();
    }

    @Override
    public void requestFire(GameManager gameManager, Coordinates coordinates) {
        wrappedComponent.requestFire(gameManager, coordinates);
    }

    @Override
    public void load(EnemyBullet enemyBulletPrototype) {
        wrappedComponent.load(enemyBulletPrototype);
    }

    @Override
    public void pause() {
        wrappedComponent.pause();
    }

    @Override
    public void resume() {
        wrappedComponent.resume();
    }

    @Override
    public boolean isPaused() {
        return wrappedComponent.isPaused();
    }

    @Override
    public int getFireRate() {
        return wrappedComponent.getFireRate();
    }
}
