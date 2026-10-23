package org.bullethell.game.entities.implementation.player.behaviour.misc;

import org.bullethell.game.entities.model.abstracts.MiscellaneousModel;
import org.bullethell.game.entities.model.interfaces.PlayerBullet;
import org.bullethell.game.entities.model.observer.EntityObserver;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class PlayerCannon extends MiscellaneousModel implements EntityObserver {
    protected final PlayerBullet loadedBullet;
    protected final float xOffset, yOffet;

    private final int fireRate;
    private final KeyListener fireCommand;
    private boolean isFiring, isInactive;

    private int time;

    public PlayerCannon(float xOffset, float yOffset, int fireRate, PlayerBullet loadedBullet) {
        super();

        this.xOffset = xOffset;
        this.yOffet = yOffset;
        this.fireRate = fireRate;
        this.loadedBullet = loadedBullet;

        fireCommand = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(!isFiring && e.getKeyCode() == KeyEvent.VK_Z) {
                    isFiring = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(isFiring && e.getKeyCode() == KeyEvent.VK_Z) {
                    isFiring = false;
                }
            }
        };
    }

    public abstract void fireCannon();

    @Override
    public void onDestruction() {
        isInactive = true;
        super.onDestruction();
    }

    @Override
    public void coreEntityLoop() {
        super.coreEntityLoop();

        if(isFiring && (loadedBullet != null)) {
            if(time >= fireRate) {
                time = 0;
                fireCannon();
            } else {
                time++;
            }
        } else {
            time = 0;
        }
    }

    @Override
    public void notifyOnGameReception(GameManager gameManager) {
        setGameManager(gameManager);
        gameManager.addListener(fireCommand);
        gameManager.add(this);
    }

    @Override
    public void notifyOnDestruction(GameManager gameManager) {
        gameManager.removeListener(fireCommand);
        gameManager.remove(this);
    }

    @Override
    public void notifyOnMovement(Coordinates coordinates) {
        setCoordinates(coordinates.getX() + xOffset, coordinates.getY() + yOffet);
    }

    @Override
    public boolean isDestroyed() {
        return isInactive;
    }
}
