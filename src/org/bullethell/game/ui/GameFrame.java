package org.bullethell.game.ui;

import java.awt.event.KeyListener;

public interface GameFrame {
    void closeFrame();
    void setActivePanel(FramePanel panel);

    int getWidth();
    int getHeight();

    boolean isFullscreenOn();

    void addKeyListener(KeyListener keyListener);
    void removeKeyListener(KeyListener keyListener);
}
