package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.audio.management.SoundCache;
import org.bullethell.game.logic.game.GameManager;

public final class CommandPlaySound extends Command {
    private final String key;

    public CommandPlaySound(int channel, String key) {
        super(channel);
        this.key = key;
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        SoundCache.play(key);
        finishCommand();
    }
}
