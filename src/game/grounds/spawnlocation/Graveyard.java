package game.grounds.spawnlocation;

import game.spawners.EnemyFactory;

/**
 * A class that represents the graveyard, where it extends SpawnGround Created by:
 *
 * @author Soo Zhan Hong
 * @see SpawnGround
 */
public class Graveyard extends SpawnGround {

  /**
   * Constructs a new Graveyard
   *
   * @param enemyFactory represents an Enemy Factory that will spawn a certain type of enemy in this
   *                     type of ground
   */
  public Graveyard(EnemyFactory enemyFactory) {
    super('n', enemyFactory);
  }

}
