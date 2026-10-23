package org.bullethell.game.entities.implementation.player;

import org.bullethell.game.entities.implementation.player.behaviour.misc.PlayerCannon;
import org.bullethell.game.entities.model.abstracts.PlayerModel;
import org.bullethell.game.entities.model.interfaces.GameEntity;

public class PlayerModelImpl extends PlayerModel {
    public PlayerModelImpl(float normalSpeed, float focusedSpeed, PlayerCannon playerCannon) {
        super(normalSpeed, focusedSpeed, playerCannon);
    }

    @Override
    public GameEntity cloneComponent() {
        return null;
    }
}
