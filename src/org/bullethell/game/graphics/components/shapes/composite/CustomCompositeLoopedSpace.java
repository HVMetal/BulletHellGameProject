package org.bullethell.game.graphics.components.shapes.composite;

import org.bullethell.game.graphics.components.shapes.Shape;

import java.awt.*;

/**Creates a looped space: composite objects exiting on one side
 * of the frame are warped to the opposite side.**/
public class CustomCompositeLoopedSpace extends CompositeShape {
    protected float hDisplacement, vDisplacement;
    protected final int compWidth, compHeight;

    public CustomCompositeLoopedSpace(float hDisplacement, float vDisplacement, int compWidth, int compHeight, Shape... shapes) {
        this.vDisplacement = vDisplacement;
        this.hDisplacement = hDisplacement;
        this.compWidth = compWidth;
        this.compHeight = compHeight;

        this.addAll(shapes);
    }

    @Override
    public Shape cloneComponent() {
        return new CustomCompositeLoopedSpace(hDisplacement, vDisplacement, compWidth, compHeight, cloneShapesList().toArray(new Shape[0]));
    }

    @Override
    public void display(Graphics g) {
        shapesList.forEach(shape -> {
            shape.moveComponent(hDisplacement, vDisplacement);
            offsetComponent(shape);
        });

        super.display(g);
    }

    protected void offsetComponent(Shape shape) {
        final float shapeX = shape.getX(), shapeY = shape.getY();
        final float shapeH = shape.getHeight(), shapeW = shape.getWidth();

        if(shapeX > compWidth && hDisplacement > 0) {
            shape.setCoordinates(-shapeW, shapeY);
        }
        if((shapeX + shapeW) < 0 && hDisplacement < 0) {
            shape.setCoordinates(compWidth + shapeW, shapeY);
        }
        if(shapeY > compHeight && vDisplacement > 0) {
            shape.setCoordinates(shapeX, -shapeH);
        }
        if((shapeY + shapeH) < 0 && vDisplacement < 0) {
            shape.setCoordinates(shapeX, compHeight + shapeH);
        }
    }

    public void setHorizontalDisplacement(float hDisplacement) {
        this.hDisplacement = hDisplacement;
    }

    public void setVerticalDisplacement(float vDisplacement) {
        this.vDisplacement = vDisplacement;
    }
}
