package game.dream;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface that represents a resettable subject. A resettable subject is actors that is
 * affected when the DreamCapable subject is respawned.
 */
public interface Resettable {

  /**
   * Resets the resettable subject. The implementation of this method should specify the actual
   * details on how the subject is reset.
   *
   * @param map The game map where the subject will be reset.
   */
  void reset(GameMap map);
}
