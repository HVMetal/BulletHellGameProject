package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.model.interfaces.Player;
import org.bullethell.game.logic.game.GameManager;

public final class CommandAddEntity extends Command {
    private final GameEntity entity;

    public CommandAddEntity(GameEntity entity) {
        this(0, entity);
    }

    public CommandAddEntity(int channel, GameEntity entity) {
        super(channel);
        this.entity = entity;

        if(entity instanceof Player) {
            throw new IllegalArgumentException("There cannot exist more than one player at a time!");
        }
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        gameManager.add(entity);
        finishCommand();
    }
}
