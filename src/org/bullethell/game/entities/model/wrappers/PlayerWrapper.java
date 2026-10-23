package org.bullethell.game.entities.model.wrappers;

import org.bullethell.game.entities.implementation.player.behaviour.misc.PlayerValues;
import org.bullethell.game.entities.model.interfaces.Player;

public abstract class PlayerWrapper extends CollisionEntityWrapper<Player> implements Player {
    public PlayerWrapper(Player entity) {
        super(entity);
    }

    @Override
    public PlayerValues getPlayerStats() {
        return wrappedEntity.getPlayerStats();
    }
}
