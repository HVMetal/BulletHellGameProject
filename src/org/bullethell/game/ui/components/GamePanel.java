package org.bullethell.game.ui.components;

import org.bullethell.game.audio.management.MusicPlayer;
import org.bullethell.game.audio.management.SoundCache;
import org.bullethell.game.entities.model.interfaces.*;
import org.bullethell.game.entities.physics.collision.management.SpatialHashGrid;
import org.bullethell.game.entities.physics.collision.management.SpatialPartition;
import org.bullethell.game.entities.EntityCollisionManager;
import org.bullethell.game.entities.EntityDisplayManager;
import org.bullethell.game.entities.EntityCoreEventManager;
import org.bullethell.game.entities.creation.factory.PlayerFactory;
import org.bullethell.game.ui.components.background.GameBackgroundManager;
import org.bullethell.game.logic.game.creation.LevelCreator;
import org.bullethell.game.entities.model.observer.EntityObserver;
import org.bullethell.game.entities.implementation.player.behaviour.observer.OnPlayerHitClearBullets;
import org.bullethell.game.entities.implementation.player.behaviour.observer.OnPlayerHitGameOver;
import org.bullethell.game.entities.storage.ObjectType;
import org.bullethell.game.entities.storage.StorageManager;
import org.bullethell.game.ui.GameFrame;
import org.bullethell.game.logic.game.GameManager;
import org.bullethell.game.ui.display.GameDisplayManager;
import org.bullethell.game.utils.Coordinates;
import org.bullethell.game.utils.HighscoreUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
public final class GamePanel extends ActiveFramePanel implements GameManager {
    private final Playfield playfield;
    private final GameGUI gameGUI;

    private final PauseScreen pauseScreen;
    private final GameOverScreen gameOverScreen;

    private final StorageManager storageManager;
    private final SpatialPartition<CollisionEntity> spatialPartition;

    private final GameDisplayManager displayManager = new GameDisplayManager(this);
    private final GameBackgroundManager backgroundManager;
    private final EntityCoreEventManager entityCoreEventManager;
    private final EntityCollisionManager entityCollisionManager;
    private final EntityDisplayManager entityDisplayManager;

    private final static int GUI_WIDTH = 192;
    private final static int COMPONENT_GAP = 8;
    private final static int BORDER_THICKNESS = 3;

    private PlayerFactory playerFactory;
    private Player gamePlayer;

    private Timer timer;
    private LevelCreator levelCreator;

    private boolean isPaused, isTheGameOver;

