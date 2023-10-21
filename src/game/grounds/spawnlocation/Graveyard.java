package game.grounds.spawnlocation;

import game.spawners.Spawnable;

/**
 * A class that represents the graveyard, where it extends SpawnGround.
 *
 * @see SpawnGround
 */
public class Graveyard extends SpawnGround {

  /**
   * Constructs a new Graveyard
   *
   * @param spawnable represents an Enemy Factory that will spawn a certain type of enemy in this
   *                  type of ground
   */
  public Graveyard(Spawnable spawnable) {
    super('n', spawnable);
  }
}
