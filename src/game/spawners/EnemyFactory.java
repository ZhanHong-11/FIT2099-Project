package game.spawners;

import game.actors.enemies.Enemy;
import game.dream.DreamCapable;

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
   * The dream capable object (player)
   */
  private DreamCapable dreamCapable;

  /**
   * Constructs a new enemy factory with the given spawning rate.
   *
   * @param spawningRate The spawning rate of the enemy
   * @param dreamCapable the Dream Capable Object (player)
   */
  public EnemyFactory(int spawningRate, DreamCapable dreamCapable) {
    this.spawningRate = spawningRate;
    this.dreamCapable = dreamCapable;
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

  /**
   * Getter method that
   * Retrieves the associated DreamCapable instance for this item.
   * @return dreamCapable
   */
  protected DreamCapable getDreamCapable() {
    return this.dreamCapable;
  }

}
