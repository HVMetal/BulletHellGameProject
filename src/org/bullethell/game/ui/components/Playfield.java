package org.bullethell.game.ui.components;

import java.awt.*;

public final class Playfield extends Canvas {
    private final int playfieldWidth, playfieldHeight;

    public Playfield(GraphicsConfiguration config, int width, int height) {
        super(config);

        setSize(width, height);
        setBackground(null);
        setFocusable(false);

        playfieldWidth = width;
        playfieldHeight = height;
    }

    @Override
    public int getWidth() {
        return playfieldWidth;
    }

    @Override
    public int getHeight() {
        return playfieldHeight;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), getHeight());
    }
}
