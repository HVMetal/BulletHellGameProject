package org.bullethell.game.entities.implementation.enemybullet.behaviour.wrapper;

import org.bullethell.game.entities.implementation.misc.Effect;
import org.bullethell.game.entities.model.interfaces.EnemyBullet;
import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.model.wrappers.EnemyBulletWrapper;

public final class EnemyBulletPostEffectWrapper extends EnemyBulletWrapper {
    private final Effect effect;

    public EnemyBulletPostEffectWrapper(EnemyBullet entity, Effect effect) {
        super(entity);
        this.effect = effect;
    }

    @Override
    public GameEntity cloneComponent() {
        return new EnemyBulletPostEffectWrapper(
                (EnemyBullet) super.cloneComponent(),
                (Effect) effect.cloneComponent()
        );
    }

    @Override
    public void onDestruction() {
        super.getGameManager().add(effect);
        effect.setCoordinates(super.getCoordinates());
        super.onDestruction();
    }
}
