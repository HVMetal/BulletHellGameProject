package org.bullethell.game.entities.creation.builder;

import org.bullethell.game.attackpatterns.AttackPattern;
import org.bullethell.game.entities.model.interfaces.Enemy;
import org.bullethell.game.entities.physics.collision.hitbox.Hitbox;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.entities.model.observer.EntityObserver;
import org.bullethell.game.utils.Coordinates;

public interface EnemyBuilder {
    EnemyBuilder setPosition(float x, float y);
    default EnemyBuilder setPosition(Coordinates coordinates) {
        return this.setPosition(coordinates.getX(), coordinates.getY());
    }
    EnemyBuilder setGraphicsComponent(GraphicsComponent graphicsComponent);
    EnemyBuilder setMovementComponent(MovementComponent movementComponent);
    EnemyBuilder setHitbox(Hitbox hitbox);
    EnemyBuilder setAttackPattern(AttackPattern attackPattern);
    EnemyBuilder setHealth(float health);
    EnemyBuilder setLifeValue(int lifeValue);
    EnemyBuilder setBombValue(int bombValue);
    EnemyBuilder setScoreValue(int scoreValue);
    EnemyBuilder setPowerValue(int powerValue);
    EnemyBuilder addObserver(EntityObserver observer);

    Enemy build();
}
