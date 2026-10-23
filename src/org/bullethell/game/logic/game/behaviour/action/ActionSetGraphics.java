package org.bullethell.game.logic.game.behaviour.action;

import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.logic.game.behaviour.Action;

public class ActionSetGraphics implements Action {
    private final GraphicsComponent graphicsComponent;

    private boolean isFinished;

    public ActionSetGraphics(GraphicsComponent graphicsComponent) {
        this.graphicsComponent = graphicsComponent;
    }

    @Override
    public Action cloneComponent() {
        return new ActionSetGraphics((GraphicsComponent) graphicsComponent.cloneComponent());
    }

    @Override
    public void execute(GameEntity entity) {
        if (!isFinished) {
            entity.setGraphicsComponent(graphicsComponent);
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
