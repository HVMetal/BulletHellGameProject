package org.bullethell.game.graphics.components.shapes.composite;

import org.bullethell.game.graphics.components.shapes.Shape;
import org.bullethell.game.graphics.components.shapes.simple.ShapeLine;

import java.awt.*;
import java.util.List;

/**Creates a grid of 'vLines' vertical and 'hLines' horizontal lines.**/
public class CustomCompositeGrid extends CustomCompositeLoopedSpace {
    private final int vLines, hLines;
    private final Color color;

    public CustomCompositeGrid(int vLines, int hLines, int compWidth, int compHeight, float vDisplacement, float hDisplacement, Color color) {
        super(hDisplacement, vDisplacement, compWidth, compHeight);
        this.vLines = vLines;
        this.hLines = hLines;
        this.color = color;

        final float vJump = (float) compHeight / vLines;
        final float hJump = (float) compWidth / hLines;

        for(int i = 0; i < vLines; i++) {
            shapesList.add(new ShapeLine(0, i * vJump, compWidth, i * vJump, color));
        }
        for(int i = 0; i < hLines; i++) {
            shapesList.add(new ShapeLine(i * hJump, 0, i * hJump, compHeight, color));
        }
    }

    @Override
    public Shape cloneComponent() {
        return new CustomCompositeGrid(vLines, hLines, compWidth, compHeight, vDisplacement, hDisplacement, color);
    }

    @Override
    public boolean add(Shape shape) {
        return false;
    }

    @Override
    public boolean addAll(Shape... shapes) {
        return false;
    }

    @Override
    public Shape remove(Shape shape) {
        return null;
    }

    @Override
    public List<Shape> removeAll() {
        return null;
    }

    @Override
    public void display(Graphics g) {
        for(int i = 0; i < vLines; i++) {
            shapesList.get(i).moveComponent(-hDisplacement, 0);
        }
        for(int i = 0; i < hLines; i++) {
            shapesList.get(i + vLines).moveComponent(0, -vDisplacement);
        }

        super.display(g);
    }
}
