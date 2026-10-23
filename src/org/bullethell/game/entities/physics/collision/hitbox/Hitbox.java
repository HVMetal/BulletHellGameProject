package org.bullethell.game.entities.physics.collision.hitbox;

import org.bullethell.game.entities.Cloneable;
import org.bullethell.game.utils.Coordinates;

public interface Hitbox extends Cloneable {
    void setPosition(Coordinates position);
    float[] getDimensions();

    boolean accept(HitboxVisitor visitor);
}
