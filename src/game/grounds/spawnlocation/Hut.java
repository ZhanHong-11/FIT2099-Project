package game.grounds.spawnlocation;

import game.spawners.Spawnable;

/**
 * a class which represents a Hut where it extends to SpawnGround
 *
 * @see SpawnGround
 */
public class Hut extends SpawnGround {

  /**
   * Constructs a new Hut
   *
   * @param spawnable represents an Enemy Factory that will spawn a certain type of enemy in this
   *                  type of ground
   */
  public Hut(Spawnable spawnable) {
    super('h', spawnable);
  }
}
