package org.bullethell.game.entities.implementation.enemybullet.behaviour.wrapper;

import org.bullethell.game.entities.model.interfaces.EnemyBullet;
import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.model.wrappers.EnemyBulletWrapper;

public final class ReboundWrapper extends EnemyBulletWrapper {
    private final Type type;
    private final int reboundNumber;

    private int reboundsLeft;

    public enum Type {
        PARTIAL_REBOUND,
        FULL_REBOUND
    }

    public ReboundWrapper(EnemyBullet decoratedModel) {
        this(decoratedModel, 1, Type.PARTIAL_REBOUND);
    }

    public ReboundWrapper(EnemyBullet decoratedModel, int reboundNumber) {
        this(decoratedModel, reboundNumber, Type.PARTIAL_REBOUND);
    }

    public ReboundWrapper(EnemyBullet decoratedModel, Type type) {
        this(decoratedModel, 1, type);
    }

    public ReboundWrapper(EnemyBullet decoratedModel, int reboundNumber, Type type) {
        super(decoratedModel);
        this.reboundNumber = reboundNumber;
        this.type = type;

        reboundsLeft = reboundNumber;
    }

    @Override
    public GameEntity cloneComponent() {
        return new ReboundWrapper((EnemyBullet) super.cloneComponent(), reboundNumber, type);
    }

    @Override
    public boolean shouldBeDestroyed() {
        return reboundsLeft < 0 || super.shouldBeDestroyed();
    }

    @Override
    public void coreEntityLoop() {
        super.coreEntityLoop();
        reboundObject();
    }

    private void reboundObject() {
        if(reboundsLeft > 0
                && (getCoordinates().getX() < 0
                || getCoordinates().getX() > getGameManager().getPlayfieldWidth()
                || getCoordinates().getY() < 0)
                && getMovementComponent() != null) {

            final float mAngle = (float) Math.toRadians(getMovementComponent().getDirection());
            final byte quadrant = getQuadrant((float) Math.sin(mAngle), (float) Math.cos(mAngle));
            final float newAngle = (float) Math.toDegrees(calculateNewAngle(mAngle, quadrant));

            getMovementComponent().setDirection(newAngle);
            reboundsLeft--;
        }
    }

    private byte getQuadrant(float sinValue, float cosValue) {
        if(sinValue >= 0) {
            if(cosValue >= 0) {
                return 1;
            } else {
                return 4;
            }
        } else {
            if(cosValue >= 0) {
                return 3;
            } else {
                return 2;
            }
        }
    }

    private float calculateNewAngle(float originalAngle, byte quadrant) {
        switch(quadrant) {
            case 1 -> {
                if(getCoordinates().getX() > getGameManager().getPlayfieldWidth()) {
                    return (float) Math.PI - originalAngle;
                } else {
                    return (float) Math.PI * 2 - originalAngle;
                }
            }
            case 2 -> {
                if(getCoordinates().getX() < 0) {
                    return (float) Math.PI - originalAngle;
                } else {
                    return (float) Math.PI * 2 - originalAngle;
                }
            }
            case 3 -> {
                if(getCoordinates().getX() > getGameManager().getPlayfieldWidth()) {
                    return (float) Math.PI * 3 - originalAngle;
                } else {
                    return (float) Math.PI * 2 - originalAngle;
                }
            }
            case 4 -> {
                if(getCoordinates().getX() < 0) {
                    return (float) Math.PI * 3 - originalAngle;
                } else {
                    return (float) Math.PI * 2 - originalAngle;
                }
            }
            default -> {
                return 0;
            }
        }
    }
}
