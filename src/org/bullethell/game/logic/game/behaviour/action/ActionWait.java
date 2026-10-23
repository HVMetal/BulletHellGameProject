package org.bullethell.game.logic.game.behaviour.action;

import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.logic.game.behaviour.Action;

public class ActionWait implements Action {
    private final int frames;

    private int timer;
    private boolean isFinished;

    public ActionWait(int frames) {
        this.frames = frames;
    }

    @Override
    public Action cloneComponent() {
        return new ActionWait(frames);
    }

    @Override
    public void execute(GameEntity entity) {
        if (timer < frames) {
            timer++;
        } else {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
