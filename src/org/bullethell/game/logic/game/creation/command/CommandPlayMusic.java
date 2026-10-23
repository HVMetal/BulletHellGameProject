package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.audio.management.MusicPlayer;
import org.bullethell.game.logic.game.GameManager;

public final class CommandPlayMusic extends Command {
    private final String key;

    public CommandPlayMusic(String key) {
        this(0, key);
    }

    public CommandPlayMusic(int channel, String key) {
        super(channel);
        this.key = key;
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        MusicPlayer.play(key);
        finishCommand();
    }
}
