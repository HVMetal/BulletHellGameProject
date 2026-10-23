package org.bullethell.game.entities.creation;

import org.bullethell.game.entities.model.interfaces.Bonus;
import org.bullethell.game.logic.game.GameManager;

public final class BonusChain extends BonusChainLink {
    private final Bonus bonusPrototype;
    private final float xSpread, ySpread;

    public BonusChain(Bonus bonusPrototype) {
        this(bonusPrototype, 0, 0);
    }

    public BonusChain(Bonus bonusPrototype, float xSpread, float ySpread) {
        this.bonusPrototype = bonusPrototype;
        this.xSpread = xSpread;
        this.ySpread = ySpread;
    }

    @Override
    public void create(GameManager manager, float relativeX, float relativeY, int scoreValue) {
        final int scoreObjects = scoreValue / bonusPrototype.getBonusValue();

        for(int i = 0; i < scoreObjects; i++) {
            final float x = relativeX + (float) (Math.random() * xSpread - Math.random() * xSpread);
            final float y = relativeY + (float) (Math.random() * ySpread - Math.random() * ySpread);
            final Bonus newScore = (Bonus) bonusPrototype.cloneComponent();

            newScore.setCoordinates(x, y);
            newScore.setGameManager(manager);
            manager.add(newScore);
        }

        next(manager, relativeX, relativeY, scoreValue % bonusPrototype.getBonusValue());
    }
}
