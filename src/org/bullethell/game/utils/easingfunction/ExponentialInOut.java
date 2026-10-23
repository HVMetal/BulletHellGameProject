package org.bullethell.game.utils.easingfunction;

public final class ExponentialInOut implements EasingFunction {
    @Override
    public float function(float t) {
        return t == 0 ? 0 : t == 1 ? 1 :
                t < 0.5 ? (float) Math.pow(2, 20 * t - 10) / 2 : (2 - (float) Math.pow(2, -20 * t + 10)) / 2;
    }
}
