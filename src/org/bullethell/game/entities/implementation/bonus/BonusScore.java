package org.bullethell.game.entities.implementation.bonus;

import org.bullethell.game.audio.management.SoundCache;
import org.bullethell.game.entities.model.abstracts.BonusModel;
import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.model.interfaces.Player;
import org.bullethell.game.entities.physics.collision.hitbox.Hitbox;
import org.bullethell.game.graphics.components.GraphicsComponent;

public class BonusScore extends BonusModel {
    public BonusScore(Hitbox hitbox, GraphicsComponent graphicsComponent, int bonusValue) {
        super(hitbox, graphicsComponent, bonusValue);
    }

    @Override
    public GameEntity cloneComponent() {
        return new BonusScore(
                (Hitbox) getHitbox().cloneComponent(),
                (GraphicsComponent) getGraphicsComponent().cloneComponent(),
                getBonusValue()
        );
    }

    @Override
    public void applyBonus(Player player) {
        SoundCache.play("score_pick");
        player.getPlayerStats().incrementScore(getBonusValue());
    }
}
