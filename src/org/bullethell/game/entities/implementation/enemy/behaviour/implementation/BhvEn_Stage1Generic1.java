package org.bullethell.game.entities.implementation.enemy.behaviour.implementation;

import org.bullethell.game.attackpatterns.SpiralPattern;
import org.bullethell.game.entities.creation.factory.EnemyBulletFactory;
import org.bullethell.game.entities.implementation.enemybullet.GenericEnemyBullet;
import org.bullethell.game.entities.implementation.enemybullet.behaviour.wrapper.EnemyBulletPostEffectWrapper;
import org.bullethell.game.entities.implementation.misc.Effect;
import org.bullethell.game.entities.physics.collision.hitbox.CircularHitbox;
import org.bullethell.game.entities.physics.movement.CircularMovement;
import org.bullethell.game.entities.physics.movement.LineSegmentMovement;
import org.bullethell.game.entities.physics.movement.LinearMovement;
import org.bullethell.game.entities.physics.movement.MovementSequence;
import org.bullethell.game.entities.physics.movement.wrapper.KinematicVelocityLimitWrapper;
import org.bullethell.game.entities.physics.movement.wrapper.MovementTimeLimitWrapper;
import org.bullethell.game.graphics.components.bitmap.Animation;
import org.bullethell.game.graphics.components.bitmap.utils.SpriteCache;
import org.bullethell.game.logic.game.behaviour.ConcurrentBehaviours;
import org.bullethell.game.logic.game.behaviour.ConditionalBehaviour;
import org.bullethell.game.logic.game.behaviour.SequentialBehaviours;
import org.bullethell.game.logic.game.behaviour.action.ActionSetAttackPattern;
import org.bullethell.game.logic.game.behaviour.action.ActionSetMovement;
import org.bullethell.game.utils.easingfunction.SineOut;

public class BhvEn_Stage1Generic1 extends SequentialBehaviours {
    public BhvEn_Stage1Generic1() {
        super(
                new ConditionalBehaviour(new ActionSetMovement(
                        new LineSegmentMovement(
                                90, 300,
                                new SineOut(), 200
                        )
                )),
                new ConcurrentBehaviours(
                        new ConditionalBehaviour(new ActionSetMovement(
                                new MovementSequence(
                                        new MovementTimeLimitWrapper(
                                                200,
                                                new KinematicVelocityLimitWrapper(
                                                        new CircularMovement(
                                                                60, 0.0001f, 0.025f
                                                        ),
                                                        1f
                                                )
                                        )
                                ).add(
                                        new LinearMovement(0, 1),
                                        MovementSequence.DirectionChange.CONTINUOUS
                                )
                        )),
                        new ConditionalBehaviour(new ActionSetAttackPattern(
                                new SpiralPattern(
                                        EnemyBulletFactory.getInstance(), 7,
                                        new EnemyBulletPostEffectWrapper(
                                                new GenericEnemyBullet(
                                                        SpriteCache.getSprite("round_red_bullet"),
                                                        new LinearMovement(0, 1),
                                                        new CircularHitbox(3)
                                                ),
                                                new Effect(
                                                        new Animation("round_red_bullet_destroyed", 10)
                                                )
                                        ), 24, 11, 0
                                )
                        ))
                )
        );
    }
}
