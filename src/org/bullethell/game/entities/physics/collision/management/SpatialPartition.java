package org.bullethell.game.entities.physics.collision.management;

import java.util.Set;

public interface SpatialPartition<T> {
    void add(T object);
    void remove(T object);
    void update(T object);
    Set<T> getObjectsNearby(T object);

    void clear();
}
