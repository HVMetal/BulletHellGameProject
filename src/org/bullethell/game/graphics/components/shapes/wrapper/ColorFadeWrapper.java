package org.bullethell.game.graphics.components.shapes.wrapper;

import org.bullethell.game.graphics.components.GraphicsComponent;
import org.bullethell.game.graphics.components.shapes.Shape;
import org.bullethell.game.utils.easingfunction.EasingFunction;

import java.awt.*;

public final class ColorFadeWrapper extends ShapeComponentWrapper {
    private final Color oldColor, newColor;
    private final int duration;
    private final EasingFunction easingFunction;

    private float time;

    public ColorFadeWrapper(Shape wrappedComponent, Color newColor, int duration, EasingFunction easingFunction) {
        super(wrappedComponent);
        this.oldColor = wrappedComponent.getColor();
        this.newColor = newColor;
        this.duration = duration;
        this.easingFunction = easingFunction;
    }

    @Override
    public GraphicsComponent cloneComponent() {
        return new ColorFadeWrapper(
                (Shape) super.cloneComponent(),
                newColor, duration, easingFunction
        );
    }

    @Override
    public void display(Graphics g) {
        if(time < 1) {
            time += 1f / duration;

            final int red = calculateColor(oldColor.getRed(), newColor.getRed());
            final int green = calculateColor(oldColor.getGreen(), newColor.getGreen());
            final int blue = calculateColor(oldColor.getBlue(), newColor.getBlue());

            super.setColor(new Color(red, green, blue));
        }

        super.display(g);
    }

    private int calculateColor(int oldColor, int newColor) {
        return (int) (oldColor + easingFunction.function(time) * (newColor - oldColor));
    }
}
