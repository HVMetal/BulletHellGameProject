package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.easingfunction.EasingFunction;
import org.bullethell.game.utils.easingfunction.LinearFunction;

import java.awt.*;

public final class CommandSetBGGradientColor extends Command {
    private final Color newColor;
    private final int duration;
    private final EasingFunction easingFunction;

    private Color oldStartColor, oldEndColor;
    private float time;

    public CommandSetBGGradientColor(int channel, Color newColor) {
        this(channel, newColor, 1, new LinearFunction());
    }

    public CommandSetBGGradientColor(int channel, Color newColor, int duration) {
        this(channel, newColor, duration, new LinearFunction());
    }

    public CommandSetBGGradientColor(int channel, Color newColor, int duration, EasingFunction easingFunction) {
        super(channel);
        this.newColor = newColor;
        this.duration = Math.max(1, duration);
        this.easingFunction = easingFunction;
    }

    @Override
    public void oneTimeActions(GameManager gameManager) {
        oldStartColor = gameManager.getBackgroundManager().getGradient().getStartColor();
        oldEndColor = gameManager.getBackgroundManager().getGradient().getEndColor();
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        time += 1f / duration;

        final int endRed = calculateColor(oldEndColor.getRed(), newColor.getRed());
        final int endGreen = calculateColor(oldEndColor.getGreen(), newColor.getGreen());
        final int endBlue = calculateColor(oldEndColor.getBlue(), newColor.getBlue());

        final int startRed = calculateColor(oldStartColor.getRed(), newColor.getRed());
        final int startGreen = calculateColor(oldStartColor.getGreen(), newColor.getGreen());
        final int startBlue = calculateColor(oldStartColor.getBlue(), newColor.getBlue());

        gameManager.getBackgroundManager().getGradient().setEndColor(new Color(endRed, endGreen, endBlue));
        gameManager.getBackgroundManager().getGradient().setStartColor(new Color(startRed, startGreen, startBlue));

        if(time >= 1) {
            finishCommand();
        }
    }

    private int calculateColor(int oldColor, int newColor) {
        return (int) (oldColor + easingFunction.function(time) * (newColor - oldColor));
    }
}
