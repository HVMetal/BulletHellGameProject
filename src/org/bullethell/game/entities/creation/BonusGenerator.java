package org.bullethell.game.entities.creation;

import org.bullethell.game.entities.implementation.bonus.BonusBomb;
import org.bullethell.game.entities.implementation.bonus.BonusLife;
import org.bullethell.game.entities.implementation.bonus.BonusScore;
import org.bullethell.game.entities.implementation.enemy.behaviour.misc.BonusValues;
import org.bullethell.game.entities.physics.collision.hitbox.CircularHitbox;
import org.bullethell.game.graphics.components.bitmap.utils.SpriteCache;
import org.bullethell.game.logic.game.GameManager;

public final class BonusGenerator {
    private final static int X_SPREAD = 48;
    private final static int Y_SPREAD = 24;

    private static final BonusChainLink SCORE_CREATION_CHAIN = BonusChainLink.link(
            new BonusChain(new BonusScore(
                    new CircularHitbox(14),
                    SpriteCache.getSprite("bonus_score_giant"),
                    100000
            ), X_SPREAD, Y_SPREAD),
            new BonusChain(new BonusScore(
                    new CircularHitbox(10),
                    SpriteCache.getSprite("bonus_score_medium"),
                    25000
            ), X_SPREAD, Y_SPREAD),
            new BonusChain(new BonusScore(
                    new CircularHitbox(8),
                    SpriteCache.getSprite("bonus_score_small"),
                    2500
            ), X_SPREAD, Y_SPREAD)
    );

    private static final BonusChainLink LIFE_CREATION = new BonusChain(
            new BonusLife(
                    new CircularHitbox(8),
                    SpriteCache.getSprite("bonus_life")
            )
    );
    private static final BonusChainLink BOMB_CREATION = new BonusChain(new BonusBomb(
            new CircularHitbox(8),
            SpriteCache.getSprite("bonus_bomb")
    ), X_SPREAD / 2f, Y_SPREAD / 2f);

    public static void execute(GameManager gameManager, float x, float y, BonusValues bonusValues) {
        SCORE_CREATION_CHAIN.create(gameManager, x, y, bonusValues.scoreValue());
        LIFE_CREATION.create(gameManager, x, y, bonusValues.lifeValue());
        BOMB_CREATION.create(gameManager, x, y, bonusValues.bombValue());
    }
}
