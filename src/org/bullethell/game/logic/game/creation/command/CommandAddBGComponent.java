package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.graphics.components.shapes.Shape;
import org.bullethell.game.logic.game.GameManager;

public final class CommandAddBGComponent extends Command {
    private final Shape component;

    public CommandAddBGComponent(int channel, Shape component) {
        super(channel);
        this.component = component;
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        gameManager.getBackgroundManager().getReservedBackgroundComposite().add(component);
        finishCommand();
    }
}
