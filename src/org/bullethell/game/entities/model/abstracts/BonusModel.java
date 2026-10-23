package org.bullethell.game.entities.model.abstracts;

import org.bullethell.game.entities.physics.collision.hitbox.Hitbox;
import org.bullethell.game.entities.physics.movement.LinearMovement;
import org.bullethell.game.entities.physics.movement.wrapper.KinematicVelocityLimitWrapper;
import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.entities.model.interfaces.Bonus;
import org.bullethell.game.entities.physics.collision.CollisionVisitor;

public abstract class BonusModel extends CollisionEntityModel implements Bonus {
    protected final int bonusValue;

    public BonusModel(Hitbox hitbox, GraphicsComponent graphicsComponent, int bonusValue) {
        super();
        this.bonusValue = bonusValue;

        setHitbox(hitbox);
        setGraphicsComponent(graphicsComponent);
        setMovementComponent(new KinematicVelocityLimitWrapper(
                new LinearMovement(90, -1, 0.0125f),
                2.5f
        ));
    }

    @Override
    public final ObjectType getObjectType() {
        return ObjectType.BONUS;
    }

    @Override
    public boolean shouldBeDestroyed() {
        return (getCoordinates().getY() > (gameManager.getPlayfieldHeight()) + 2 * getHeight());
    }

    @Override
    public int getBonusValue() {
        return bonusValue;
    }

    @Override
    public void accept(CollisionVisitor visitor) {
        visitor.collide(this);
    }

    @Override
    public void collide(PlayerModel player) {
        onDestruction();
    }
}
