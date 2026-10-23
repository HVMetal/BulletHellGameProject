package org.bullethell.game.utils.easingfunction;

public final class SineIn implements EasingFunction {
    @Override
    public float function(float t) {
        return 1 - (float) Math.cos((t * Math.PI) / 2);
    }
}
