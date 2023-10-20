package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.HollowSoldier;
import game.dream.DreamCapable;

import java.util.Random;

/**
 * A factory that can spawn a hollow soldier.
 *
 * @see EnemyFactory
 */
public class HollowSoldierFactory extends EnemyFactory {

  /**
   * The base spawning rate of the hollow soldier
   */
  public static final int BASE_SPAWN_RATE = 10;
  private Random random = new Random();

  /**
   * Constructs a new hollow soldier factory with the default spawning rate.
   * @param dreamCapable the Dream Capable Object (player)
   */
  public HollowSoldierFactory(DreamCapable dreamCapable) {
    super(BASE_SPAWN_RATE, dreamCapable);
  }

  /**
   * Spawns a hollow soldier.
   *
   * @return The hollow soldier that is spawned
   */
  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < getSpawningRate()) {
      return new HollowSoldier(getDreamCapable());
    }
    return null;
  }
}
