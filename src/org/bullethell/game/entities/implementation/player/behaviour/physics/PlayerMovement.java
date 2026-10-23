package org.bullethell.game.entities.implementation.player.behaviour.physics;

import org.bullethell.game.entities.physics.movement.Movable;
import org.bullethell.game.entities.physics.movement.MovementComponent;
import org.bullethell.game.entities.model.observer.EntityObserver;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class PlayerMovement implements MovementComponent, EntityObserver {
    private GameManager gameManager;

    private final KeyListener movementKeyListener;
    private final float normalSpeed, focusedSpeed;
    private final float playerWidth, playerHeight;

    private float currentSpeed, uSpeed, dSpeed, lSpeed, rSpeed;
    private boolean canMoveUp, canMoveDown, canMoveLeft, canMoveRight, isFocused;

    public PlayerMovement(float playerWidth, float playerHeight, float normalSpeed, float focusedSpeed) {
        this.playerWidth = playerWidth / 2f;
        this.playerHeight = playerHeight / 2f;
        this.normalSpeed = normalSpeed;
        this.focusedSpeed = focusedSpeed;

        resetVariables();

        movementKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_SHIFT) {
                    isFocused = true;
                }
                if(canMoveUp && keyCode == KeyEvent.VK_UP) {
                    uSpeed = currentSpeed;
                }
                if(canMoveDown && keyCode == KeyEvent.VK_DOWN) {
                    dSpeed = currentSpeed;
                }
                if(canMoveLeft && keyCode == KeyEvent.VK_LEFT) {
                    lSpeed = currentSpeed;
                }
                if(canMoveRight && keyCode == KeyEvent.VK_RIGHT) {
                    rSpeed = currentSpeed;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_SHIFT) {
                    isFocused = false;
                }
                if(keyCode == KeyEvent.VK_UP) {
                    uSpeed = 0;
                    canMoveUp = true;
                }
                if(keyCode == KeyEvent.VK_DOWN) {
                    dSpeed = 0;
                    canMoveDown = true;
                }
                if(keyCode == KeyEvent.VK_LEFT) {
                    lSpeed = 0;
                    canMoveLeft = true;
                }
                if(keyCode == KeyEvent.VK_RIGHT) {
                    rSpeed = 0;
                    canMoveRight = true;
                }
            }
        };
    }

    @Override
    public MovementComponent cloneComponent() {
        return new PlayerMovement(playerWidth, playerHeight, normalSpeed, focusedSpeed);
    }

    @Override
    public void notifyOnGameReception(GameManager gameManager) {
        this.gameManager = gameManager;
        gameManager.addListener(movementKeyListener);
        resetVariables();
    }

    @Override
    public void notifyOnDestruction(GameManager gameManager) {
        gameManager.removeListener(movementKeyListener);
    }

    @Override
    public void calculateAndPosition(Movable movableObject) {
        focusMovement();

        final Coordinates newOriginPosition = stopAtFrameBorders(movableObject.getCoordinates());
        movableObject.setCoordinates(
                newOriginPosition.getX() + rSpeed - lSpeed,
                newOriginPosition.getY() + dSpeed - uSpeed
        );
    }

    private void focusMovement() {
        if(isFocused) {
            currentSpeed = focusedSpeed;
        }
        else {
            currentSpeed = normalSpeed;
        }

        uSpeed = forceSpeedChange(uSpeed);
        dSpeed = forceSpeedChange(dSpeed);
        lSpeed = forceSpeedChange(lSpeed);
        rSpeed = forceSpeedChange(rSpeed);
    }

    private float forceSpeedChange(float speed) {
        if(speed > 0) {
            return currentSpeed;
        }
        return speed;
    }

    private Coordinates stopAtFrameBorders(Coordinates currentPosition) {
        float newX = currentPosition.getX(), newY = currentPosition.getY();

        if((currentPosition.getX() - playerWidth) < 0) {
            newX = playerWidth;
            canMoveLeft = false;
            lSpeed = 0;
        }
        if((currentPosition.getX() + playerWidth) > gameManager.getPlayfieldWidth()) {
            newX = gameManager.getPlayfieldWidth() - playerWidth;
            canMoveRight = false;
            rSpeed = 0;
        }
        if((currentPosition.getY() - playerHeight) < 0) {
            newY = playerHeight;
            canMoveUp = false;
            uSpeed = 0;
        }
        if((currentPosition.getY() + playerHeight) > gameManager.getPlayfieldHeight()) {
            newY = gameManager.getPlayfieldHeight() - playerHeight;
            canMoveDown = false;
            dSpeed = 0;
        }

        return new Coordinates(newX, newY);
    }

    private void resetVariables() {
        currentSpeed = normalSpeed;
        uSpeed = dSpeed = lSpeed = rSpeed = 0;
        canMoveUp = canMoveDown = canMoveLeft = canMoveRight = true;
        isFocused = false;
    }
}
