package game.dream;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface that represents a dream capable subject. A dream capable subject is actors that can
 * be respawned, which basically is the player.
 */
public interface DreamCapable {

  /**
   * Let the resettable to subscribes to the dream capable subject. The resettable will be added
   * into a list storing in the dream capable subject.
   *
   * @param resettable The resettable that subscribes to the dream capable subject.
   */
  void subscribe(Resettable resettable);

  /**
   * Let the resettable to unsubscribes to the dream capable subject. The resettable will be removed
   * from a list storing in the dream capable subject.
   *
   * @param resettable The resettable that unsubscribes to the dream capable subject.
   */
  void unsubscribe(Resettable resettable);

  /**
   * Respawns the dream capable subject. The implementation of this method should specify the actual
   * details on how the subject is respawned.
   *
   * @param map The game map where the subject will be respawned.
   */
  void respawn(GameMap map);
}
