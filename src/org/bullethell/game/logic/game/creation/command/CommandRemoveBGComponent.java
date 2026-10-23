package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.graphics.components.shapes.Shape;
import org.bullethell.game.graphics.components.shapes.composite.CompositeShape;
import org.bullethell.game.logic.game.GameManager;

public final class CommandRemoveBGComponent extends Command {
    private final Type type;
    private final int index;

    public enum Type {
        REMOVE_ALL,
        REMOVE_ONE
    }

    public CommandRemoveBGComponent(int channel, int index) {
        super(channel);
        this.index = index;
        this.type = Type.REMOVE_ONE;
    }

    public CommandRemoveBGComponent(int channel, Type type) {
        super(channel);
        this.index = 0;
        this.type = type;
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        final CompositeShape cs = gameManager.getBackgroundManager().getReservedBackgroundComposite();

        switch (type) {
            case REMOVE_ALL -> {
                Shape shapeComponent;
                while((shapeComponent = cs.getChild(0)) != null) {
                    cs.remove(shapeComponent);
                }
            }
            case REMOVE_ONE -> cs.remove(cs.getChild(index));
        }

        finishCommand();
    }
}
