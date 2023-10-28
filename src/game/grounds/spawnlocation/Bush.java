package game.grounds.spawnlocation;

import game.spawners.Spawnable;

/**
 * a class which represents a Bush where it extends to SpawnGround
 *
 * @see SpawnGround
 */
public class Bush extends SpawnGround {

  /**
   * Constructs a new Hut
   *
   * @param spawnable represents an Enemy Factory that will spawn a certain type of enemy in this
   *                  type of ground
   */
  public Bush(Spawnable spawnable) {
    super('m', spawnable);
  }
}
