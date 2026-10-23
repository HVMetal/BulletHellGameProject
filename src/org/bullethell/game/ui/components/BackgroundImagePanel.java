package org.bullethell.game.ui.components;

import org.bullethell.game.graphics.components.bitmap.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public final class BackgroundImagePanel extends JPanel {
    private final BufferedImage background;

    public BackgroundImagePanel(String backgroundFilename) {
        setBackground(null);
        setFocusable(false);
        setOpaque(false);
        setLayout(new GridBagLayout());

        background = ImageUtils.loadImage(backgroundFilename);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}
