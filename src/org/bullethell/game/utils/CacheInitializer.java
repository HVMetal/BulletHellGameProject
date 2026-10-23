package org.bullethell.game.utils;

import org.bullethell.game.audio.management.MusicPlayer;
import org.bullethell.game.audio.management.SoundCache;
import org.bullethell.game.graphics.components.bitmap.utils.SpriteCache;

public final class CacheInitializer {
    public static void initializeSpriteCaches() {
        initializeSprites();
        initializeAnimations();
    }

    public static void initializeSoundCaches() {
        initializeSoundCache();
        // initializeMusicCache();
    }

    private static void initializeSprites() {
        SpriteCache.addSprite("dummy_enemy", "Black_Enemy");
        SpriteCache.addSprite("dummy_enemy_two", "Red_Enemy");

        SpriteCache.addSprite("big_round_red_bullet", "BigRoundRedBullet");
        SpriteCache.addSprite("round_red_bullet", "RoundRedBullet");
        SpriteCache.addSprite("round_blue_bullet", "RoundBlueBullet");
        SpriteCache.addSprite("blue_bullet", "BlueBullet");

        SpriteCache.addSprite("default_round_bullet", "RoundBullet");

        SpriteCache.addSprite("bonus_score_giant", "BonusScoreGiant");
        SpriteCache.addSprite("bonus_score_medium", "BonusScore");
        SpriteCache.addSprite("bonus_score_small", "BonusScoreSmall");
        SpriteCache.addSprite("bonus_life", "BonusLife");
        SpriteCache.addSprite("bonus_bomb", "BonusBomb");
    }

    private static void initializeAnimations() {
        SpriteCache.addSpriteSequence("big_round_red_bullet_destroyed",
                "BigRoundRedBulletDestroyed", 4);

        SpriteCache.addSpriteSequence("round_red_bullet_destroyed",
                "RoundRedBulletDestroyed", 4);

        SpriteCache.addSpriteSequence("round_blue_bullet_destroyed",
                "RoundBlueBulletDestroyed", 4);

        SpriteCache.addSpriteSequence("blue_bullet_destroyed",
                "BlueBulletDestroyed", 4);

        SpriteCache.addSpriteSequence("player1", "Player1", 2);
    }

    private static void initializeSoundCache() {
        SoundCache.load("shoot_1", "shoot1.wav");
        SoundCache.load("shoot_2", "shoot2.wav");
        SoundCache.load("pause", "pause.wav");
        SoundCache.load("select", "select.wav");
        SoundCache.load("cancel", "cancel.wav");
        SoundCache.load("sparkle", "sparkle1.wav");
        SoundCache.load("score_pick", "scorepick.wav");
        SoundCache.load("enemy_hit", "enemyhit.wav");
        SoundCache.load("enemy_defeated", "enemydefeated1.wav");
        SoundCache.load("whoosh", "whoosh.wav");
    }

    private static void initializeMusicCache() {
        MusicPlayer.load("level1", "Alice in Wonderland.wav");
        MusicPlayer.load("boss1", "Magic Square.wav");
    }
}
