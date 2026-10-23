package org.bullethell.game.entities.creation;

import org.bullethell.game.logic.game.GameManager;

public abstract class BonusChainLink {
    private BonusChainLink next;

    public static BonusChainLink link(BonusChainLink first, BonusChainLink... chain) {
        BonusChainLink head = first;
        for(BonusChainLink next : chain) {
            head.next = next;
            head = next;
        }

        return first;
    }

    public abstract void create(GameManager panel, float x, float y, int bonusValue);

    protected void next(GameManager panel, float x, float y, int bonusValue) {
        if(next == null) {
            return;
        }
        next.create(panel, x, y, bonusValue);
    }
}
