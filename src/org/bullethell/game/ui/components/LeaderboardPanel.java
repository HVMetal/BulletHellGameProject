package org.bullethell.game.ui.components;

import org.bullethell.game.audio.management.SoundCache;
import org.bullethell.game.ui.GameFrame;
import org.bullethell.game.utils.HighscoreUtils;

import javax.swing.*;
import java.awt.*;

public final class LeaderboardPanel extends ActiveFramePanel {
    private final static int BORDER_THICKNESS = 3;
    private final static int PADDING = 12;

    public LeaderboardPanel(GameFrame gameFrame) {
        super(gameFrame);

        final JLabel title = new JLabel("Leaderboard:");
        title.setFont(new Font("Arial", Font.PLAIN | Font.BOLD, 16));
        title.setForeground(Color.WHITE);

        final JTextArea scores = new JTextArea(
                HighscoreUtils.getFormattedHighscores()
        );
        scores.setEditable(false);
        scores.setBackground(null);
        scores.setBorder(null);
        scores.setFont(new Font("Monospaced", Font.PLAIN | Font.ITALIC, 14));
        scores.setForeground(Color.WHITE);
        scores.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JButton menuButton = new JButton("Return");
        menuButton.addActionListener(_ -> {
            SoundCache.play("select");
            gameFrame.setActivePanel(new MenuPanel(gameFrame));
        });
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel innerContainer = new JPanel();
        final LayoutManager innerLayout = new BoxLayout(innerContainer, BoxLayout.Y_AXIS);
        innerContainer.setLayout(innerLayout);
        innerContainer.setBackground(null);

        innerContainer.add(title);
        innerContainer.add(Box.createVerticalStrut(PADDING));
        innerContainer.add(scores);

        final JPanel outerContainer = new JPanel();
        final LayoutManager outerLayout = new BoxLayout(outerContainer, BoxLayout.X_AXIS);
        outerContainer.setLayout(outerLayout);
        outerContainer.setBackground(null);

        outerContainer.add(Box.createHorizontalStrut(PADDING));
        outerContainer.add(innerContainer);
        outerContainer.add(Box.createHorizontalStrut(15 * PADDING));

        final JPanel outerestContainer = new JPanel();
        final LayoutManager outerestLayout = new BoxLayout(outerestContainer, BoxLayout.Y_AXIS);
        outerestContainer.setLayout(outerestLayout);
        outerestContainer.setBackground(null);

        outerestContainer.add(Box.createVerticalStrut(PADDING / 2));
        outerestContainer.add(outerContainer);
        outerestContainer.add(Box.createVerticalStrut(5 * PADDING));
        outerestContainer.add(menuButton);
        outerestContainer.add(Box.createVerticalStrut(PADDING / 2));

        outerestContainer.setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDER_THICKNESS));

        this.add(outerestContainer);
    }
}
