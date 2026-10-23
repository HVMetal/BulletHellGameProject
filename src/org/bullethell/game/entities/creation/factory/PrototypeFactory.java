package org.bullethell.game.entities.creation.factory;

import org.bullethell.game.entities.model.interfaces.GameEntity;

import java.util.List;

public interface PrototypeFactory<T extends GameEntity> {
    List<T> createEntities(int numberOfEntities, T prototype);
    T createEntity(T prototype);
}
