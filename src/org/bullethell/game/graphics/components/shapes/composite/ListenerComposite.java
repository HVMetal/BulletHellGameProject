package org.bullethell.game.graphics.components.shapes.composite;

import org.bullethell.game.entities.model.observer.EntityObserver;
import org.bullethell.game.graphics.components.shapes.Shape;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

public class ListenerComposite extends CompositeShape implements EntityObserver {
    public ListenerComposite(Shape... shapes) {
        super(shapes);
    }

    @Override
    public void notifyOnGameReception(GameManager gameManager) {
        gameManager.getBackgroundManager().add(this);
    }

    @Override
    public void notifyOnDestruction(GameManager gameManager) {
        gameManager.getBackgroundManager().remove(this);
    }

    @Override
    public void notifyOnMovement(Coordinates coordinates) {
        setCoordinates(coordinates);
    }
}
