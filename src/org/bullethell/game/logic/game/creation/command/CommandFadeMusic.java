package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.audio.management.MusicPlayer;
import org.bullethell.game.logic.game.GameManager;

public final class CommandFadeMusic extends Command {
    private final Type fadeType;
    private float currentVolume, fadeSpeed;

    public enum Type {
        FADE_IN,
        FADE_OUT
    }

    public CommandFadeMusic(int channel, Type fadeType, float fadeSpeed) {
        super(channel);
        this.fadeType = fadeType;
        this.fadeSpeed = fadeSpeed;
    }

    @Override
    protected void oneTimeActions(GameManager manager) {
        currentVolume = (float) MusicPlayer.getVolume();
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        switch (fadeType) {
            case FADE_IN -> {
                if(currentVolume < 1.0) {
                    currentVolume += fadeSpeed;
                    MusicPlayer.setVolume(currentVolume);
                } else {
                    finishCommand();
                }
            }
            case FADE_OUT -> {
                if(currentVolume > 0.0) {
                    currentVolume -= fadeSpeed;
                    MusicPlayer.setVolume(currentVolume);
                } else {
                    finishCommand();
                }
            }
        }
    }
}
