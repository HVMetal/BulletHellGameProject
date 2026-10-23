package org.bullethell.game.entities.model.abstracts;

import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

import java.awt.Graphics;

public abstract class GameEntityModel extends AbstractEntity implements GameEntity {
    protected GraphicsComponent graphicsComponent;
    protected MovementComponent movementComponent;
    protected final Coordinates coordinates;

    protected GameManager gameManager;
    protected boolean isActive;

    public GameEntityModel() {
        super();

        coordinates = new Coordinates();
    }

    @Override
    public boolean shouldBeDestroyed() {
        return false;
    }

    @Override
    public void onDestruction() {
        setActive(false);
    }

    @Override
    public void coreEntityLoop() {
        if(getMovementComponent() != null) {
            getMovementComponent().calculateAndPosition(this);
        }
        if(isActive() && shouldBeDestroyed()) {
            setActive(false);
        }
    }

    @Override
    public void display(Graphics g) {
        if(graphicsComponent != null) {
            graphicsComponent.setCoordinates(coordinates);
            graphicsComponent.display(g);
        }
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public MovementComponent getMovementComponent() {
        return movementComponent;
    }

    @Override
    public void setMovementComponent(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    @Override
    public GraphicsComponent getGraphicsComponent() {
        return graphicsComponent;
    }

    @Override
    public void setGraphicsComponent(GraphicsComponent component) {
        this.graphicsComponent = component;
    }

    @Override
    public GameManager getGameManager() {
        return gameManager;
    }

    @Override
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        this.isActive = active;
    }
}
