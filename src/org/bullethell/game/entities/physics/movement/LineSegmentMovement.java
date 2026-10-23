package org.bullethell.game.entities.physics.movement;

import org.bullethell.game.utils.Coordinates;
import org.bullethell.game.utils.easingfunction.EasingFunction;
import org.bullethell.game.utils.easingfunction.LinearFunction;

public final class LineSegmentMovement implements MovementComponent {
    private final Coordinates end;
    private final EasingFunction easingFunction;
    private final float movementDuration, deltaTime;

    private float direction, distance, time;
    private boolean hasFinished, isInitiated, initByEnd;

    public LineSegmentMovement(Coordinates end) {
        this(end, new LinearFunction(), 1);
    }

    public LineSegmentMovement(Coordinates end, EasingFunction easingFunction, float movementDuration) {
        this.end = new Coordinates(end);
        this.easingFunction = easingFunction;
        this.movementDuration = movementDuration;

        this.deltaTime = 1f / movementDuration;
        initByEnd = true;
    }

    public LineSegmentMovement(float direction, float distance) {
        this(direction, distance, new LinearFunction(), 1);
    }

    public LineSegmentMovement(float direction, float distance, EasingFunction easingFunction, float movementDuration) {
        this.end = new Coordinates();
        this.easingFunction = easingFunction;
        this.movementDuration = movementDuration;
        this.direction = (float) Math.toRadians(direction);
        this.distance = distance;

        this.deltaTime = 1f / movementDuration;
    }

    @Override
    public MovementComponent cloneComponent() {
        return new LineSegmentMovement(
                (float) Math.toDegrees(direction),
                distance, easingFunction, movementDuration
        );
    }

    @Override
    public void calculateAndPosition(Movable movableObject) {
        if(!isInitiated()) {
            initiateMovement(movableObject);
        }
        if(!hasFinished()) {
            final float step = getVelocity();

            final float deltaX = step * (float) Math.cos(direction);
            final float deltaY = step * (float) Math.sin(direction);

            movableObject.moveObject(deltaX, deltaY);
            time += deltaTime;

            if (time >= 1f) {
                finishMovement();
            }
        }
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
        final float deltaF = easingFunction.function(time + deltaTime) - easingFunction.function(time);
        return deltaF * distance;
    }

    @Override
    public boolean hasFinished() {
        return hasFinished;
    }

    private void finishMovement() {
        hasFinished = true;
    }

    private boolean isInitiated() {
        return isInitiated;
    }

    private void initiateMovement(Movable movableObject) {
        isInitiated = true;
        if (initByEnd) {
            final Coordinates start = movableObject.getCoordinates();
            final float deltaX = end.getX() - start.getX();
            final float deltaY = end.getY() - start.getY();

            direction = (float) Math.atan2(deltaY, deltaX);
            distance = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        }
    }
}
