package org.bullethell.game.attackpatterns;

import org.bullethell.game.entities.model.interfaces.EnemyBullet;
import org.bullethell.game.entities.creation.factory.PrototypeFactory;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public abstract class AttackPatternImpl implements AttackPattern {
    protected final PrototypeFactory<EnemyBullet> prototypeFactory;
    protected final int fireRate;

    protected EnemyBullet bulletPrototype;
    protected int time;
    protected boolean isPaused;

    public AttackPatternImpl(PrototypeFactory<EnemyBullet> factory, int fireRate) {
        this.prototypeFactory = factory;
        this.fireRate = fireRate;
    }

    public abstract List<EnemyBullet> fire(Coordinates coordinates);

    @Override
    public void requestFire(GameManager gameManager, Coordinates coordinates) {
        if(!isPaused() && bulletPrototype != null) {
            if(time >= fireRate) {
                time = 0;

                final List<EnemyBullet> enemyBullets = fire(coordinates);
                enemyBullets.forEach(gameManager::add);
            } else {
                time++;
            }
        }
    }

    @Override
    public void load(EnemyBullet enemyBulletPrototype) {
        bulletPrototype = enemyBulletPrototype;
    }

    @Override
    public int getFireRate() {
        return fireRate;
    }

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {
        isPaused = false;
    }

    @Override
    public boolean isPaused() {
        return isPaused;
    }

    protected List<EnemyBullet> createBullets(int number) {
        return prototypeFactory.createEntities(number, bulletPrototype);
    }
}
