package org.bullethell.game.entities.model.abstracts;

import org.bullethell.game.attackpatterns.AttackPattern;
import org.bullethell.game.audio.management.SoundCache;
import org.bullethell.game.entities.implementation.enemy.behaviour.misc.BonusValues;
import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.entities.model.interfaces.Enemy;
import org.bullethell.game.entities.physics.collision.CollisionVisitor;
import org.bullethell.game.entities.model.observer.EntityObserver;
import org.bullethell.game.entities.creation.BonusGenerator;
import org.bullethell.game.logic.game.GameManager;

import java.util.ArrayList;
import java.util.List;

public abstract class EnemyModel extends CollisionEntityModel implements Enemy {
    protected final List<EntityObserver> observers;
    protected AttackPattern attackPattern;

    protected final BonusValues bonusValues;
    protected final float originalHealth;
    protected float health;

    public EnemyModel() {
        this(1, new BonusValues());
    }

    public EnemyModel(float health, BonusValues bonusValues) {
        super();
        observers = new ArrayList<>();
        originalHealth = health;
        this.health = health;
        this.bonusValues = bonusValues;
    }

    @Override
    public final ObjectType getObjectType() {
        return ObjectType.ENEMY;
    }

    @Override
    public boolean shouldBeDestroyed() {
        return health <= 0
                || getCoordinates().getX() < -128
                || getCoordinates().getX() > getGameManager().getPlayfieldWidth() + 128
                || getCoordinates().getY() < -128
                || getCoordinates().getY() > getGameManager().getPlayfieldHeight() + 128
                || super.shouldBeDestroyed();
    }

    @Override
    public void onDestruction() {
        super.onDestruction();
        notifyObservers(observer -> observer.notifyOnDestruction(gameManager));
        BonusGenerator.execute(gameManager, coordinates.getX(), coordinates.getY(), bonusValues);
    }

    @Override
    public void coreEntityLoop() {
        super.coreEntityLoop();
        notifyObservers(observer -> observer.notifyOnMovement(coordinates));

        if(attackPattern != null) {
            attackPattern.requestFire(gameManager, getCoordinates());
        }
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public float getOriginalHealth() {
        return originalHealth;
    }

    @Override
    public void setHealth(float health) {
        this.health = health;
    }

    @Override
    public void setGameManager(GameManager gameManager) {
        super.setGameManager(gameManager);
        notifyObservers(observer -> observer.notifyOnGameReception(gameManager));
    }

    @Override
    public BonusValues getBonusValues() {
        return bonusValues;
    }

    @Override
    public AttackPattern getAttackPattern() {
        return attackPattern;
    }

    @Override
    public void setAttackPattern(AttackPattern attackPattern) {
        this.attackPattern = attackPattern;
    }

    @Override
    public List<EntityObserver> getObservers() {
        return observers;
    }

    @Override
    public void accept(CollisionVisitor visitor) {
        visitor.collide(this);
    }

    @Override
    public void collide(PlayerModel player) {
        setHealth(getHealth() - 10);
    }

    @Override
    public void collide(PlayerBulletModel playerBullet) {
        setHealth(getHealth() - playerBullet.getBulletDamage());
        SoundCache.play("enemy_hit");

        if(getHealth() <= 0) {
            SoundCache.play("enemy_defeated");
        }
    }
}
