package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.ForestKeeper;
import java.util.Random;

/**
 * A factory that can spawn a forest keeper.
 *
 * @see EnemyFactory
 */
public class ForestKeeperFactory implements EnemyFactory {

  /**
   * The base spawning rate of the forest keeper
   */
  public static final int BASE_SPAWN_RATE = 15;
  /**
   * The spawning rate of the forest keeper
   */
  private int spawningRate;
  private Random random = new Random();

  /**
   * Constructs a new forest keeper factory with the default spawning rate.
   */
  public ForestKeeperFactory() {
    this.spawningRate = BASE_SPAWN_RATE;
  }

  /**
   * Constructs a new forest keeper factory with the specified spawning rate. Useful for those
   * ability that can change the spawning rate of a spawning ground
   *
   * @param spawningRate The spawning rate of the forest keeper
   */
  public ForestKeeperFactory(int spawningRate) {
    this.spawningRate = spawningRate;
  }

  /**
   * Spawns a forest keeper.
   *
   * @return The forest keeper that is spawned
   */
  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < spawningRate) {
      return new ForestKeeper();
    }
    return null;
  }
}
