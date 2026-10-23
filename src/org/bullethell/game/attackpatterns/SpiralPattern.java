package org.bullethell.game.attackpatterns;

import org.bullethell.game.audio.management.SoundCache;
import org.bullethell.game.entities.model.interfaces.EnemyBullet;
import org.bullethell.game.entities.creation.factory.PrototypeFactory;
import org.bullethell.game.utils.Coordinates;

import java.util.List;

public final class SpiralPattern extends AttackPatternImpl {
    private final int nrOfSpirals;
    private final float angleIncValue;

    private float angle;

    public SpiralPattern(PrototypeFactory<EnemyBullet> factory, int fireRate, EnemyBullet bulletPrototype, int nrOfSpirals, float angleIncValue) {
        this(factory, fireRate, bulletPrototype, nrOfSpirals, angleIncValue, (float) (Math.random() * 360));
    }

    public SpiralPattern(PrototypeFactory<EnemyBullet> factory, int fireRate, EnemyBullet bulletPrototype, int nrOfSpirals, float angleIncValue, float initAngleValue) {
        super(factory, fireRate);
        this.nrOfSpirals = nrOfSpirals;
        this.angleIncValue = angleIncValue;
        this.angle = initAngleValue;

        load(bulletPrototype);
    }

    @Override
    public AttackPattern cloneComponent() {
        return new SpiralPattern(prototypeFactory, fireRate, bulletPrototype, nrOfSpirals, angleIncValue, angle);
    }

    @Override
    public List<EnemyBullet> fire(Coordinates coordinates) {
        final List<EnemyBullet> enemyBullets = createBullets(nrOfSpirals);
        if(enemyBullets != null) {
            for(int i = 0; i < nrOfSpirals; ++i) {
                final EnemyBullet bullet = enemyBullets.get(i);

                bullet.setCoordinates(coordinates);
                bullet.getMovementComponent().setDirection(angle + i * 360f / nrOfSpirals);
            }

            SoundCache.play("shoot_1");
            offsetAngle();
        }

        return enemyBullets;
    }

    private void offsetAngle() {
        angle += angleIncValue;

        if (angle >= 360) {
            angle -= 360;
        } else {
            if (angle <= -360) {
                angle += 360;
            }
        }
    }
}
