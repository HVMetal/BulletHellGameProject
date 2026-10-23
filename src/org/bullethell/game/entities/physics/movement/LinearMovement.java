package org.bullethell.game.entities.physics.movement;

public final class LinearMovement implements MovementComponent, Kinematic {
    private float direction;
    private float velocity;
    private float acceleration;

    private final float originalDirection, originalVelocity, originalAcceleration;

    public LinearMovement(float direction, float velocity) {
        this(direction, velocity, 0);
    }

    public LinearMovement(float direction, float velocity, float acceleration) {
        setDirection(direction);
        setVelocity(velocity);
        setAcceleration(acceleration);

        this.originalDirection = direction;
        this.originalVelocity = velocity;
        this.originalAcceleration = acceleration;
    }

    @Override
    public MovementComponent cloneComponent() {
        return new LinearMovement(originalDirection, originalVelocity, originalAcceleration);
    }

    @Override
    public void calculateAndPosition(Movable movable) {
        final float deltaX = (float) (velocity * Math.cos(direction));
        final float deltaY = (float) (velocity * Math.sin(direction));

        movable.moveObject(deltaX, deltaY);
        advanceParameters();
    }

    @Override
    public float getDirection() {
        return (float) Math.toDegrees(direction);
    }

    @Override
    public void setDirection(float direction) {
        this.direction = (float) Math.toRadians(direction);
    }

    @Override
    public float getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    @Override
    public float getAcceleration() {
        return acceleration;
    }

    @Override
    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void advanceParameters() {
        velocity += acceleration;
    }
}
