package org.bullethell.game.entities.model.abstracts;

import org.bullethell.game.entities.implementation.player.behaviour.misc.PlayerCannon;
import org.bullethell.game.entities.implementation.player.behaviour.physics.PlayerMovement;
import org.bullethell.game.entities.implementation.player.behaviour.misc.PlayerValues;
import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.entities.model.interfaces.Player;
import org.bullethell.game.entities.physics.collision.CollisionVisitor;
import org.bullethell.game.entities.model.observer.EntityObserver;
import org.bullethell.game.logic.game.GameManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public abstract class PlayerModel extends CollisionEntityModel implements Player {
    protected final List<EntityObserver> observers;
    protected final PlayerValues playerValues;
    protected final PlayerCannon cannon;

    private final int invincibilityDuration = 200;
    private int invicibilityLeft;

    public PlayerModel(float normalSpeed, float focusedSpeed, PlayerCannon playerCannon) {
        super();
        observers = new ArrayList<>();
        playerValues = new PlayerValues();
        cannon = playerCannon;

        setMovementComponent(new PlayerMovement(getWidth(), getHeight(), normalSpeed, focusedSpeed));

        addObserver((PlayerMovement) movementComponent);
        addObserver(cannon);
    }

    @Override
    public final ObjectType getObjectType() {
        return ObjectType.PLAYER;
    }

    @Override
    public void onDestruction() {
        super.onDestruction();
        notifyObservers(observer -> observer.notifyOnDestruction(gameManager));
    }

    @Override
    public void coreEntityLoop() {
        super.coreEntityLoop();
        notifyObservers(observer -> observer.notifyOnMovement(coordinates));

        if(invicibilityLeft > 0) {
            invicibilityLeft--;
        }
    }

    @Override
    public void display(Graphics g) {
        if(isInvincibilityOff() || invicibilityLeft % 2 == 0) {
            super.display(g);
        }
    }

    @Override
    public void setGameManager(GameManager gameManager) {
        super.setGameManager(gameManager);
        notifyObservers(observer -> observer.notifyOnGameReception(gameManager));
    }

    @Override
    public PlayerValues getPlayerStats() {
        return playerValues;
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
    public void collide(BonusModel bonus) {
        bonus.applyBonus(this);
    }

    @Override
    public void collide(EnemyModel enemy) {
        if(isInvincibilityOff()) {
            notifyObservers(EntityObserver::notifyOnHitTaken);
            playerValues.decrementLives(1);
            triggerInvicibility();
        }
    }

    @Override
    public void collide(EnemyBulletModel enemyBullet) {
        if(isInvincibilityOff()) {
            notifyObservers(EntityObserver::notifyOnHitTaken);
            playerValues.decrementLives(1);
            triggerInvicibility();
        }
    }

    private void triggerInvicibility() {
        invicibilityLeft = invincibilityDuration;
    }

    private boolean isInvincibilityOff() {
        return invicibilityLeft <= 0;
    }
}
