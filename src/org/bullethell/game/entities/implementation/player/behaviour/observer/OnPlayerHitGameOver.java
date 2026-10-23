package org.bullethell.game.entities.implementation.player.behaviour.observer;

import org.bullethell.game.entities.model.interfaces.Player;
import org.bullethell.game.entities.model.observer.EntityObserver;
import org.bullethell.game.logic.game.GameManager;

public final class OnPlayerHitGameOver implements EntityObserver {
    private final Player player;
    private final GameManager gameManager;

    public OnPlayerHitGameOver(Player player, GameManager gameManager) {
        this.player = player;
        this.gameManager = gameManager;
    }

    @Override
    public void notifyOnHitTaken() {
        if(player.getPlayerStats().getLives() == 0) {
            gameManager.gameOver();
        }
    }
}
