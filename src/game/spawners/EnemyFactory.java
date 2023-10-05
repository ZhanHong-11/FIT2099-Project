package game.spawners;

import game.actors.enemies.Enemy;

/**
 * An abstract parent that represents a factory that can spawn an enemy.
 *
 * @see Spawnable
 */
public abstract class EnemyFactory implements Spawnable {

  /**
   * The spawning rate of the enemy
   */
  private int spawningRate;

  /**
   * Constructs a new enemy factory with the given spawning rate.
   *
   * @param spawningRate The spawning rate of the enemy
   */
  public EnemyFactory(int spawningRate) {
    this.spawningRate = spawningRate;
  }

  /**
   * Gets the spawning rate of the enemy.
   *
   * @return The spawning rate of the enemy
   */
  protected int getSpawningRate() {
    return spawningRate;
  }

  /**
   * Sets the spawning rate of the enemy.
   *
   * @param spawningRate The spawning rate of the enemy
   */
  protected void setSpawningRate(int spawningRate) {
    this.spawningRate = spawningRate;
  }

  /**
   * Spawns an enemy.
   *
   * @return The enemy that is spawned
   */
  public abstract Enemy spawnEnemy();
}
