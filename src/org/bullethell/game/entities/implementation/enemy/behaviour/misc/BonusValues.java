package org.bullethell.game.entities.implementation.enemy.behaviour.misc;

public record BonusValues(int scoreValue, int powerValue, int bombValue, int lifeValue) {
    public BonusValues() {
        this(0, 0, 0, 0);
    }
}
