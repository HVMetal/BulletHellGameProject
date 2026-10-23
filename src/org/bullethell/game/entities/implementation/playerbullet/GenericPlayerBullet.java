package org.bullethell.game.entities.implementation.playerbullet;

import org.bullethell.game.entities.model.abstracts.PlayerBulletModel;
import org.bullethell.game.entities.model.interfaces.GameEntity;

public class GenericPlayerBullet extends PlayerBulletModel {
    public GenericPlayerBullet(float damage) {
        super(damage);
    }

    @Override
    public GameEntity cloneComponent() {
        final GameEntity bullet = new GenericPlayerBullet(bulletDamage);
        bullet.setGraphicsComponent(getGraphicsComponent());

        return bullet;
    }
}
