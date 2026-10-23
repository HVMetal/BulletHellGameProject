package org.bullethell.game.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public final class GameOverScreen extends JPanel {
    private BufferedImage backgroundImage;

    public GameOverScreen(int width, int height, ActionListener restartAction, ActionListener exitAction) {
        setBounds(0, 0, width, height);
        setBackground(new Color(0, 0, 0, 175));
        setOpaque(false);
        setLayout(new GridBagLayout());

        GameOverDialog dialog = new GameOverDialog(192, 112);
        dialog.setRestartAction(restartAction);
        dialog.setExitAction(exitAction);

        this.add(dialog);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        g.setColor(getBackground());
        final Rectangle r = g.getClipBounds();
        g.fillRect(r.x - 1, r.y - 1, r.width + 2, r.height + 2);
    }

    public void setBackground(BufferedImage img) {
        this.backgroundImage = img;
    }

    private static class GameOverDialog extends JPanel {
        private final JButton restartButton, exitButton;
        private final int width, height;

        private final static int GAP = 8;

        public GameOverDialog(int width, int height) {
            this.width = width;
            this.height = height;

            setBackground(Color.BLACK);
            setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            setLayout(new GridBagLayout());
            setFocusable(false);

            JPanel contentPanel = new JPanel(new GridLayout(0, 1, GAP, GAP));
            contentPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
            contentPanel.setOpaque(false);

            JLabel pauseLabel = new JLabel("Game Over.", SwingConstants.CENTER);
            pauseLabel.setForeground(Color.WHITE);
            pauseLabel.setFont(new Font("Arial", Font.BOLD, 16));

            restartButton = new JButton("Restart stage?");
            restartButton.setFocusable(false);

            exitButton = new JButton("Return to Main Menu?");
            exitButton.setFocusable(false);

            contentPanel.add(pauseLabel);
            contentPanel.add(restartButton);
            contentPanel.add(exitButton);

            this.add(contentPanel);
        }

        public void setRestartAction(ActionListener action) {
            restartButton.addActionListener(action);
        }

        public void setExitAction(ActionListener action) {
            exitButton.addActionListener(action);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }
    }
}
