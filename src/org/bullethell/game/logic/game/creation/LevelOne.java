package org.bullethell.game.logic.game.creation;

import org.bullethell.game.attackpatterns.AttackPattern;
import org.bullethell.game.attackpatterns.SpiralPattern;
import org.bullethell.game.attackpatterns.wrapper.AttackDelayWrapper;
import org.bullethell.game.attackpatterns.wrapper.AttackDurationWrapper;
import org.bullethell.game.entities.implementation.enemybullet.GenericEnemyBullet;
import org.bullethell.game.entities.implementation.enemybullet.behaviour.wrapper.DirectionalWrapper;
import org.bullethell.game.entities.implementation.enemybullet.behaviour.wrapper.EnemyBulletPostEffectWrapper;
import org.bullethell.game.entities.implementation.enemybullet.behaviour.wrapper.ReboundWrapper;
import org.bullethell.game.entities.implementation.misc.Effect;
import org.bullethell.game.entities.implementation.misc.behaviour.wrapper.MiscPostEntityWrapper;
import org.bullethell.game.entities.model.interfaces.GameEntity;
import org.bullethell.game.entities.physics.collision.hitbox.CircularHitbox;
import org.bullethell.game.entities.physics.movement.*;
import org.bullethell.game.entities.physics.movement.wrapper.KinematicVelocityLimitWrapper;
import org.bullethell.game.entities.physics.movement.wrapper.MovementTimeLimitWrapper;
import org.bullethell.game.entities.physics.movement.wrapper.PlayerTargetedMovementWrapper;
import org.bullethell.game.graphics.components.bitmap.Animation;
import org.bullethell.game.graphics.components.bitmap.utils.SpriteCache;
import org.bullethell.game.graphics.components.bitmap.wrapper.BitmapRotationWrapper;
import org.bullethell.game.graphics.components.shapes.composite.CustomCompositeLoopedSpace;
import org.bullethell.game.graphics.components.shapes.composite.CustomCompositeStar;
import org.bullethell.game.graphics.components.shapes.composite.ListenerComposite;
import org.bullethell.game.graphics.components.shapes.simple.ShapeDot;
import org.bullethell.game.entities.creation.builder.EnemyBuilderImpl;
import org.bullethell.game.entities.creation.factory.EnemyBulletFactory;
import org.bullethell.game.graphics.components.shapes.wrapper.ColorFadeWrapper;
import org.bullethell.game.logic.game.creation.command.*;
import org.bullethell.game.utils.Coordinates;
import org.bullethell.game.utils.easingfunction.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class LevelOne extends LevelCreator {
    public LevelOne() {
        super();
    }

    @Override
    public List<Command> createCommandsList() {
        List<Command> commands = new ArrayList<>();

        /*commands.add(popUpText(1, "Get ready!", new Coordinates(
                gameManager.getPlayfieldWidth(), gameManager.getPlayfieldHeight() / 2f
        )));
        commands.add(new CommandWait(1,125));
        commands.add(popUpText(1, "Stage 1", new Coordinates(
                gameManager.getPlayfieldWidth(), gameManager.getPlayfieldHeight() / 2f + 24
        )));*/

        // commands.add(new CommandPlayMusic(0, "level1"));
        //commands.add(musicPlayText(0, "No music is playing..."));
        /*commands.add(new CommandWait(0,200));

        commands.add(new CommandAddBGComponent(0, new ColorFadeWrapper(new CustomCompositeLoopedSpace(
                0, 0.5f,
                gameManager.getPlayfieldWidth(), gameManager.getPlayfieldHeight(),
                new ShapeDot(8, 8, 4, Color.BLACK),
                new ShapeDot(160, 320, 4, Color.BLACK),
                new ShapeDot(320, 448, 4, Color.BLACK),
                new ShapeDot(480, 256, 4, Color.BLACK),
                new ShapeDot(256, 96, 4, Color.BLACK),
                new ShapeDot(64, 128, 4, Color.BLACK),
                new ShapeDot(576, 384, 4, Color.BLACK)
        ), Color.WHITE, 150, new SineInOut())));
        commands.add(new CommandWait(0,400));

        commands.add(new CommandSetBGGradientEndColor(0, new Color(10, 8, 23), 250, new SineInOut()));

        commands.add(new CommandSetBGGradientEndPoint(0,
                new Point(gameManager.getPlayfieldWidth(), gameManager.getPlayfieldHeight() / 2),
                250, new SineInOut())
        );
        commands.add(new CommandSetBGGradientStartPoint(1,
                new Point(0, gameManager.getPlayfieldHeight() / 2),
                250, new SineInOut())
        );
*/
        final GameEntity effectPrototype = new Effect(
                new Animation("round_red_bullet_destroyed", 15),
                new Coordinates(
                        gameManager.getPlayfieldWidth() / 2f,
                        224
                )
        );
        commands.add(new CommandAddEntity(0, new MiscPostEntityWrapper(
                (Effect) effectPrototype.cloneComponent(),
                new EnemyBuilderImpl((gameManager))
                        .setHealth(25)
                        .setScoreValue(30000)
                        .setGraphicsComponent(SpriteCache.getSprite("dummy_enemy_two"))
                        .setHitbox(new CircularHitbox(6))
                        .setMovementComponent(new MovementSequence(
                                new MovementTimeLimitWrapper(
                                        25,
                                        new LinearMovement(90, 1)
                                )
                        ).add(
                                new MovementTimeLimitWrapper(
                                        400,
                                        new CircularMovement(0, 1, 0)
                                ),
                                MovementSequence.DirectionChange.CONTINUOUS
                        ).add(
                                new LineSegmentMovement(
                                        0, 200,
                                        new SineOut(), 50
                                ),
                                MovementSequence.DirectionChange.CONTINUOUS
                        ).add(
                                new LinearMovement(0, 2),
                                MovementSequence.DirectionChange.CONTINUOUS
                        ))
                        .setAttackPattern(new AttackDelayWrapper(
                                new AttackDurationWrapper(new SpiralPattern(
                                        EnemyBulletFactory.getInstance(), 5,
                                        new EnemyBulletPostEffectWrapper(new GenericEnemyBullet(
                                                SpriteCache.getSprite("round_red_bullet"),
                                                new MovementSequence(
                                                        new LineSegmentMovement(
                                                                0, 150,
                                                                new SineOut(), 100
                                                        )
                                                ).add(
                                                        new CompositeMovement(
                                                                new KinematicVelocityLimitWrapper(
                                                                        new CircularMovement(
                                                                                125, -0.0001f, 0.015f,
                                                                                90
                                                                        ),
                                                                        0.75f
                                                                ),
                                                                new KinematicVelocityLimitWrapper(
                                                                        new LinearMovement(
                                                                                0, 0, 0.035f
                                                                        ),
                                                                        1f
                                                                ),
                                                                new KinematicVelocityLimitWrapper(
                                                                        new CircularMovement(
                                                                                25, 0, 0.025f,
                                                                                0
                                                                        ),
                                                                        0.85f
                                                                )
                                                        ),
                                                        MovementSequence.DirectionChange.ADDITIVE
                                                ),
                                                new CircularHitbox(3)
                                        ), new Effect(
                                                new Animation("round_red_bullet_destroyed", 10)
                                        )), 18, 6, 90
                                ), 145),
                                25
                        ))
                        /*.setAttackPattern(new AttackDelayWrapper(
                                new AttackDurationWrapper(new SpiralPattern(
                                        EnemyBulletFactory.getInstance(), 7,
                                        new EnemyBulletPostEffectWrapper(new GenericEnemyBullet(
                                                SpriteCache.getSprite("round_red_bullet"),
                                                new MovementSequence(
                                                        new LineSegmentMovement(
                                                                0, 50,
                                                                new SineOut(), 75
                                                        )
                                                ).add(
                                                        new LineSegmentMovement(
                                                                60, 50,
                                                                new SineOut(), 75
                                                        ),
                                                        MovementSequence.DirectionChange.ADDITIVE
                                                ).add(
                                                        new LineSegmentMovement(
                                                                -60, 50,
                                                                new SineOut(), 75
                                                        ),
                                                        MovementSequence.DirectionChange.ADDITIVE
                                                ).add(
                                                        new LineSegmentMovement(
                                                                60, 50,
                                                                new SineOut(), 75
                                                        ),
                                                        MovementSequence.DirectionChange.ADDITIVE
                                                ).add(
                                                        new LineSegmentMovement(
                                                                -60, 50,
                                                                new SineOut(), 75
                                                        ),
                                                        MovementSequence.DirectionChange.ADDITIVE
                                                ).add(
                                                        new LineSegmentMovement(
                                                                90, 100,
                                                                new SineOut(), 150
                                                        ),
                                                        MovementSequence.DirectionChange.ADDITIVE
                                                ).add(
                                                        new LinearMovement(-90, 3),
                                                        MovementSequence.DirectionChange.ADDITIVE
                                                ),
                                                new CircularHitbox(3)
                                        ), new Effect(
                                                new Animation("round_red_bullet_destroyed", 10)
                                        )),
                                        8, 7
                                ), 400),
                                25
                        ))*/
                        .build()
        )));
        commands.add(new CommandWait(50000));

        commands.add(new CommandAddEntity(0, new MiscPostEntityWrapper(
                (Effect) effectPrototype.cloneComponent(),
                new EnemyBuilderImpl(gameManager)
                        .setHealth(25)
                        .setScoreValue(30000)
                        .setGraphicsComponent(SpriteCache.getSprite("dummy_enemy_two"))
                        .setMovementComponent(new PlayerTargetedMovementWrapper(new KinematicVelocityLimitWrapper(
                                new LinearMovement(0, 0, 0.015f),
                                3
                        ), gameManager))
                        .setHitbox(new CircularHitbox(6))
                        .build()
        )));
        commands.add(new CommandWait(100));
        commands.add(new CommandAddEntity(0, new MiscPostEntityWrapper(
                (Effect) effectPrototype.cloneComponent(),
                new EnemyBuilderImpl(gameManager)
                        .setHealth(25)
                        .setScoreValue(30000)
                        .setGraphicsComponent(SpriteCache.getSprite("dummy_enemy"))
                        .setMovementComponent(new PlayerTargetedMovementWrapper(new KinematicVelocityLimitWrapper(
                                new LinearMovement(0, 0, 0.02f),
                                4
                        ), gameManager))
                        .setHitbox(new CircularHitbox(6))
                        .build()
        )));
        commands.add(new CommandWait(100));
        commands.add(new CommandAddEntity(0, new MiscPostEntityWrapper(
                (Effect) effectPrototype.cloneComponent(),
                new EnemyBuilderImpl(gameManager)
                        .setHealth(50)
                        .setScoreValue(90000)
                        .setLifeValue(1)
                        .setGraphicsComponent(SpriteCache.getSprite("dummy_enemy_two"))
                        .setMovementComponent(new PlayerTargetedMovementWrapper(new KinematicVelocityLimitWrapper(
                                new LinearMovement(0, 0, 0.0075f),
                                1
                        ), gameManager))
                        .setAttackPattern(new AttackDelayWrapper(new SpiralPattern(
                                EnemyBulletFactory.getInstance(), 7,
                                new EnemyBulletPostEffectWrapper(new GenericEnemyBullet(
                                        SpriteCache.getSprite("round_red_bullet"),
                                        new LinearMovement(0, 2, 0),
                                        new CircularHitbox(3)
                                ), new Effect(
                                        new Animation("round_red_bullet_destroyed", 10)
                                )),
                                2, 9, 0
                        ), 25))
                        .setHitbox(new CircularHitbox(6))
                        .build()
        )));

        commands.add(new CommandSetBGGradientStartColor(1, new Color(37, 34, 41), 400, new SineInOut()));
        commands.add(new CommandWait(400));

        commands.add(new CommandAddEntity(0, new EnemyBuilderImpl(gameManager)
                .setHealth(10)
                .setScoreValue(10000)
                .setPosition(-8, 16)
                .setGraphicsComponent(SpriteCache.getSprite("dummy_enemy_two"))
                .setMovementComponent(new LinearMovement(
                        10, 3
                ))
                .setHitbox(new CircularHitbox(6))
                .build()
        ));
        commands.add(new CommandWait(50));
        commands.add(new CommandAddEntity(0, new EnemyBuilderImpl(gameManager)
                .setHealth(10)
                .setScoreValue(10000)
                .setPosition(-8, 24)
                .setGraphicsComponent(SpriteCache.getSprite("dummy_enemy"))
                .setMovementComponent(new LinearMovement(
                        7.5f, 3
                ))
                .setHitbox(new CircularHitbox(6))
                .build()
        ));
        commands.add(new CommandWait(50));
        commands.add(new CommandAddEntity(0, new EnemyBuilderImpl(gameManager)
                .setHealth(10)
                .setScoreValue(10000)
                .setPosition(-8, 32)
                .setGraphicsComponent(SpriteCache.getSprite("dummy_enemy_two"))
                .setMovementComponent(new LinearMovement(
                        5, 3
                ))
                .setHitbox(new CircularHitbox(6))
                .build()
        ));
        commands.add(new CommandWait(150));

        final AttackPattern genericPattern1 = new AttackDelayWrapper(new SpiralPattern(
                EnemyBulletFactory.getInstance(), 7,
                new EnemyBulletPostEffectWrapper(new DirectionalWrapper(new GenericEnemyBullet(
                        new BitmapRotationWrapper(SpriteCache.getSprite("blue_bullet")),
                        new PlayerTargetedMovementWrapper(new KinematicVelocityLimitWrapper(
                                new LinearMovement(0, 0, 0.015f),
                                4
                        ), gameManager),
                        new CircularHitbox(3)
                ), DirectionalWrapper.Type.ALWAYS_SET_DIRECTION), new Effect(
                        new Animation("blue_bullet_destroyed", 10)
                )),
                1, 0, 0
        ), 25);

        commands.add(new CommandAddEntity(0, new EnemyBuilderImpl(gameManager)
                .setHealth(10)
                .setScoreValue(10000)
                .setPosition(-8, 16)
                .setGraphicsComponent(SpriteCache.getSprite("dummy_enemy"))
                .setMovementComponent(new LinearMovement(
                        7.5f, 1.75f
                ))
                .setHitbox(new CircularHitbox(6))
                .setAttackPattern((AttackPattern) genericPattern1.cloneComponent())
                .build()
        ));
        commands.add(new CommandWait(50));
        commands.add(new CommandAddEntity(0, new EnemyBuilderImpl(gameManager)
                .setHealth(10)
                .setScoreValue(10000)
                .setPosition(-8, 24)
                .setGraphicsComponent(SpriteCache.getSprite("dummy_enemy_two"))
                .setMovementComponent(new LinearMovement(
                        10, 1.75f
                ))
                .setHitbox(new CircularHitbox(6))
                .setAttackPattern((AttackPattern) genericPattern1.cloneComponent())
                .build()
        ));

        commands.add(new CommandWait(400));
        // commands.add(new CommandPlayMusic("boss1"));
        // commands.add(musicPlayText(0, "Touhou 05 Kaikidan - Mystic Square"));

        commands.add(new CommandAddBoss(0, new EnemyBuilderImpl(gameManager)
                .setHealth(150)
                .setGraphicsComponent(SpriteCache.getSprite("dummy_enemy_two"))
                .setPosition(-32, 64)
                /*.setMovementComponent(
                        new LineSegmentMovement(
                                new Coordinates(gameManager.getPlayfieldWidth() / 2f, 320),
                                50, new SineOut()
                        )
                )*/
                .setAttackPattern(new AttackDelayWrapper(new SpiralPattern(
                        EnemyBulletFactory.getInstance(), 10,
                        new EnemyBulletPostEffectWrapper(new DirectionalWrapper(new ReboundWrapper(new GenericEnemyBullet(
                                new BitmapRotationWrapper(SpriteCache.getSprite("blue_bullet")),
                                new LinearMovement(0, 2, 0),
                                new CircularHitbox(3)
                        )), DirectionalWrapper.Type.ALWAYS_SET_DIRECTION), new Effect(
                                new Animation("blue_bullet_destroyed", 10)
                        )),
                        3, 7, 225
                ), 50))
                .addObserver(new ListenerComposite(
                        new CustomCompositeStar(128, 32, -2, 0, 3, Color.RED)
                ))
                .build()
        ));
        commands.add(new CommandEndLevel(0, gameManager, this));

        return commands;
    }

    @Override
    public LevelCreator nextLevel() {
        final LevelCreator nextLevel = new LevelTwo();
        nextLevel.setGameManager(gameManager);

        return nextLevel;
    }

    @Override
    public long getBaseLevelCompletionScore() {
        return 250000;
    }

    @Override
    public boolean isFinalLevel() {
        return false;
    }
}
