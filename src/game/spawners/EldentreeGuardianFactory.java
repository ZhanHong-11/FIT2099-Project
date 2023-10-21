package game.spawners;

import game.actors.enemies.EldentreeGuardian;
import game.actors.enemies.Enemy;
import game.dream.DreamCapable;

import java.util.Random;

/**
 * A subclass of EnemyFactory that represents a factory that spawns eldentree guardians.
 */
public class EldentreeGuardianFactory extends EnemyFactory {

  /**
   * The base spawning rate of the eldentree guardian
   */
  public static final int BASE_SPAWN_RATE = 20;
  private Random random = new Random();

  /**
   * Constructs a new eldentree guardian factory with the default spawning rate.
   *
   * @param dreamCapable the Dream Capable Object (player)
   */
  public EldentreeGuardianFactory(DreamCapable dreamCapable) {
    super(BASE_SPAWN_RATE, dreamCapable);

  }

  /**
   * Spawns a eldentree guardian.
   *
   * @return The eldentree guardian that is spawned
   */
  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < getSpawningRate()) {
      return new EldentreeGuardian(getDreamCapable());
    }
    return null;
  }
}
