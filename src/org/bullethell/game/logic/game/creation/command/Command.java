package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.logic.game.GameManager;

public abstract class Command {
    private final int channel;
    private boolean hasFinished, hasRunOneTimeActions;

    public Command(int channel) {
        this.channel = channel;
    }

    public final void execute(GameManager gameManager) {
        if(!hasRunOneTimeActions) {
            hasRunOneTimeActions = true;
            oneTimeActions(gameManager);
        }
        coreActions(gameManager);
    }

    protected void oneTimeActions(GameManager gameManager) {}
    protected abstract void coreActions(GameManager gameManager);

    protected final void finishCommand() {
        hasFinished = true;
    }

    public final boolean hasFinished() {
        return hasFinished;
    }

    public final int getChannel() {
        return channel;
    }
}
