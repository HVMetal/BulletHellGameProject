package org.bullethell.game.ui.components;

import org.bullethell.game.ui.FramePanel;
import org.bullethell.game.ui.GameFrame;

import javax.swing.*;
import java.awt.*;

public abstract class ActiveFramePanel extends JPanel implements FramePanel {
    protected final GameFrame gameFrame;

    public ActiveFramePanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        setBackground(null);
        setFocusable(false);
        setLayout(new GridBagLayout());
        setVisible(false);
        setOpaque(false);
    }

    @Override
    public void finalizePanelSetup() {
        revalidate();
        repaint(100);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(gameFrame.getWidth(), gameFrame.getHeight());
    }
}
