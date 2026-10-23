package org.bullethell.game.entities.creation.factory;

import org.bullethell.game.entities.implementation.player.behaviour.misc.PlayerValues;
import org.bullethell.game.entities.model.interfaces.Player;

public interface PlayerFactory extends ObjectFactory<Player> {
    default PlayerValues getDefaultPlayerValues() {
        return new PlayerValues();
    }
}
