package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.WanderingUndead;
import java.util.Random;

/**
 * A factory that can spawn a wandering undead.
 *
 * @see EnemyFactory
 */
public class WanderingUndeadFactory extends EnemyFactory {

  /**
   * The base spawning rate of the wandering undead
   */
  public static final int BASE_SPAWN_RATE = 25;
  private Random random = new Random();

  /**
   * Constructs a new wandering undead factory with the default spawning rate.
   */
  public WanderingUndeadFactory() {
    super(BASE_SPAWN_RATE);
  }

  /**
   * Spawns a wandering undead.
   *
   * @return The wandering undead that is spawned
   */
  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < getSpawningRate()) {
      return new WanderingUndead();
    }
    return null;
  }
}
