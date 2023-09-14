package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.actors.enemies.HollowSoldier;
import game.actors.enemies.WanderingUndead;
import game.gamemaps.AbandonedVillage;
import game.gamemaps.BurialGround;
import java.util.List;
import java.util.Random;

/**
 * A class that represents the graveyard.
 * Created by:
 *
 * @author Soo Zhan Hong
 */
public class Graveyard extends Ground {

  private final Random random = new Random();

  public Graveyard() {
    super('n');
  }

  /**
   * For every turn, there is a probability to spawn a new enemy, either a wandering undead or a
   * hollow soldier depending on the current map. If the graveyard is surrounded with enemies, and
   * there is also an enemy standing on the ground, the graveyard will not spawn another enemy
   * unless there is a empty space at the surrounding.
   *
   * @param location The location of the graveyard
   */
  @Override
  public void tick(Location location) {
    int num = random.nextInt(4);
    if (num == 0) {
      Location spawnLocation = validLocation(location);
      if (spawnLocation != null) {
        spawnLocation.addActor(validEnemy(location));
      }
    }
  }

  /**
   * Find a valid location at the surrounding of this graveyard. Return a location if there is an
   * empty space, return null otherwise
   *
   * @param location The location of the graveyard
   * @return a valid location if there is an empty space at the surrounding of the graveyard, return
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

  /**
   * Return a valid enemy depends on the current game map. Wandering undead for the abandoned
   * village while hollow soldier for the burial ground.
   *
   * @param location The location of the graveyard
   * @return a type of enemy depending on the current map
   */
  private Enemy validEnemy(Location location) {
    if (location.map() instanceof AbandonedVillage) {
      return new WanderingUndead();
    } else if (location.map() instanceof BurialGround) {
      return new HollowSoldier();

    }
    return null;
  }
}
