package org.bullethell.game.logic.game.creation.command;

import org.bullethell.game.entities.model.interfaces.Player;
import org.bullethell.game.graphics.components.shapes.simple.ShapeFilledRectangle;
import org.bullethell.game.graphics.components.shapes.simple.ShapeRectangle;
import org.bullethell.game.graphics.components.shapes.simple.TextComponent;
import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.logic.game.creation.LevelCreator;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;
import org.bullethell.game.utils.easingfunction.SineInOut;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "SameParameterValue"})
public final class CommandEndLevel extends Command {
    private final List<Command> endLevelCommands;
    private final GameManager gameManager;
    private final LevelCreator levelCreator;

    private final KeyListener keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if(haveAllCommandsFinished()) {
                finishCommand();
                gameManager.removeListener(keyListener);
                gameManager.initializeNextLevel(levelCreator.nextLevel());
            }
        }
    };

    public CommandEndLevel(int channel, GameManager gameManager, LevelCreator levelCreator) {
        super(channel);
        this.gameManager = gameManager;
        this.levelCreator = levelCreator;

        endLevelCommands = new ArrayList<>();
    }

    @Override
    public void oneTimeActions(GameManager gameManager) {
        gameManager.addListener(keyListener);
        gameManager.prepareNextLevelInitialization();

        final Player player = (Player) gameManager.getEntities(ObjectType.PLAYER).getFirst();
        final long lifeLeftScore = 25000L * player.getPlayerStats().getLives();
        final long playerBonusScore = lifeLeftScore + levelCreator.getBaseLevelCompletionScore();
        final long levelEndScore = playerBonusScore + player.getPlayerStats().getScore();

        player.getPlayerStats().incrementScore(playerBonusScore);
        player.setActive(false);

        final int stringContainerWidth = 256, stringContainerHeight = 160;
        final int baseStringX = (gameManager.getPlayfieldWidth() - stringContainerWidth) / 2;
        final int baseStringY = (gameManager.getPlayfieldHeight() - stringContainerHeight) / 2;

        endLevelCommands.add(new CommandWait(-1, 100));
        endLevelCommands.add(new CommandAddBGComponent(-1, new ShapeFilledRectangle(
                baseStringX - 8, baseStringY - 24,
                stringContainerWidth + 16, stringContainerHeight + 16,
                Color.BLACK
        )));
        endLevelCommands.add(new CommandAddBGComponent(-1, new ShapeRectangle(
                baseStringX - 8, baseStringY - 24,
                stringContainerWidth + 16, stringContainerHeight + 16,
                Color.WHITE
        )));
        endLevelCommands.add(new CommandPlaySound(-1, "whoosh"));
        endLevelCommands.add(endLevelStringLine(
                -1, "Level complete!",
                new Coordinates(baseStringX, baseStringY), 16)
        );
        endLevelCommands.add(new CommandWait(-1, 100));
        endLevelCommands.add(new CommandPlaySound(-1, "whoosh"));
        endLevelCommands.add(endLevelStringLine(
                -1, "Level completion bonus: " + levelCreator.getBaseLevelCompletionScore() + ".\n",
                new Coordinates(baseStringX, baseStringY + 32), 16
        ));
        endLevelCommands.add(new CommandWait(-1, 100));
        endLevelCommands.add(new CommandPlaySound(-1, "whoosh"));
        endLevelCommands.add(endLevelStringLine(
                -1,  player.getPlayerStats().getLives() +
                        (player.getPlayerStats().getLives() == 1 ? " life" : " lives") +
                        " left bonus: " + lifeLeftScore + ".\n",
                new Coordinates(baseStringX, baseStringY + 64), 16
        ));
        endLevelCommands.add(new CommandWait(-1, 100));
        endLevelCommands.add(new CommandPlaySound(-1, "whoosh"));
        endLevelCommands.add(endLevelStringLine(
                -1, "Final score: " + levelEndScore + ".\n",
                new Coordinates(baseStringX, baseStringY + 96), 16
        ));
        if(levelCreator.isFinalLevel()) {
            endLevelCommands.add(endLevelStringLine(
                    -1, "Congratulations! You won!",
                    new Coordinates(baseStringX, baseStringY + 128), 16
            ));
        }

        endLevelCommands.add(new CommandFadeMusic(-1, CommandFadeMusic.Type.FADE_OUT, 0.005f));
        endLevelCommands.add(new CommandSetBGGradientColor(-1, Color.BLACK, 200, new SineInOut()));
        endLevelCommands.add(new CommandRemoveBGComponent(-1, CommandRemoveBGComponent.Type.REMOVE_ALL));

        if(!levelCreator.isFinalLevel()) {
            endLevelCommands.add(endLevelStringLine(-1,
                    "Press any key to continue.",
                    new Coordinates(
                            gameManager.getPlayfieldWidth() / 2f - 100,
                            gameManager.getPlayfieldHeight() - 20
                    ),
                    18
            ));
        }

        endLevelCommands.forEach(levelCreator::addCommand);
    }

    @Override
    protected void coreActions(GameManager gameManager) {
        if(haveAllCommandsFinished() && levelCreator.isFinalLevel()) {
            finishCommand();
            gameManager.removeListener(keyListener);
            gameManager.endGame();
        }
    }

    private Command endLevelStringLine(int channel, String text, Coordinates coordinates, int fontSize) {
        return new CommandAddBGComponent(channel, new TextComponent(
                coordinates.getX(), coordinates.getY(),
                Color.WHITE, text, new Font("Arial", Font.ITALIC | Font.BOLD, fontSize)
        ));
    }

    private boolean haveAllCommandsFinished() {
        for(Command command : endLevelCommands) {
            if(!command.hasFinished()) {
                return false;
            }
        }
        return true;
    }
}
