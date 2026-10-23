package org.bullethell.game.entities.model.abstracts;

import org.bullethell.game.utils.KeyGenerator;

public abstract class AbstractEntity {
    private final int objectID;

    public AbstractEntity() {
        this.objectID = KeyGenerator.next();
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(o == null || this.getClass() != o.getClass()) {
            return false;
        }
        AbstractEntity object = (AbstractEntity) o;
        return (objectID == object.objectID);
    }

    @Override
    public int hashCode() {
        return objectID;
    }
}
