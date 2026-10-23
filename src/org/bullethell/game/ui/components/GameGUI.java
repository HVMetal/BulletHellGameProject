package org.bullethell.game.ui.components;

import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public final class GameGUI extends JPanel {
    private final int guiWidth, guiHeight;

    private final JLabel scoreTextLabel, lifeTextLabel, bombTextLabel;
    private final JLabel scoreLabel, lifeLabel, bombLabel;

    public GameGUI(int width, int height) {
        this.guiWidth = width;
        this.guiHeight = height;

        setLayout(new GridLayout(0, 1));

        JLabel topLabel = new JLabel("Game Project");
        topLabel.setForeground(Color.WHITE);
        topLabel.setFont(new Font("Arial", Font.BOLD, 16));

        scoreTextLabel = new JLabel("Score : ");
        scoreLabel = new JLabel();
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));

        lifeTextLabel = new JLabel("Lives : ");
        lifeLabel = new JLabel();
        lifeLabel.setForeground(Color.WHITE);
        lifeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        bombTextLabel = new JLabel("Bombs : ");
        bombLabel = new JLabel();
        bombLabel.setForeground(Color.WHITE);
        bombLabel.setFont(new Font("Arial", Font.BOLD, 16));

        this.add(topLabel);
        this.add(scoreLabel);
        this.add(lifeLabel);
        // this.add(bombLabel);

        setOpaque(false);
        setFocusable(false);
    }

    @Override
    public int getWidth() {
        return guiWidth;
    }

    @Override
    public int getHeight() {
        return guiHeight;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), getHeight());
    }

    public void update(List<GameEntity> playerList) {
        if(!playerList.isEmpty()) {
            Player player = (Player) playerList.getFirst();

            setDisplayedScore(player.getPlayerStats().getScore());
            setDisplayedLives(player.getPlayerStats().getLives());
            setDisplayedBombs(player.getPlayerStats().getBombs());
        }
    }

    private void setDisplayedLives(int value) {
        lifeLabel.setText(lifeTextLabel.getText() + value);
    }

    private void setDisplayedBombs(int value) {
        bombLabel.setText(bombTextLabel.getText() + value);
    }

    private void setDisplayedScore(long value) {
        scoreLabel.setText(scoreTextLabel.getText() + value);
    }
}
