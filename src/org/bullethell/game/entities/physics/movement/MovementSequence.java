package org.bullethell.game.entities.physics.movement;

import org.bullethell.game.utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public final class MovementSequence implements MovementComponent {
    private final List<MovementSequenceComponent> components;
    private int currentComponent;

    private boolean hasFinished;

    private record MovementSequenceComponent(
            MovementComponent movementComponent,
            DirectionChange type
    ) {}

    public enum DirectionChange {
        CONTINUOUS,
        ADDITIVE,
        RANDOM,
        PRESET
    }

    public MovementSequence(MovementComponent movementComponent) {
        components = new ArrayList<>();
        components.add(new MovementSequenceComponent(movementComponent, DirectionChange.PRESET));

        currentComponent = 0;
    }

    @Override
    public MovementComponent cloneComponent() {
        final MovementSequence clone = new MovementSequence((MovementComponent) components.getFirst().movementComponent().cloneComponent());
        for(int i = 1; i < components.size(); ++i) {
            final MovementSequenceComponent component = components.get(i);
            clone.add((MovementComponent) component.movementComponent().cloneComponent(), component.type());
        }

        return clone;
    }

    @Override
    public void calculateAndPosition(Movable movableObject) {
        if(!hasFinished()) {
            final MovementComponent movementComponent = components.get(currentComponent).movementComponent();
            if(movementComponent.hasFinished()) {
                nextComponent();
            } else {
                movementComponent.calculateAndPosition(movableObject);
            }
        }
    }

    @Override
    public boolean hasFinished() {
        return hasFinished;
    }

    @Override
    public float getDirection() {
        return components.get(currentComponent).movementComponent().getDirection();
    }

    @Override
    public void setDirection(float direction) {
        components.get(currentComponent).movementComponent().setDirection(direction);
    }

    public MovementSequence add(MovementComponent movementComponent) {
        return this.add(movementComponent, DirectionChange.PRESET);
    }

    public MovementSequence add(MovementComponent movementComponent, DirectionChange type) {
        components.add(new MovementSequenceComponent(movementComponent, type));
        return this;
    }

    private void nextComponent() {
        currentComponent++;
        if (currentComponent >= components.size()) {
            finishMovement();
        } else {
            final MovementSequenceComponent component = components.get(currentComponent);

            if (component.type() != DirectionChange.PRESET) {
                float direction = 0;
                switch (component.type()) {
                    case CONTINUOUS -> direction = components.get(currentComponent - 1).movementComponent().getDirection();

                    case ADDITIVE -> direction = components.get(currentComponent - 1).movementComponent().getDirection()
                            + component.movementComponent().getDirection();

                    case RANDOM -> direction = (float) (Math.random() * 360);
                }

                component.movementComponent().setDirection(direction);
            }
        }
    }

    private void finishMovement() {
        hasFinished = true;
    }
}
