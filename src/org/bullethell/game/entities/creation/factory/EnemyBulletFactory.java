package org.bullethell.game.entities.creation.factory;

import org.bullethell.game.entities.model.interfaces.EnemyBullet;
import org.bullethell.game.entities.physics.collision.hitbox.Hitbox;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.graphics.components.GraphicsComponent;

import java.util.ArrayList;
import java.util.List;

public final class EnemyBulletFactory implements PrototypeFactory<EnemyBullet> {
    private final static EnemyBulletFactory instance = new EnemyBulletFactory();

    private EnemyBulletFactory() {}

    public static EnemyBulletFactory getInstance() {
        return instance;
    }

    @Override
    public List<EnemyBullet> createEntities(int numberOfEntities, EnemyBullet prototype) {
        return createModels(numberOfEntities, prototype);
    }

    @Override
    public EnemyBullet createEntity(EnemyBullet prototype) {
        final List<EnemyBullet> bullets = createEntities(1, prototype);
        return bullets.getFirst();
    }

    private List<EnemyBullet> createModels(int numberOfEntities, EnemyBullet entityModel) {
        final List<EnemyBullet> bullets = new ArrayList<>(numberOfEntities);
        for(int i = 0; i < numberOfEntities; ++i) {
            final EnemyBullet bullet = (EnemyBullet) entityModel.cloneComponent();
            bullets.add(bullet);
        }

        return bullets;
    }
}
