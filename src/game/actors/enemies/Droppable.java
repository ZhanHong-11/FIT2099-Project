package game.actors.enemies;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface that represents a droppable subject. A droppable subject is actors that can drop an
 * item when it is killed.
 */
public interface Droppable {

  /**
   * Drops an item when the enemy is killed. The implementation of this method should specify what
   * item to drop, the probability and where to drop it.
   *
   * @param map The game map where the enemy is located.
   */
  void drop(GameMap map);
}
