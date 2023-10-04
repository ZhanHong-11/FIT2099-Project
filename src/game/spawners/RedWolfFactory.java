package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.RedWolf;
import java.util.Random;

/**
 * A factory that can spawn a red wolf.
 *
 * @see EnemyFactory
 */
public class RedWolfFactory implements EnemyFactory{
  /**
   * The base spawning rate of the red wolf
   */
  public static final int BASE_SPAWN_RATE = 30;
  /**
   * The spawning rate of the red wolf
   */
  private int spawningRate;
  private Random random = new Random();

  /**
   * Constructs a new red wolf factory with the default spawning rate.
   */
  public RedWolfFactory(){
    this.spawningRate = BASE_SPAWN_RATE;
  }

  /**
   * Constructs a new red wolf factory with the specified spawning rate. Useful for those
   * ability that can change the spawning rate of a spawning ground
   *
   * @param spawningRate The spawning rate of the red wolf
   */
  public RedWolfFactory(int spawningRate){
    this.spawningRate = spawningRate;
  }

  /**
   * Spawns a red wolf.
   *
   * @return The red wolf that is spawned
   */
  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < spawningRate){
      return new RedWolf();
    }
    return null;
  }
}
