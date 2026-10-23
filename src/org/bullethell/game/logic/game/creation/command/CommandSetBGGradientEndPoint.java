package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.easingfunction.EasingFunction;
import org.bullethell.game.utils.easingfunction.LinearFunction;

import java.awt.geom.Point2D;

public final class CommandSetBGGradientEndPoint extends Command {
    private final Point2D newPosition;
    private final int duration;
    private final EasingFunction easingFunction;

    private Point2D oldPosition;
    private float time;

    public CommandSetBGGradientEndPoint(int channel, Point2D newPosition) {
        this(channel, newPosition, 1, new LinearFunction());
    }

    public CommandSetBGGradientEndPoint(int channel, Point2D newPosition, int duration) {
        this(channel, newPosition, duration, new LinearFunction());
    }

    public CommandSetBGGradientEndPoint(int channel, Point2D newPosition, int duration, EasingFunction easingFunction) {
        super(channel);
        this.newPosition = newPosition;
        this.duration = Math.max(1, duration);
        this.easingFunction = easingFunction;
    }

    @Override
    public void oneTimeActions(GameManager gameManager) {
        oldPosition = gameManager.getBackgroundManager().getGradient().getEndPoint();
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        time += 1f / duration;

        final int x = (int) (oldPosition.getX() + easingFunction.function(time) * (newPosition.getX() - oldPosition.getX()));
        final int y = (int) (oldPosition.getY() + easingFunction.function(time) * (newPosition.getY() - oldPosition.getY()));

        gameManager.getBackgroundManager().getGradient().setEndPoint(x, y);

        if(time >= 1) {
            finishCommand();
        }
    }
}
