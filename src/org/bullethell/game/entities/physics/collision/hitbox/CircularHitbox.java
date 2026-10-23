package org.bullethell.game.entities.physics.collision.hitbox;

import org.bullethell.game.utils.Coordinates;

public final class CircularHitbox implements Hitbox, HitboxVisitor {
    private final Coordinates position;
    private final float radius;

    public CircularHitbox(float radius) {
        this.radius = radius;
        position = new Coordinates();
    }

    @Override
    public Hitbox cloneComponent() {
        return new CircularHitbox(radius);
    }

    @Override
    public void setPosition(Coordinates position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    @Override
    public float[] getDimensions() {
        return new float[]{position.getX() - radius, position.getY() - radius, 2 * radius, 2 * radius};
    }

    @Override
    public boolean accept(HitboxVisitor visitor) {
        return visitor.intersects(this);
    }

    @Override
    public boolean intersects(CircularHitbox hitbox) {
        final float hDiff = (float) Math.pow(this.position.getX() - hitbox.position.getX(), 2);
        final float vDiff = (float) Math.pow(this.position.getY() - hitbox.position.getY(), 2);
        final float rSum = (float) Math.pow(radius + hitbox.radius, 2);

        return (hDiff + vDiff) <= rSum;
    }
}
