package org.bullethell.game.entities.physics.movement;

public interface Kinematic {
    float getVelocity();
    void setVelocity(float velocity);
    float getAcceleration();
    void setAcceleration(float acceleration);
}
