package org.bullethell.game.entities.implementation.enemy;

import org.bullethell.game.attackpatterns.AttackPattern;
import org.bullethell.game.entities.implementation.enemy.behaviour.misc.BonusValues;
import org.bullethell.game.entities.model.abstracts.EnemyModel;
import org.bullethell.game.entities.model.abstracts.GameEntityModel;
import org.bullethell.game.entities.physics.collision.hitbox.Hitbox;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.utils.Coordinates;

public class GenericEnemy extends EnemyModel {
    public GenericEnemy(Coordinates position, GraphicsComponent graphics, MovementComponent movement, Hitbox hitbox, AttackPattern attackPattern, float hitpoints, BonusValues bonusValues) {
        super(hitpoints, bonusValues);
        setCoordinates(position);
        setGraphicsComponent(graphics);
        setMovementComponent(movement);
        setHitbox(hitbox);
        setAttackPattern(attackPattern);
    }

    @Override
    public GameEntityModel cloneComponent() {
        return new GenericEnemy(
                getCoordinates(),
                (GraphicsComponent) getGraphicsComponent().cloneComponent(),
                (MovementComponent) getMovementComponent().cloneComponent(),
                (Hitbox) getHitbox().cloneComponent(),
                (AttackPattern) getAttackPattern().cloneComponent(),
                getOriginalHealth(), getBonusValues()
        );
    }
}
