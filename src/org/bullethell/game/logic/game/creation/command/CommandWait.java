package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.logic.game.GameManager;

public final class CommandWait extends Command {
    private final int frames;
    private int currentFrame;

    public CommandWait(int frames) {
        this(0, frames);
    }

    public CommandWait(int channel, int frames) {
        super(channel);
        this.frames = frames;
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        if(currentFrame >= frames) {
            finishCommand();
        } else {
            currentFrame++;
        }
    }
}
