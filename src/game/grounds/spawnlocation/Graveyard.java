package game.grounds.spawnlocation;

import game.spawners.EnemyFactory;

/**
 * A class that represents the graveyard.
 * Created by:
 *
 * @author Soo Zhan Hong
 */
public class Graveyard extends SpawnGround {

  public Graveyard(EnemyFactory enemyFactory) {
    super('n', enemyFactory);
  }

}
