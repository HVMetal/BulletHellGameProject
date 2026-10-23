package org.bullethell.game.entities.implementation.misc;

import org.bullethell.game.entities.model.abstracts.MiscellaneousModel;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.graphics.components.shapes.simple.TextComponent;
import org.bullethell.game.entities.Cloneable;

public class TextEntity extends MiscellaneousModel {
    public TextEntity(TextComponent textComponent, MovementComponent movementComponent) {
        super();
        setGraphicsComponent(textComponent);
        setMovementComponent(movementComponent);
    }

    @Override
    public Cloneable cloneComponent() {
        return new TextEntity(
                (TextComponent) getGraphicsComponent().cloneComponent(),
                (MovementComponent) getMovementComponent().cloneComponent()
        );
    }

    @Override
    public boolean shouldBeDestroyed() {
        return getMovementComponent().hasFinished() || super.shouldBeDestroyed();
    }
}
