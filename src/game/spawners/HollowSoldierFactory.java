package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.HollowSoldier;
import java.util.Random;

/**
 * A factory that can spawn a hollow soldier.
 *
 * @see EnemyFactory
 */
public class HollowSoldierFactory implements EnemyFactory {

  /**
   * The base spawning rate of the hollow soldier
   */
  public static final int BASE_SPAWN_RATE = 10;
  /**
   * The spawning rate of the hollow soldier
   */
  private int spawningRate;
  private Random random = new Random();

  /**
   * Constructs a new hollow soldier factory with the default spawning rate.
   */
  public HollowSoldierFactory() {
    this.spawningRate = BASE_SPAWN_RATE;
  }

  /**
   * Constructs a new hollow soldier factory with the specified spawning rate. Useful for those
   * ability that can change the spawning rate of a spawning ground
   *
   * @param spawningRate The spawning rate of the hollow soldier
   */
  public HollowSoldierFactory(int spawningRate) {
    this.spawningRate = spawningRate;
  }

  /**
   * Spawns a hollow soldier.
   *
   * @return The hollow soldier that is spawned
   */
  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < spawningRate) {
      return new HollowSoldier();
    }
    return null;
  }
}
