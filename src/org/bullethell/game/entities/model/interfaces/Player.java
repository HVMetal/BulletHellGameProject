package org.bullethell.game.entities.model.interfaces;

import org.bullethell.game.entities.implementation.player.behaviour.misc.PlayerValues;
import org.bullethell.game.entities.model.observer.ObservedEntity;

public interface Player extends CollisionEntity, ObservedEntity {
    PlayerValues getPlayerStats();
}