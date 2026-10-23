package org.bullethell.game.graphics.components;

import java.awt.Graphics;

public interface Displayable {
    /**
     * Displays the object on the screen using the provided {@link Graphics} object.
     * It renders the entity's graphical representation onto the playfield or other visual areas.
     *
     * @param g The {@link Graphics} object used for drawing the entity.
     * **/
    void display(Graphics g);
}