    private final KeyListener pauseKeyEvent = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if(!isPaused() && keyCode == KeyEvent.VK_ESCAPE) {
                pause();
                return;
            }
            if(isPaused() && keyCode == KeyEvent.VK_ESCAPE) {
                SoundCache.play("cancel");
                resume();
            }
        }
    };

    private final ActionListener exitButtonOption = _ -> {
        SoundCache.play("select");
        prepareToExit();
        gameFrame.setActivePanel(new MenuPanel(gameFrame));
    };

    private final ActionListener continueButtonOption = _ -> {
        SoundCache.play("select");
        resume();
    };

    private final ActionListener restartButtonOption = _ -> {
        SoundCache.play("select");
        restart();
    };

    private final EntityObserver playerHitclearBullets,  playerGameOverTrigger;

    public GamePanel(GameFrame gameFrame, LevelCreator levelCreator, PlayerFactory playerFactory) {
        super(gameFrame);

        this.levelCreator = levelCreator;
        this.playerFactory = playerFactory;

        levelCreator.setGameManager(this);

        storageManager = new StorageManager();
        spatialPartition = new SpatialHashGrid(getPlayfieldWidth(), getPlayfieldHeight(), 32);

        backgroundManager = new GameBackgroundManager(this);
        entityCoreEventManager = new EntityCoreEventManager(this);
        entityCollisionManager = new EntityCollisionManager(this);
        entityDisplayManager = new EntityDisplayManager(this);

        gameGUI = new GameGUI(GUI_WIDTH, getPlayfieldHeight());
        playfield = new Playfield(displayManager.getGraphicsConfiguration(),
                getPlayfieldWidth(), getPlayfieldHeight());

        pauseScreen = new PauseScreen(
                getPlayfieldWidth(), getPlayfieldHeight(),
                continueButtonOption, restartButtonOption, exitButtonOption
        );

        gameOverScreen = new GameOverScreen(
                getPlayfieldWidth(), getPlayfieldHeight(),
                restartButtonOption, exitButtonOption
        );

        final JLayeredPane playfieldPanel = new JLayeredPane();
        playfieldPanel.setBackground(Color.BLACK);
        playfieldPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDER_THICKNESS));
        playfieldPanel.setLayout(new OverlayLayout(playfieldPanel));
        playfieldPanel.setOpaque(false);

        playfieldPanel.add(playfield, Integer.valueOf(0));
        playfieldPanel.add(pauseScreen, Integer.valueOf(1));
        playfieldPanel.add(gameOverScreen, Integer.valueOf(2));
        pauseScreen.setVisible(false);
        gameOverScreen.setVisible(false);

        final JPanel contentPanel = new JPanel();
        final GroupLayout groupLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(groupLayout);
        contentPanel.setOpaque(false);

        this.add(contentPanel);

        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGap(COMPONENT_GAP)
                        .addComponent(playfieldPanel)
                        .addGap(COMPONENT_GAP)
                        .addComponent(gameGUI)
                        .addGap(COMPONENT_GAP)
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup()
                        .addGap(COMPONENT_GAP)
                        .addComponent(playfieldPanel)
                        .addGap(COMPONENT_GAP)
                        .addComponent(gameGUI)
                        .addGap(COMPONENT_GAP)
        );

        addListener(pauseKeyEvent);

        gamePlayer = playerFactory.create(new Coordinates(
                getPlayfieldWidth() / 2f, getPlayfieldHeight() - 64
        ));

        playerHitclearBullets = new OnPlayerHitClearBullets(this);
        playerGameOverTrigger = new OnPlayerHitGameOver(gamePlayer, this);

        gamePlayer.addObserver(playerHitclearBullets);
        gamePlayer.addObserver(playerGameOverTrigger);

        add(gamePlayer);
    }

    private class EventLoop extends TimerTask {
        @Override
        public void run() {
            SwingUtilities.invokeLater(() -> {
                if(!isPaused() && !isTheGameOver()) {
                    coreObjectEvents();
                    levelCreator.execute();

                    displayManager.repaintField();
                    gameGUI.update(storageManager.get(ObjectType.PLAYER));
                }
            });
        }
    }

    @Override
    public void finalizePanelSetup() {
        super.finalizePanelSetup();
        playfield.createBufferStrategy(2);
        displayManager.finalizeSetup(playfield);

        timer = new Timer();
        timer.scheduleAtFixedRate(new EventLoop(), 0, 10);
    }

    @Override
    public void coreObjectEvents() {
        entityCoreEventManager.execute();
        entityCollisionManager.execute();
    }

    @Override
    public void repaintPlayfield(Graphics g) {
        backgroundManager.display(g);
        entityDisplayManager.display(g);
    }

    @Override
    public void gameOver() {
        isTheGameOver = true;
        MusicPlayer.stop();

        removeListener(pauseKeyEvent);

        gameOverScreen.setBackground(displayManager.captureScreen());
        gameOverScreen.setVisible(true);

        repaint(100);
        revalidate();
    }

    @Override
    public void prepareNextLevelInitialization() {
        for(ObjectType objectType : ObjectType.values()) {
            if(objectType == ObjectType.PLAYER) {
                continue;
            }

            final List<GameEntity> entities = getEntities(objectType);
            for(GameEntity gameEntity : entities) {
                gameEntity.setActive(false);
            }
        }
        removeListener(pauseKeyEvent);
    }

    @Override
    public void initializeNextLevel(LevelCreator levelCreator) {
        clear();
        this.levelCreator = levelCreator;

        add(gamePlayer);
        gamePlayer.setCoordinates(getPlayfieldWidth() / 2f, getPlayfieldHeight() - 64);

        MusicPlayer.stop();
        MusicPlayer.setVolume(1.0);
        addListener(pauseKeyEvent);
    }

    @Override
    public void endGame() {
        final long playerScore = gamePlayer.getPlayerStats().getScore();

        HighscoreUtils.recordScore(playerScore);
        prepareToExit();
        gameFrame.setActivePanel(new LeaderboardPanel(gameFrame));
    }

    @Override
    public int getPlayfieldWidth() {
        return 702 - GUI_WIDTH - 3 * COMPONENT_GAP;
    }

    @Override
    public int getPlayfieldHeight() {
        return 720 - 2 * COMPONENT_GAP;
    }

    @Override
    public void add(GameEntity entity) {
        entity.setGameManager(this);
        entity.setActive(true);

        if(entity instanceof CollisionEntity collisionEntity) {
            spatialPartition.add(collisionEntity);
        }
        storageManager.add(entity);
    }

    @Override
    public void remove(GameEntity entity) {
        if(entity instanceof CollisionEntity collisionEntity) {
            spatialPartition.remove(collisionEntity);
        }
        storageManager.remove(entity);
    }

    @Override
    public List<GameEntity> getEntities(ObjectType entityType) {
        return storageManager.get(entityType);
    }

    @Override
    public GameBackgroundManager getBackgroundManager() {
        return backgroundManager;
    }

    @Override
    public SpatialPartition<CollisionEntity> getSpatialPartition() {
        return spatialPartition;
    }

    @Override
    public void addListener(KeyListener keyListener) {
        gameFrame.addKeyListener(keyListener);
    }

    @Override
    public void removeListener(KeyListener keyListener) {
        gameFrame.removeKeyListener(keyListener);
    }

    @Override
    public void clear() {
        storageManager.clear();
        spatialPartition.clear();
        backgroundManager.clear();
    }

    private boolean isTheGameOver() {
        return isTheGameOver;
    }

    private boolean isPaused() {
        return isPaused;
    }

    private void pause() {
        SoundCache.play("pause");
        MusicPlayer.pause();

        isPaused = true;
        pauseScreen.setBackground(displayManager.captureScreen());
        pauseScreen.setVisible(true);

        repaint(100);
        revalidate();
    }

    private void resume() {
        MusicPlayer.unpause();

        isPaused = false;
        pauseScreen.setVisible(false);
    }

    private void restart() {
        removeListener(pauseKeyEvent);
        pauseScreen.setVisible(false);
        gameOverScreen.setVisible(false);

        isTheGameOver = false;
        isPaused = false;

        MusicPlayer.rewind();
        MusicPlayer.stop();
        MusicPlayer.setVolume(1.0);

        clear();
        levelCreator.reset();

        add(gamePlayer);
        gamePlayer.setCoordinates(getPlayfieldWidth() / 2f, getPlayfieldHeight() - 64);
        gamePlayer.getPlayerStats().setValues(playerFactory.getDefaultPlayerValues());

        addListener(pauseKeyEvent);
    }

    private void prepareToExit() {
        MusicPlayer.stop();
        timer.cancel();
        removeListener(pauseKeyEvent);
        clear();
        displayManager.dispose();
    }

}
