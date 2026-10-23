package org.bullethell.game.entities.model.interfaces;

import org.bullethell.game.entities.physics.collision.Collidable;
import org.bullethell.game.entities.physics.collision.CollisionVisitor;

public interface CollisionEntity extends GameEntity, Collidable, CollisionVisitor {}
