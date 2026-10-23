package org.bullethell.game.ui.components;

import org.bullethell.game.audio.management.SoundCache;
import org.bullethell.game.entities.creation.factory.PlayerOneFactory;
import org.bullethell.game.logic.game.creation.LevelOne;
import org.bullethell.game.ui.GameFrame;

import javax.swing.*;
import java.awt.*;

public final class MenuPanel extends ActiveFramePanel {
    public MenuPanel(GameFrame gameFrame) {
        super(gameFrame);

        final JPanel container = new JPanel(new GridLayout(0, 1));
        container.setBackground(null);

        final JButton startButton = new JButton("Start game");
        startButton.addActionListener(_ -> {
            SoundCache.play("select");
            gameFrame.setActivePanel(new GamePanel(gameFrame, new LevelOne(), new PlayerOneFactory()));
        });

        final JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.addActionListener(_ -> {
            SoundCache.play("select");
            gameFrame.setActivePanel(new LeaderboardPanel(gameFrame));
        });

        final JButton exitButton = new JButton("Exit game");
        exitButton.addActionListener(_ -> gameFrame.closeFrame());

        container.add(startButton);
        container.add(leaderboardButton);
        container.add(exitButton);

        this.add(container);
    }
}
