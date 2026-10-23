package org.bullethell.game.entities.implementation.enemybullet;

import org.bullethell.game.entities.model.abstracts.EnemyBulletModel;
import org.bullethell.game.entities.model.abstracts.GameEntityModel;
import org.bullethell.game.entities.physics.collision.hitbox.Hitbox;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.utils.Coordinates;

public class GenericEnemyBullet extends EnemyBulletModel {
    public GenericEnemyBullet(GraphicsComponent graphics, MovementComponent movement, Hitbox hitbox) {
        this(new Coordinates(), graphics, movement, hitbox);
    }

    public GenericEnemyBullet(Coordinates position, GraphicsComponent graphics, MovementComponent movement, Hitbox hitbox) {
        super();
        setCoordinates(position);
        setGraphicsComponent(graphics);
        setMovementComponent(movement);
        setHitbox(hitbox);
    }

    @Override
    public GameEntityModel cloneComponent() {
        return new GenericEnemyBullet(
                getCoordinates(),
                (GraphicsComponent) getGraphicsComponent().cloneComponent(),
                (MovementComponent) getMovementComponent().cloneComponent(),
                (Hitbox) getHitbox().cloneComponent()
        );
    }
}
