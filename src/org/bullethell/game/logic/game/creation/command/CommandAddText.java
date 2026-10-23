package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.entities.implementation.misc.TextEntity;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.graphics.components.shapes.simple.TextComponent;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;
import org.bullethell.game.utils.TextUtils;

import java.awt.*;

public final class CommandAddText extends Command {
    private final TextEntity textEntity;

    public CommandAddText(String text, Font font, Coordinates coordinates, Color color, int maxWidth, MovementComponent movement) {
        this(0, text, font, coordinates, color, maxWidth, movement);
    }

    public CommandAddText(int channel, String text, Font font, Coordinates coordinates, Color color, int maxWidth, MovementComponent movement) {
        super(channel);
        textEntity = new TextEntity(
                new TextComponent(
                        coordinates.getX(), coordinates.getY(), color,
                        text, TextUtils.getIdealSizedFont(font, text, maxWidth, 0.5f)
                ), movement
        );
        textEntity.setCoordinates(coordinates);
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        gameManager.add(textEntity);
        finishCommand();
    }
}
