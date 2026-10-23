package org.bullethell.game.entities.storage;

import org.bullethell.game.entities.model.interfaces.GameEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ActiveEntityStorage {
    private final Map<ObjectType, List<GameEntity>> entityStorage;

    public ActiveEntityStorage() {
        entityStorage = new HashMap<>();

        for(ObjectType type : ObjectType.values()) {
            entityStorage.put(type, new ArrayList<>());
        }
    }

    public void add(GameEntity model) {
        entityStorage.get(model.getObjectType()).add(model);
    }

    public void remove(GameEntity model) {
        entityStorage.get(model.getObjectType()).remove(model);
    }

    public List<GameEntity> get(ObjectType type) {
        return entityStorage.get(type);
    }

    public void clear() {
        for(ObjectType type : ObjectType.values()) {
            entityStorage.get(type).clear();
        }
    }
}
