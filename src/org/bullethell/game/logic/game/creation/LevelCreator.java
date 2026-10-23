package org.bullethell.game.logic.game.creation;

import org.bullethell.game.entities.physics.movement.LinearMovement;
import org.bullethell.game.entities.physics.movement.MovementSequence;
import org.bullethell.game.entities.physics.movement.wrapper.MovementTimeLimitWrapper;
import org.bullethell.game.logic.game.creation.command.*;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.utils.Coordinates;

import java.awt.*;
import java.util.*;
import java.util.List;

@SuppressWarnings("SameParameterValue")
public abstract class LevelCreator {
    private final Map<Integer, List<Command>> commandChannels;
    private final List<Command> commands;
    private int currentCommand;

    private boolean hasMainChannelCommandFinished, hasLevelFinished;

    protected GameManager gameManager;

    public abstract List<Command> createCommandsList();
    public abstract LevelCreator nextLevel();
    public abstract long getBaseLevelCompletionScore();
    public abstract boolean isFinalLevel();

    public LevelCreator() {
        commandChannels = new HashMap<>();
        commandChannels.put(0, new LinkedList<>());
        commands = new ArrayList<>();
        currentCommand = 0;

        hasMainChannelCommandFinished = true;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
        resetCommands();
    }

    public void execute() {
        updateCommandChannels();
        runCommandChannels();
    }

    public void reset() {
        resetCommandChannels();
        resetCommands();

        hasMainChannelCommandFinished = true;
        hasLevelFinished = false;
    }

    public boolean isFinished() {
        return hasLevelFinished;
    }

    public void addCommand(Command command) {
        if(!isFinished()) {
            commands.add(command);
        }
    }

    protected final Command musicPlayText(int channel, String text) {
        return new CommandAddText(channel,
                "♫ " + text + " ♫",
                new Font("Times New Roman", Font.ITALIC | Font.BOLD, 24),
                new Coordinates(gameManager.getPlayfieldWidth() + 32, gameManager.getPlayfieldHeight() - 16),
                Color.WHITE, gameManager.getPlayfieldWidth() - 160,
                new MovementSequence(
                        new MovementTimeLimitWrapper(92, new LinearMovement(180, 4))
                ).add(
                        new MovementTimeLimitWrapper(150, new LinearMovement(0, 0.66f)),
                        MovementSequence.DirectionChange.CONTINUOUS
                ).add(
                        new MovementTimeLimitWrapper(150, new LinearMovement(0, 8)),
                        MovementSequence.DirectionChange.CONTINUOUS
                )
        );
    }

    protected final Command popUpText(int channel, String text, Coordinates coordinates) {
        return new CommandAddText(channel,
                text, new Font("Arial", Font.PLAIN | Font.ITALIC, 18),
                coordinates, Color.WHITE, 160,
                new MovementSequence(
                        new MovementTimeLimitWrapper(35, new LinearMovement(180, 8))
                ).add(
                        new MovementTimeLimitWrapper(100, new LinearMovement(0, 0)),
                        MovementSequence.DirectionChange.CONTINUOUS
                ).add(
                        new LinearMovement(180, 8),
                        MovementSequence.DirectionChange.CONTINUOUS
                )
        );
    }

    private void updateCommandChannels() {
        if(currentCommand < commands.size()) {
            final Command command = commands.get(currentCommand);
            final int channelNumber = command.getChannel();
            List<Command> commandChannel = commandChannels.computeIfAbsent(channelNumber, _ -> new LinkedList<>());

            if(channelNumber != 0) {
                commandChannel.add(command);
                currentCommand++;
            } else {
                if(hasMainChannelCommandFinished) {
                    commandChannel.add(command);
                    currentCommand++;

                    hasMainChannelCommandFinished = false;
                }
            }
        } else {
            if(haveAllChannelsFinished()) {
                hasLevelFinished = true;
            }
        }
    }

    private void runCommandChannels() {
        commandChannels.forEach((_, commands) -> {
            if(!commands.isEmpty()) {
                final Command command = commands.getFirst();
                command.execute(gameManager);

                if(command.hasFinished()) {
                    commands.removeFirst();

                    if(command.getChannel() == 0) {
                        hasMainChannelCommandFinished = true;
                    }
                }
            }
        });
    }

    private boolean haveAllChannelsFinished() {
        boolean hasFinished = true;
        for(Map.Entry<Integer, List<Command>> entry : commandChannels.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                hasFinished = false;
                break;
            }
        }

        return hasFinished;
    }

    private void resetCommandChannels() {
        commandChannels.forEach((_, commands) -> commands.clear());
        commandChannels.clear();
        commandChannels.put(0, new LinkedList<>());
    }

    private void resetCommands() {
        commands.clear();
        commands.addAll(createCommandsList());
        currentCommand = 0;
    }
}
