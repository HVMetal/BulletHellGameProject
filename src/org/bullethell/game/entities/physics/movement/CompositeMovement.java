package org.bullethell.game.entities.physics.movement;

import org.bullethell.game.entities.EntityUtils;
import org.bullethell.game.utils.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CompositeMovement implements MovementComponent {
    private final ArrayList<MovementComponent> movementsList = new ArrayList<>();

    public CompositeMovement(MovementComponent... movements) {
        movementsList.addAll(Arrays.asList(movements));
    }

    @Override
    public MovementComponent cloneComponent() {
        final List<MovementComponent> clonedList = EntityUtils.cloneList(movementsList);
        return new CompositeMovement(clonedList.toArray(new MovementComponent[0]));
    }

    @Override
    public void calculateAndPosition(Movable movableObject) {
        movementsList.forEach(m -> m.calculateAndPosition(movableObject));
    }

    @Override
    public void setDirection(float direction) {
        movementsList.forEach(m -> m.setDirection(direction));
    }

    @Override
    public float getDirection() {
        float deltaX = 0f, deltaY = 0f;
        for (MovementComponent movement : movementsList) {
            deltaX += movement.getXVelocity();
            deltaY += movement.getYVelocity();
        }

        return (float) Math.toDegrees(MathUtils.atan2(deltaY, deltaX));
    }

    @Override
    public float getVelocity() {
        return (float) movementsList.stream()
                .mapToDouble(MovementComponent::getVelocity)
                .sum();
    }
}
