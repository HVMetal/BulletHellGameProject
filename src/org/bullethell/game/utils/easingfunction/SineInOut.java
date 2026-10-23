package org.bullethell.game.utils.easingfunction;

public final class SineInOut implements EasingFunction {
    @Override
    public float function(float t) {
        return (float) -(Math.cos(Math.PI * t) - 1) / 2;
    }
}
