package org.bullethell.game.entities.implementation.bonus;

import org.bullethell.game.audio.management.SoundCache;
import org.bullethell.game.entities.model.abstracts.BonusModel;
import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.model.interfaces.Player;
import org.bullethell.game.entities.physics.collision.hitbox.Hitbox;
import org.bullethell.game.graphics.components.GraphicsComponent;

public class BonusLife extends BonusModel {
    public BonusLife(Hitbox hitbox, GraphicsComponent graphicsComponent) {
        super(hitbox, graphicsComponent, 1);
    }

    @Override
    public GameEntity cloneComponent() {
        return new BonusLife(
                (Hitbox) getHitbox().cloneComponent(),
                (GraphicsComponent) getGraphicsComponent().cloneComponent()
        );
    }

    @Override
    public void applyBonus(Player player) {
        SoundCache.play("score_pick");
        player.getPlayerStats().incrementLives(bonusValue);
    }
}
