package org.bullethell.game.entities.physics.movement;

import org.bullethell.game.utils.MathUtils;

public final class CircularMovement implements MovementComponent, Kinematic {
    private final float radius, initVelocity, initAcceleration, initAngle;
    private float velocity, acceleration, angle;
    private float deltaX, deltaY;

    public CircularMovement(float radius, float velocity) {
        this(radius, velocity, 0f);
    }

    public CircularMovement(float radius, float velocity, float acceleration) {
        this(radius, velocity, acceleration, 0f);
    }

    public CircularMovement(float radius, float velocity, float acceleration, float angle) {
        this.radius = radius;
        setVelocity(velocity);
        setAcceleration(acceleration);
        setDirection(angle);

        this.initAngle = angle;
        this.initVelocity = velocity;
        this.initAcceleration = acceleration;
    }

    @Override
    public MovementComponent cloneComponent() {
        return new CircularMovement(radius, initVelocity, initAcceleration, initAngle);
    }

    @Override
    public void calculateAndPosition(Movable movableObject) {
        recalculateDelta();
        movableObject.moveObject(deltaX, deltaY);
        advanceParameters();
    }

    @Override
    public float getDirection() {
        recalculateDelta();
        float dir = MathUtils.atan2(deltaY, deltaX);
        return (float) Math.toDegrees(dir);
    }

    @Override
    public void setDirection(float direction) {
        if (velocity > 0) {
            angle = (float) Math.toRadians(direction - 90);
        } else {
            angle = (float) Math.toRadians(direction + 90);
        }
    }

    @Override
    public float getVelocity() {
        return (float) Math.toDegrees(velocity);
    }

    @Override
    public void setVelocity(float velocity) {
        this.velocity = (float) Math.toRadians(velocity);
    }

    @Override
    public float getAcceleration() {
        return (float) Math.toDegrees(acceleration);
    }

    @Override
    public void setAcceleration(float acceleration) {
        this.acceleration = (float) Math.toRadians(acceleration);
    }

    @Override
    public void advanceParameters() {
        angle += velocity;
        velocity += acceleration;
        offsetAngle();
    }

    private void recalculateDelta() {
        final float sinAngle = (float) Math.sin(angle);
        final float cosAngle = (float) Math.cos(angle);
        final float sinVelocity = (float) Math.sin(velocity);
        final float cosVelocity = (float) Math.cos(velocity);

        deltaX = radius * (cosAngle * (cosVelocity - 1) - sinAngle * sinVelocity);
        deltaY = radius * (sinAngle * (cosVelocity - 1) + cosAngle * sinVelocity);
    }

    private void offsetAngle() {
        final float twoPI = (float) Math.PI * 2;

        if(angle >= twoPI) {
            angle -= twoPI;
        } else {
            if(angle <= -twoPI) {
                angle += twoPI;
            }
        }
    }
}
