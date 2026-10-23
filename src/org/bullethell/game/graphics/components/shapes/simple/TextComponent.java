package org.bullethell.game.graphics.components.shapes.simple;

import org.bullethell.game.entities.Cloneable;

import java.awt.*;

public class TextComponent extends SimpleShape {
    private final String text;
    private final Font font;

    public TextComponent(float x, float y, Color shapeColor, String text, Font font) {
        super(x, y, shapeColor);
        this.text = text;
        this.font = font;
    }

    @Override
    public Cloneable cloneComponent() {
        return new TextComponent(getX(), getY(), shapeColor, text, font);
    }

    @Override
    public void display(Graphics g) {
        super.display(g);
        g.setFont(font);
        g.drawString(text, (int) getX(), (int) getY());
    }
}
