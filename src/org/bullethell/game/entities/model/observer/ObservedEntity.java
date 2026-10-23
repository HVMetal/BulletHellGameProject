package org.bullethell.game.entities.model.observer;

import java.util.Iterator;
import java.util.List;

public interface ObservedEntity {
    List<EntityObserver> getObservers();

    default EntityObserver findObserver(EntityObserver observer) {
        final List<EntityObserver> observers = getObservers();
        for(EntityObserver o : observers) {
            if(o.equals(observer)) {
                return o;
            }
        }
        return null;
    }

    default void addObserver(EntityObserver observer) {
        final EntityObserver o = findObserver(observer);
        if(o == null) {
            getObservers().add(observer);
        }
    }

    default void removeObserver(EntityObserver observer) {
        final EntityObserver o = findObserver(observer);
        if(o != null) {
            getObservers().remove(o);
        }
    }

    default void notifyObservers(NotificationMethod notifyMethod) {
        final List<EntityObserver> observers = getObservers();
        for(Iterator<EntityObserver> it = observers.iterator(); it.hasNext(); ) {
            final EntityObserver observer = it.next();
            if(observer.isDestroyed()) {
                it.remove();
            } else {
                notifyMethod.notify(observer);
            }
        }
    }
}
