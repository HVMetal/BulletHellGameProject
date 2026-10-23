package org.bullethell.game.utils.easingfunction;

public final class ExponentialIn implements EasingFunction {
    @Override
    public float function(float t) {
        return t == 0 ? 0 : (float) Math.pow(2, 10 * t - 10);
    }
}
