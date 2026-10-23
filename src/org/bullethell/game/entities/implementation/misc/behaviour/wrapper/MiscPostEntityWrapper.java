package org.bullethell.game.entities.implementation.misc.behaviour.wrapper;

import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.model.interfaces.Miscellaneous;
import org.bullethell.game.entities.model.wrappers.MiscellaneousWrapper;

public class MiscPostEntityWrapper extends MiscellaneousWrapper {
    private final GameEntity entity;

    public MiscPostEntityWrapper(Miscellaneous wrappedEntity, GameEntity entity) {
        super(wrappedEntity);
        this.entity = entity;
    }

    @Override
    public GameEntity cloneComponent() {
        return new MiscPostEntityWrapper((Miscellaneous) wrappedEntity.cloneComponent(), (GameEntity) entity.cloneComponent());
    }

    @Override
    public void onDestruction() {
        super.getGameManager().add(entity);
        entity.setCoordinates(super.getCoordinates());
        super.onDestruction();
    }
}
