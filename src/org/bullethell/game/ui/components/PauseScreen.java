package org.bullethell.game.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public final class PauseScreen extends JPanel {
    private BufferedImage backgroundImage;

    public PauseScreen(int width, int height, ActionListener continueAction, ActionListener restartAction, ActionListener exitAction) {
        setBounds(0, 0, width, height);
        setBackground(new Color(0, 0, 0, 175));
        setOpaque(false);
        setLayout(new GridBagLayout());

        PlayfieldPauseDialog dialog = new PlayfieldPauseDialog(192, 144);
        dialog.setContinueAction(continueAction);
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

    private static class PlayfieldPauseDialog extends JPanel {
        private final JButton continueButton, restartButton, exitButton;
        private final int width, height;

        private final static int GAP = 8;

        public PlayfieldPauseDialog(int width, int height) {
            this.width = width;
            this.height = height;

            setBackground(Color.BLACK);
            setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            setLayout(new GridBagLayout());
            setFocusable(false);

            JPanel contentPanel = new JPanel(new GridLayout(0, 1, GAP, GAP));
            contentPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
            contentPanel.setOpaque(false);

            JLabel pauseLabel = new JLabel("Paused.", SwingConstants.CENTER);
            pauseLabel.setForeground(Color.WHITE);
            pauseLabel.setFont(new Font("Arial", Font.BOLD, 16));

            continueButton = new JButton("Continue.");
            continueButton.setFocusable(false);

            restartButton = new JButton("Restart stage.");
            restartButton.setFocusable(false);

            exitButton = new JButton("Exit to Main Menu.");
            exitButton.setFocusable(false);

            contentPanel.add(pauseLabel);
            contentPanel.add(continueButton);
            contentPanel.add(restartButton);
            contentPanel.add(exitButton);

            this.add(contentPanel);
        }

        public void setContinueAction(ActionListener action) {
            continueButton.addActionListener(action);
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
