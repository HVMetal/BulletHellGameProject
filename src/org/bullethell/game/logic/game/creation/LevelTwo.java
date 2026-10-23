package org.bullethell.game.logic.game.creation;

import org.bullethell.game.entities.creation.builder.EnemyBuilderImpl;
import org.bullethell.game.entities.physics.collision.hitbox.CircularHitbox;
import org.bullethell.game.graphics.components.bitmap.utils.SpriteCache;
import org.bullethell.game.logic.game.creation.command.*;
import org.bullethell.game.utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public final class LevelTwo extends LevelCreator {
    @Override
    public List<Command> createCommandsList() {
        List<Command> commands = new ArrayList<>();

        commands.add(popUpText(1, "Get ready!", new Coordinates(
                gameManager.getPlayfieldWidth(), gameManager.getPlayfieldHeight() / 2f
        )));
        commands.add(new CommandWait(1,125));
        commands.add(popUpText(1, "Stage 2", new Coordinates(
                gameManager.getPlayfieldWidth(), gameManager.getPlayfieldHeight() / 2f + 24
        )));

        // commands.add(new CommandPlayMusic("level1"));
        // commands.add(musicPlayText(0, "Touhou 05 Kaikidan - Alice in Wonderland"));
        commands.add(new CommandAddBoss(0, new EnemyBuilderImpl(gameManager)
                .setPosition(gameManager.getPlayfieldWidth() / 2f, 96)
                .setGraphicsComponent(SpriteCache.getSprite("dummy_enemy_two"))
                .setHitbox(new CircularHitbox(6))
                .setHealth(20)
                .build()
        ));
        commands.add(new CommandEndLevel(0, gameManager, this));

        return commands;
    }

    @Override
    public LevelCreator nextLevel() {
        return null;
    }

    @Override
    public long getBaseLevelCompletionScore() {
        return 400000;
    }

    @Override
    public boolean isFinalLevel() {
        return true;
    }
}
