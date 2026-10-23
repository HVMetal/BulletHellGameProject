package org.bullethell.game.entities.model.interfaces;

public interface Bonus extends CollisionEntity {
    int getBonusValue();
    void applyBonus(Player player);
}
