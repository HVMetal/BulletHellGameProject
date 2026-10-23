package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.entities.model.interfaces.Enemy;
import org.bullethell.game.entities.model.observer.EntityObserver;
import org.bullethell.game.logic.game.GameManager;

@SuppressWarnings("FieldCanBeLocal")
public final class CommandAddBoss extends Command {
    private final Enemy boss;
    private final EntityObserver bossDefeatObserver = new EntityObserver() {
        @Override
        public void notifyOnDestruction(GameManager gameManager) {
            finishCommand();
        }
    };

    private boolean bossAdded;

    public CommandAddBoss(int channel, Enemy boss) {
        super(channel);
        this.boss = boss;
        boss.addObserver(bossDefeatObserver);
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        if(!bossAdded) {
            bossAdded = true;
            gameManager.add(boss);
        }
    }
}
