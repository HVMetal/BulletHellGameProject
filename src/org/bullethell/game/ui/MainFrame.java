package org.bullethell.game.ui;

import kuusisto.tinysound.TinySound;
import org.bullethell.game.ui.components.BackgroundImagePanel;
import org.bullethell.game.ui.components.MenuPanel;
import org.bullethell.game.utils.CacheInitializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame implements GameFrame {
    private final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    private final JPanel contentPanel;
    private FramePanel activeFramePanel;
    private boolean isFullscreenOn;

    private final static int FRAME_WINDOWED_WIDTH = 702;
    private final static int FRAME_WINDOWED_HEIGHT = 720;

    static {
        TinySound.init();
        CacheInitializer.initializeSpriteCaches();
        CacheInitializer.initializeSoundCaches();
    }

    public MainFrame() {
        setTitle("Game Project");
        setFocusable(true);
        setResizable(false);
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.BLACK);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeFrame();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(!isFullscreenOn() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_F) {
                    fullscreenMode();
                }
                if(isFullscreenOn() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W) {
                    windowedMode();
                }
            }
        });

        contentPanel = new BackgroundImagePanel("DeepSpace");
        add(contentPanel, BorderLayout.CENTER);

        setActivePanel(new MenuPanel(this));
        setVisible(true);
    }

    @Override
    public void closeFrame() {
        TinySound.shutdown();
        dispose();
        System.exit(0);
    }

    @Override
    public void setActivePanel(FramePanel framePanel) {
        if(activeFramePanel != null) {
            contentPanel.remove((Component) activeFramePanel);
        }
        contentPanel.add((Component) framePanel);
        pack();

        activeFramePanel = framePanel;
        activeFramePanel.finalizePanelSetup();

        revalidate();
        repaint(100);
        setLocationRelativeTo(null);

        ((Component) activeFramePanel).setVisible(true);
    }

    @Override
    public int getWidth() {
        return (!isFullscreenOn() ? FRAME_WINDOWED_WIDTH + getInsets().left + getInsets().right
                : Toolkit.getDefaultToolkit().getScreenSize().width);
    }

    @Override
    public int getHeight() {
        return (!isFullscreenOn() ? FRAME_WINDOWED_HEIGHT + getInsets().top + getInsets().bottom
                : Toolkit.getDefaultToolkit().getScreenSize().height);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), getHeight());
    }

    @Override
    public boolean isFullscreenOn() {
        return isFullscreenOn;
    }

    private void windowedMode() {
        isFullscreenOn = false;
        dispose();
        setUndecorated(false);

        device.setFullScreenWindow(null);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void fullscreenMode() {
        isFullscreenOn = true;
        dispose();
        setUndecorated(true);

        device.setFullScreenWindow(this);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
