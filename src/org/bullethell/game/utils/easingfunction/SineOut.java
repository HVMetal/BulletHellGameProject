package org.bullethell.game.utils.easingfunction;

public final class SineOut implements EasingFunction {
    @Override
    public float function(float t) {
        return (float) Math.sin((t * Math.PI) / 2);
    }
}
