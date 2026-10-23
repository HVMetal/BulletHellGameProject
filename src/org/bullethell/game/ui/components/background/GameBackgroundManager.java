package org.bullethell.game.ui.components.background;

import org.bullethell.game.graphics.components.shapes.Shape;
import org.bullethell.game.graphics.components.shapes.composite.CompositeShape;
import org.bullethell.game.logic.game.GameManager;

import java.awt.*;

public final class GameBackgroundManager {
    private final CompositeShape backgroundObjects;
    private final Gradient backgroundGradient;

    public GameBackgroundManager(GameManager gameManager) {
        backgroundObjects = new CompositeShape();
        backgroundGradient = new Gradient(gameManager);

        backgroundObjects.add(new CompositeShape());
    }

    public boolean add(Shape component) {
        return backgroundObjects.add(component);
    }

    public Shape remove(Shape component) {
        if(component.equals(getComponents(0))) {
            return null;
        }
        return backgroundObjects.remove(component);
    }

    public Shape getComponents(int index) {
        return backgroundObjects.getChild(index);
    }

    public Gradient getGradient() {
        return backgroundGradient;
    }

    public void display(Graphics g) {
        backgroundGradient.display(g);
        backgroundObjects.display(g);
    }

    public void clear() {
        backgroundObjects.clear();
        backgroundGradient.clear();

        backgroundObjects.add(new CompositeShape());
    }

    public CompositeShape getReservedBackgroundComposite() {
         return (CompositeShape) getComponents(0);
    }
}
