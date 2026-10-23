package org.bullethell.game.entities.creation.factory;

import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.utils.Coordinates;

public interface ObjectFactory<T extends GameEntity> {
    T create(Coordinates coordinates);
}
