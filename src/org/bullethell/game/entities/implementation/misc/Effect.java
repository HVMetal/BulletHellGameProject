package org.bullethell.game.entities.implementation.misc;

import org.bullethell.game.entities.model.abstracts.MiscellaneousModel;
import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.graphics.components.bitmap.Animation;
import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.utils.Coordinates;

public class Effect extends MiscellaneousModel {
    public Effect(GraphicsComponent graphicsComponent) {
        this(graphicsComponent, new Coordinates());
    }

    public Effect(GraphicsComponent graphicsComponent, Coordinates position) {
        super();
        setGraphicsComponent(graphicsComponent);
        setCoordinates(position);
    }

    @Override
    public Cloneable cloneComponent() {
        return new Effect((GraphicsComponent) getGraphicsComponent().cloneComponent(), getCoordinates());
    }

    @Override
    public boolean shouldBeDestroyed() {
        return (getGraphicsComponent() instanceof Animation animation && animation.isAnimationFinished())
                || !(getGraphicsComponent() instanceof Animation)
                || super.shouldBeDestroyed();
    }
}
