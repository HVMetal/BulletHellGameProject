package org.bullethell.game.entities.model.abstracts;

import org.bullethell.game.entities.model.interfaces.Miscellaneous;
import org.bullethell.game.entities.storage.ObjectType;

public abstract class MiscellaneousModel extends GameEntityModel implements Miscellaneous {
    @Override
    public final ObjectType getObjectType() {
        return ObjectType.MISCELLANEOUS;
    }
}
