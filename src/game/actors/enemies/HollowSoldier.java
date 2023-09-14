package game.actors.enemies;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import java.util.Random;

/**
 * A subclass of Enemy that represents a hollow soldier. A hollow soldier is an enemy that are
 * immune to the void and can attack those that are hostile to enemy. It can drop healing vials or
 * refreshing flasks when killed with a certain probability.
 *
 * @see Enemy
 */
public class HollowSoldier extends Enemy {

  private Random random = new Random();

  /**
   * Constructs a new hollow soldier.
   */
  public HollowSoldier() {
    super("Hollow Soldier", '&', 200);
    this.addCapability(Ability.IMMUNE_TO_VOID);
  }

  /**
   * Returns an IntrinsicWeapon that represents the attack of the hollow soldier. The attack has a
   * damage of 50 and a hit rate of 50%.
   *
   * @return An IntrinsicWeapon that represents the attack
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(50, "whacked", 50);
  }

  /**
   * Drops an item on the game map when the hollow soldier is killed. The item can be either a
   * healing vial or a refreshing flask, with a probability of 20% and 30% respectively. The
   * probability of item dropping is independent to the others. The item is dropped at the location
   * of the hollow soldier.
   *
   * @param map The game map where the hollow soldier is located.
   */
  @Override
  public void drop(GameMap map) {
    int num = random.nextInt(10);
    if (num < 2) {
      Location location = map.locationOf(this);
      map.at(location.x(), location.y()).addItem(new HealingVial());
    }
    if (num < 3) {
      Location location = map.locationOf(this);
      map.at(location.x(), location.y()).addItem(new RefreshingFlask());
    }
  }

}
