package org.bullethell.game.entities.model.interfaces;

public interface Destroyable {
    boolean shouldBeDestroyed();
    void onDestruction();
}
