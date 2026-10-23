package org.bullethell.game.entities.model.wrappers;

import org.bullethell.game.entities.model.interfaces.Miscellaneous;

public abstract class MiscellaneousWrapper extends GameEntityWrapper<Miscellaneous> implements Miscellaneous {
    public MiscellaneousWrapper(Miscellaneous wrappedEntity) {
        super(wrappedEntity);
    }
}
