package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.logic.game.GameManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class CommandAddPressToContinue extends Command {
    private final GameManager gameManager;
    private final KeyListener keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            finishCommand();
            gameManager.removeListener(keyListener);
        }
    };

    public CommandAddPressToContinue(int channel, GameManager gameManager) {
        super(channel);
        this.gameManager = gameManager;
        gameManager.addListener(keyListener);
    }

    @Override
    protected void coreActions(GameManager gameManager) {}
}
