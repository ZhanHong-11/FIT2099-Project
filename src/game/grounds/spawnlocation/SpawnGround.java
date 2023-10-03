package game.grounds.spawnlocation;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.spawners.EnemyFactory;
import java.util.List;

/**
 * an abstract class which represents a SpawnGround, it extends to the Ground Class
 * @author Soo Zhan Hong
 * @see Ground
 */
public abstract class SpawnGround extends Ground {
  private EnemyFactory enemyFactory;

  /**
   * Constructor.
   *
   * @param displayChar character to display for this type of terrain
   */
  public SpawnGround(char displayChar, EnemyFactory enemyFactory) {
    super(displayChar);
    this.enemyFactory = enemyFactory;
  }

  /**
   * a Method to set the enemy factory of the spawn ground
   * @param enemyFactory
   */
  public void setEnemyFactory(EnemyFactory enemyFactory){
    this.enemyFactory = enemyFactory;
  }

  /**
   * For every turn, there is a probability to spawn a new enemy If the ground is surrounded with enemies, and
   * there is also an enemy standing on the ground, the ground will not spawn another enemy
   * unless there is an empty space at the surrounding.
   *
   * @param location The location of the ground
   */
  @Override
  public void tick(Location location) {
    Enemy enemy = enemyFactory.spawnEnemy();
    if (enemy != null){
      Location spawnLocation = validLocation(location);
      if (spawnLocation != null){
        spawnLocation.addActor(enemy);
      }
    }
  }

  /**
   * Find a valid location at the surrounding of the ground. Return a location if there is an
   * empty space, return null otherwise
   *
   * @param location The location of the ground
   * @return a valid location if there is an empty space at the surrounding of the ground, return
   * null otherwise
   */
  private Location validLocation(Location location) {
    if (!location.containsAnActor()) {
      return location;
    } else {
      List<Exit> exits = location.getExits();
      for (Exit exit : exits) {
        if (!exit.getDestination().containsAnActor()) {
          return exit.getDestination();
        }
      }
      return null;
    }
  }
}
