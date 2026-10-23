package org.bullethell.game.entities.implementation.player.behaviour.misc;

import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.model.interfaces.PlayerBullet;
import org.bullethell.game.entities.physics.collision.hitbox.Hitbox;
import org.bullethell.game.entities.physics.movement.LinearMovement;
import org.bullethell.game.graphics.components.GraphicsComponent;

public final class GenericBlaster extends PlayerCannon {
    private final int[][] offsets = new int[][]{{0, -4, 270}, {-12, 4, 269}, {12, 4, 271}, {-18, 10, 268}, {18, 10, 272}};
    private final int bulletSpeed;

    public GenericBlaster(float xOffset, float yOffset, int fireRate, int bulletSpeed, PlayerBullet loadedBullet) {
        super(xOffset, yOffset, fireRate, loadedBullet);
        this.bulletSpeed = bulletSpeed;
    }

    @Override
    public void fireCannon() {
        for(int[] offset : offsets) {
            final PlayerBullet playerBullet = (PlayerBullet) loadedBullet.cloneComponent();

            playerBullet.setGraphicsComponent((GraphicsComponent) loadedBullet.getGraphicsComponent().cloneComponent());
            playerBullet.setHitbox((Hitbox) loadedBullet.getHitbox().cloneComponent());
            playerBullet.setCoordinates(getCoordinates().getX() + offset[0], getCoordinates().getY() + offset[1]);

            final LinearMovement bulletMovement = new LinearMovement(offset[2], bulletSpeed);
            playerBullet.setMovementComponent(bulletMovement);

            gameManager.add(playerBullet);
        }
    }

    @Override
    public GameEntity cloneComponent() {
        return null;
    }
}
