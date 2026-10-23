package org.bullethell.game.ui.display;

import org.bullethell.game.logic.game.GameManager;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public final class GameDisplayManager {
    private final GameManager gameManager;

    private final GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice().getDefaultConfiguration();

    private final BufferedImage background;
    private final Graphics2D backgroundGraphics;
    private BufferStrategy bufferStrategy;
    private Graphics2D graphics;

    public GameDisplayManager(GameManager gameManager) {
        this.gameManager = gameManager;

        background = create(gameManager.getPlayfieldWidth(), gameManager.getPlayfieldHeight());
        backgroundGraphics = (Graphics2D) background.getGraphics();
    }

    public void finalizeSetup(Canvas canvas) {
        do {
            bufferStrategy = canvas.getBufferStrategy();
        } while (bufferStrategy == null);
    }

    public GraphicsConfiguration getGraphicsConfiguration() {
        return config;
    }

    public void repaintField() {
        do {
            Graphics2D bg = getBuffer();
            gameManager.repaintPlayfield(backgroundGraphics);
            if(bg != null) {
                bg.drawImage(background, 0, 0, null);
                bg.dispose();
            }
        } while(!updateScreen());
    }

    public BufferedImage captureScreen() {
        final BufferedImage screenCapture = create(gameManager.getPlayfieldWidth(), gameManager.getPlayfieldHeight());
        final Graphics2D g2d = screenCapture.createGraphics();
        g2d.drawImage(background, 0, 0, null);
        g2d.dispose();

        return screenCapture;
    }

    public void dispose() {
        if(backgroundGraphics != null) {
            backgroundGraphics.dispose();
        }
        if(graphics != null) {
            graphics.dispose();
        }
    }

    private Graphics2D getBuffer() {
        if(graphics == null) {
            try {
                graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
            } catch(IllegalStateException e) {
                return null;
            }
        }
        return graphics;
    }

    private boolean updateScreen() {
        graphics.dispose();
        graphics = null;
        try {
            bufferStrategy.show();
            Toolkit.getDefaultToolkit().sync();
            return (!bufferStrategy.contentsLost());
        }
        catch(IllegalStateException | NullPointerException e) {
            return true;
        }
    }

    private BufferedImage create(int panelWidth, int panelHeight) {
        return config.createCompatibleImage(panelWidth, panelHeight, Transparency.OPAQUE);
    }
}
