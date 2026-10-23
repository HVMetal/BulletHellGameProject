package org.bullethell.game.utils.easingfunction;

public final class ExponentialOut implements EasingFunction {
    @Override
    public float function(float t) {
        return t == 1 ? 1 : 1 - (float) Math.pow(2, -10 * t);
    }
}
