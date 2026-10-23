package org.bullethell.game.attackpatterns;

import org.bullethell.game.entities.model.interfaces.EnemyBullet;
import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

public interface AttackPattern extends Cloneable {
    void requestFire(GameManager gameManager, Coordinates coordinates);
    void load(EnemyBullet enemyBulletPrototype);

    void pause();
    void resume();
    boolean isPaused();

    int getFireRate();
}
