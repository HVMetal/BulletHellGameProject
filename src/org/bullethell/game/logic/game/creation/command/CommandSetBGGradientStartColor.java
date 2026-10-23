package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.easingfunction.EasingFunction;
import org.bullethell.game.utils.easingfunction.LinearFunction;

import java.awt.*;

public final class CommandSetBGGradientStartColor extends Command {
    private final Color newColor;
    private final int duration;
    private final EasingFunction easingFunction;

    private Color oldColor;
    private float time;

    public CommandSetBGGradientStartColor(int channel, Color newColor) {
        this(channel, newColor, 1, new LinearFunction());
    }

    public CommandSetBGGradientStartColor(int channel, Color newColor, int duration) {
        this(channel, newColor, duration, new LinearFunction());
    }

    public CommandSetBGGradientStartColor(int channel, Color newColor, int duration, EasingFunction easingFunction) {
        super(channel);
        this.newColor = newColor;
        this.duration = Math.max(1, duration);
        this.easingFunction = easingFunction;
    }

    @Override
    public void oneTimeActions(GameManager gameManager) {
        oldColor = gameManager.getBackgroundManager().getGradient().getStartColor();
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        time += 1f / duration;

        final int red = calculateColor(oldColor.getRed(), newColor.getRed());
        final int green = calculateColor(oldColor.getGreen(), newColor.getGreen());
        final int blue = calculateColor(oldColor.getBlue(), newColor.getBlue());

        gameManager.getBackgroundManager().getGradient().setStartColor(new Color(red, green, blue));

        if(time >= 1) {
            finishCommand();
        }
    }

    private int calculateColor(int oldColor, int newColor) {
        return (int) (oldColor + easingFunction.function(time) * (newColor - oldColor));
    }
}
