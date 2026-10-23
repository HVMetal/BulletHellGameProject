package org.bullethell.game.entities.model.wrappers;

import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

import java.awt.*;

public abstract class GameEntityWrapper<T extends GameEntity> implements GameEntity {
    protected final T wrappedEntity;

    public GameEntityWrapper(T wrappedEntity) {
        this.wrappedEntity = wrappedEntity;
    }

    @Override
    public ObjectType getObjectType() {
        return wrappedEntity.getObjectType();
    }

    @Override
    public GameEntity cloneComponent() {
        return (GameEntity) wrappedEntity.cloneComponent();
    }

    @Override
    public boolean shouldBeDestroyed() {
        return wrappedEntity.shouldBeDestroyed();
    }

    @Override
    public void onDestruction() {
        wrappedEntity.onDestruction();
    }

    @Override
    public void coreEntityLoop() {
        wrappedEntity.coreEntityLoop();
    }

    @Override
    public Coordinates getCoordinates() {
        return wrappedEntity.getCoordinates();
    }

    @Override
    public void setCoordinates(float x, float y) {
        wrappedEntity.setCoordinates(x, y);
    }

    @Override
    public void setCoordinates(Coordinates position) {
        wrappedEntity.setCoordinates(position);
    }

    @Override
    public float getWidth() {
        return wrappedEntity.getWidth();
    }

    @Override
    public float getHeight() {
        return wrappedEntity.getHeight();
    }

    @Override
    public GraphicsComponent getGraphicsComponent() {
        return wrappedEntity.getGraphicsComponent();
    }

    @Override
    public void setGraphicsComponent(GraphicsComponent component) {
        wrappedEntity.setGraphicsComponent(component);
    }

    @Override
    public MovementComponent getMovementComponent() {
        return wrappedEntity.getMovementComponent();
    }

    @Override
    public void setMovementComponent(MovementComponent component) {
        wrappedEntity.setMovementComponent(component);
    }

    @Override
    public GameManager getGameManager() {
        return wrappedEntity.getGameManager();
    }

    @Override
    public void setGameManager(GameManager gameManager) {
        wrappedEntity.setGameManager(gameManager);
    }

    @Override
    public boolean isActive() {
        return wrappedEntity.isActive();
    }

    @Override
    public void setActive(boolean active) {
        wrappedEntity.setActive(active);
    }

    @Override
    public void display(Graphics g) {
        wrappedEntity.display(g);
    }
}
