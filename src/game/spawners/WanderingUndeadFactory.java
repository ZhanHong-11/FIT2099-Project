package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.WanderingUndead;
import java.util.Random;

/**
 * A factory that can spawn a wandering undead.
 *
 * @see EnemyFactory
 */
public class WanderingUndeadFactory implements EnemyFactory {

  /**
   * The base spawning rate of the wandering undead
   */
  public static final int BASE_SPAWN_RATE = 25;
  /**
   * The spawning rate of the wandering undead
   */
  private int spawningRate;
  private Random random = new Random();

  /**
   * Constructs a new wandering undead factory with the default spawning rate.
   */
  public WanderingUndeadFactory() {
    this.spawningRate = BASE_SPAWN_RATE;
  }

  /**
   * Constructs a new wandering undead factory with the specified spawning rate. Useful for those
   * ability that can change the spawning rate of a spawning ground
   *
   * @param spawningRate The spawning rate of the wandering undead
   */
  public WanderingUndeadFactory(int spawningRate) {
    this.spawningRate = spawningRate;
  }

  /**
   * Spawns a wandering undead.
   *
   * @return The wandering undead that is spawned
   */
  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < spawningRate) {
      return new WanderingUndead();
    }
    return null;
  }
}
