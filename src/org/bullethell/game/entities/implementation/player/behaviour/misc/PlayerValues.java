package org.bullethell.game.entities.implementation.player.behaviour.misc;

public final class PlayerValues {
    private long score;
    private int lives;
    private int bombs;

    public PlayerValues() {
        this(3, 3);
    }

    public PlayerValues(int lives, int bombs) {
        this.lives = lives;
        this.bombs = bombs;
        score = 0;
    }

    public void setValues(PlayerValues values) {
        score = values.getScore();
        lives = values.getLives();
        bombs = values.getBombs();
    }

    public long getScore() {
        return score;
    }

    public void incrementScore(long value) {
        score += value;
    }

    public int getLives() {
        return lives;
    }

    public void incrementLives(int value) {
        lives += value;
    }

    public void decrementLives(int value) {
        lives -= value;
    }

    public int getBombs() {
        return bombs;
    }

    public void incrementBombs(int value) {
        bombs += value;
    }

    public void decrementBombs(int value) {
        bombs -= value;
    }
}
