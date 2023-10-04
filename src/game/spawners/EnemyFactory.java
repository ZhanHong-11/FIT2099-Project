package game.spawners;

import game.actors.enemies.Enemy;

/**
 * An interface that represents a factory that can spawn an enemy.
 */
public interface EnemyFactory {

  /**
   * Spawns an enemy.
   *
   * @return The enemy that is spawned
   */
  Enemy spawnEnemy();
}
