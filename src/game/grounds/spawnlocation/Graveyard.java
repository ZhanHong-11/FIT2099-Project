package game.grounds.spawnlocation;

import game.spawners.EnemyFactory;

/**
 * A class that represents the graveyard, where it extends SpawnGround
 * Created by:
 *
 * @author Soo Zhan Hong
 * @see SpawnGround
 */
public class Graveyard extends SpawnGround {

  public Graveyard(EnemyFactory enemyFactory) {
    super('n', enemyFactory);
  }

}
