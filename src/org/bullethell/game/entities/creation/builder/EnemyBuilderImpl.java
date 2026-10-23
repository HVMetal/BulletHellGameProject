package org.bullethell.game.entities.creation.builder;

import org.bullethell.game.attackpatterns.AttackPattern;
import org.bullethell.game.entities.implementation.enemy.GenericEnemy;
import org.bullethell.game.entities.implementation.enemy.behaviour.misc.BonusValues;
import org.bullethell.game.entities.model.interfaces.Enemy;
import org.bullethell.game.entities.physics.collision.hitbox.CircularHitbox;
import org.bullethell.game.entities.physics.collision.hitbox.Hitbox;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.entities.model.observer.EntityObserver;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public final class EnemyBuilderImpl implements EnemyBuilder {
    private final GameManager gameManager;
    private final Coordinates position;
    private final List<EntityObserver> observers;

    private GraphicsComponent graphicsComponent;
    private MovementComponent movementComponent;
    private Hitbox hitbox;
    private AttackPattern attackPattern;

    private float hitPoints;
    private int lifeValue, bombValue, scoreValue, powerValue;

    public EnemyBuilderImpl(GameManager gameManager) {
        this.gameManager = gameManager;
        position = new Coordinates();
        observers = new ArrayList<>();

        lifeValue = bombValue = scoreValue = powerValue = 0;
        hitbox = new CircularHitbox(1);
        hitPoints = 1;
    }

    @Override
    public EnemyBuilder setPosition(float x, float y) {
        position.setX(x);
        position.setY(y);
        return this;
    }

    @Override
    public EnemyBuilder setGraphicsComponent(GraphicsComponent graphicsComponent) {
        this.graphicsComponent = graphicsComponent;
        return this;
    }

    @Override
    public EnemyBuilder setMovementComponent(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
        return this;
    }

    @Override
    public EnemyBuilder setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
        return this;
    }

    @Override
    public EnemyBuilder setAttackPattern(AttackPattern attackPattern) {
        this.attackPattern = attackPattern;
        return this;
    }

    @Override
    public EnemyBuilder setHealth(float health) {
        this.hitPoints = health;
        return this;
    }

    @Override
    public EnemyBuilder setLifeValue(int lifeValue) {
        this.lifeValue = lifeValue;
        return this;
    }

    @Override
    public EnemyBuilder setBombValue(int bombValue) {
        this.bombValue = bombValue;
        return this;
    }

    @Override
    public EnemyBuilder setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
        return this;
    }

    @Override
    public EnemyBuilder setPowerValue(int powerValue) {
        this.powerValue = powerValue;
        return this;
    }

    @Override
    public EnemyBuilder addObserver(EntityObserver observer) {
        observers.add(observer);
        return this;
    }

    @Override
    public Enemy build() {
        final BonusValues bonusValues = new BonusValues(scoreValue, powerValue, bombValue, lifeValue);
        final Enemy enemy = new GenericEnemy(
                position, graphicsComponent, movementComponent,
                hitbox, attackPattern, hitPoints, bonusValues
        );
        enemy.setGameManager(gameManager);
        if(!observers.isEmpty()) {
            observers.forEach(enemy::addObserver);
        }

        return enemy;
    }
}
