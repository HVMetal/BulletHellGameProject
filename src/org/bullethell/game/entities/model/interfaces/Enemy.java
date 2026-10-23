package org.bullethell.game.entities.model.interfaces;

import org.bullethell.game.attackpatterns.AttackPattern;
import org.bullethell.game.entities.implementation.enemy.behaviour.misc.BonusValues;
import org.bullethell.game.entities.model.observer.ObservedEntity;

public interface Enemy extends CollisionEntity, ObservedEntity {
    float getHealth();
    float getOriginalHealth();
    void setHealth(float health);

    BonusValues getBonusValues();

    AttackPattern getAttackPattern();
    void setAttackPattern(AttackPattern attackPattern);
}
