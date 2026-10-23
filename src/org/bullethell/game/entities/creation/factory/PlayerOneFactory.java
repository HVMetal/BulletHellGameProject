package org.bullethell.game.entities.creation.factory;

import org.bullethell.game.entities.implementation.player.PlayerModelImpl;
import org.bullethell.game.entities.implementation.player.behaviour.misc.GenericBlaster;
import org.bullethell.game.entities.implementation.player.behaviour.misc.PlayerCannon;
import org.bullethell.game.entities.implementation.playerbullet.GenericPlayerBullet;
import org.bullethell.game.entities.model.interfaces.Player;
import org.bullethell.game.entities.model.interfaces.PlayerBullet;
import org.bullethell.game.entities.physics.collision.hitbox.CircularHitbox;
import org.bullethell.game.graphics.components.bitmap.Animation;
import org.bullethell.game.graphics.components.bitmap.utils.SpriteCache;
import org.bullethell.game.utils.Coordinates;

public final class PlayerOneFactory implements PlayerFactory {
    @Override
    public Player create(Coordinates coordinates) {
        final Player player = new PlayerModelImpl(3.66f, 1.75f, createCannon());
        player.setHitbox(new CircularHitbox(6));
        player.setGraphicsComponent(new Animation(
                SpriteCache.getSpriteSequence("player1"),
                5, true)
        );
        player.setCoordinates(coordinates);

        return player;
    }

    private PlayerCannon createCannon() {
        final PlayerBullet playerBullet = new GenericPlayerBullet(2);
        playerBullet.setHitbox(new CircularHitbox(6));
        playerBullet.setGraphicsComponent(SpriteCache.getSprite("round_blue_bullet"));

        return new GenericBlaster(0, -7, 7, 7, playerBullet);
    }
}
