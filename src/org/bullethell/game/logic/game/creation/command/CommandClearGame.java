package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.logic.game.GameManager;

public final class CommandClearGame extends Command {
    public CommandClearGame(int channel) {
        super(channel);
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        gameManager.clear();
        finishCommand();
    }
}
