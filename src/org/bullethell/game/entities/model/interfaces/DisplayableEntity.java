package org.bullethell.game.entities.model.interfaces;

import org.bullethell.game.graphics.components.Displayable;
import org.bullethell.game.graphics.components.GraphicsComponent;

public interface DisplayableEntity extends Displayable {
    /**
     * Retrieves the graphical component, the visual representation, of the entity.
     *
     * @return The {@link GraphicsComponent} if this entity, or null if none is set.
     * **/
    GraphicsComponent getGraphicsComponent();

    /**
     * Sets the graphical component for the entity.
     *
     * @param component The {@link GraphicsComponent} to associate with the entity.
     * **/
    void setGraphicsComponent(GraphicsComponent component);

    /**
     * Returns the width of the entity.
     *
     * @return The entity's width.
     **/
    default float getWidth() {
        return getGraphicsComponent() == null ? 0 : getGraphicsComponent().getWidth();
    }

    /**
     * Returns the height of the entity.
     *
     * @return The entity's height.
     **/
    default float getHeight() {
        return getGraphicsComponent() == null ? 0 : getGraphicsComponent().getHeight();
    }
}
