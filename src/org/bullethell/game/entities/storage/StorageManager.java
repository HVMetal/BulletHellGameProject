package org.bullethell.game.entities.storage;

import org.bullethell.game.entities.model.interfaces.GameEntity;

import java.util.List;

public final class StorageManager {
    private final ActiveEntityStorage activeStorage;

    public StorageManager() {
        this.activeStorage = new ActiveEntityStorage();
    }

    public void add(GameEntity model) {
        activeStorage.add(model);
    }

    public void remove(GameEntity model) {
        activeStorage.remove(model);
    }

    public List<GameEntity> get(ObjectType type) {
        return activeStorage.get(type);
    }

    public void clear() {
        activeStorage.clear();
    }
}
