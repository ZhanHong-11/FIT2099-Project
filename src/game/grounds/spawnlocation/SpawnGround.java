package game.grounds.spawnlocation;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.spawners.Spawnable;
import java.util.List;

/**
 * an abstract class which represents a SpawnGround, it extends from the Ground Class
 *
 * @author Soo Zhan Hong
 * @see Ground
 */
public abstract class SpawnGround extends Ground {

  /**
   * an Enemy Factory that will spawn a certain type of enemy in this type of ground
   */
  private Spawnable spawnable;

  /**
   * Constructor.
   *
   * @param displayChar  character to display for this type of terrain
   * @param spawnable represents an Enemy Factory that will spawn a certain type of enemy in this
   *                     spawning ground
   */
  public SpawnGround(char displayChar, Spawnable spawnable) {
    super(displayChar);
    this.spawnable = spawnable;
  }

  /**
   * Set the enemy factory for the spawn ground Useful for setting different spawning rate for the
   * enemy factory
   *
   * @param spawnable The enemy factory
   */
  public void setEnemyFactory(Spawnable spawnable) {
    this.spawnable = spawnable;
  }

  /**
   * For every turn, there is a probability to spawn a new enemy If the ground is surrounded with
   * enemies, and there is also an enemy standing on the ground, the ground will not spawn another
   * enemy unless there is an empty space at the surrounding.
   *
   * @param location The location of the ground
   */
  @Override
  public void tick(Location location) {
    Enemy enemy = spawnable.spawnEnemy();
    if (enemy != null) {
      Location spawnLocation = validLocation(location);
      if (spawnLocation != null) {
        spawnLocation.addActor(enemy);
      }
    }
  }

  /**
   * Find a valid location at the surrounding of the ground. Return a location if there is an empty
   * space, return null otherwise
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
