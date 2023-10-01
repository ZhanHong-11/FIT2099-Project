package game.actors.enemies;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.items.HealingVial;
import game.items.Key;
import game.items.Rune;

import java.util.Random;

/**
 * A subclass of Enemy that represents a wandering undead. A wandering undead is an enemy that are
 * immune to the void and can attack those that are hostile to enemy. It can drop healing vials or
 * refreshing flasks when killed with a certain probability.
 *
 * @see Enemy
 */
public class WanderingUndead extends Enemy {

  private Random random = new Random();
  public static final int BASE_KEY_DROP_RATE = 25;
  public static final int BASE_HEALING_VIAL_DROP_RATE = 20;
  public static final int BASE_RUNES_DROP_AMOUNT = 50;
  /**
   * Constructs a new wandering undead.
   */
  public WanderingUndead() {
    super("Wandering Undead", 't', 100);
    this.addCapability(Ability.IMMUNE_TO_VOID);
  }

  /**
   * Returns an IntrinsicWeapon that represents the attack of the wandering undead. The attack
   * has a damage of 30 and a hit rate of 50%.
   *
   * @return An IntrinsicWeapon that represents the attack
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(30, "smacked", 50);
  }

  /**
   * Drops an item on the game map when the wandering undead is killed. The item can be either a
   * healing vial or a key, with a probability of 20% and 10% respectively. The probability of item
   * dropping is independent to the others. The item is dropped at the location of the wandering
   * undead.
   *
   * @param map The game map where the wandering undead is located.
   */
  @Override
  public void drop(GameMap map) {
    int num = random.nextInt(100);
    Location location = map.locationOf(this);
    map.at(location.x(), location.y()).addItem(new Rune(BASE_RUNES_DROP_AMOUNT));
    if (num < BASE_KEY_DROP_RATE) {
      map.at(location.x(), location.y()).addItem(new Key());
    }
    if (num < BASE_HEALING_VIAL_DROP_RATE) {
      map.at(location.x(), location.y()).addItem(new HealingVial());
    }
  }

}
