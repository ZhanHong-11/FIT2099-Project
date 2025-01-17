package game.actors.enemies;

import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.WanderBehaviour;
import game.dream.DreamCapable;
import game.items.HealingVial;
import game.items.Key;

import java.util.Random;

/**
 * A subclass of Enemy that represents a wandering undead. A wandering undead is an enemy that can
 * attack those that are hostile to enemy. It can drop healing vials or refreshing flasks when
 * killed with a certain probability.
 *
 * @see Enemy
 */
public class WanderingUndead extends Enemy {

  /**
   * The base intrinsic weapon damage of the wandering undead
   */
  private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 30;
  /**
   * The base intrinsic hit rate of the wandering undead
   */
  private static final int BASE_INTRINSIC_HIT_RATE = 50;
  /**
   * The base weapon verb of the wandering undead
   */
  private static final String BASE_WEAPON_VERB = "smacked";
  /**
   * The base key drop rate of the wandering undead
   */
  private static final int BASE_KEY_DROP_RATE = 25;
  /**
   * The base healing vial drop rate of the wandering undead
   */
  private static final int BASE_HEALING_VIAL_DROP_RATE = 20;
  /**
   * The base runes drop amount of the wandering undead
   */
  private static final int BASE_RUNES_DROP_AMOUNT = 50;
  private Random random = new Random();

  /**
   * Constructs a new wandering undead.
   *
   * @param dreamCapable the Dream Capable Object (player)
   */
  public WanderingUndead(DreamCapable dreamCapable) {
    super("Wandering Undead", 't', 100, dreamCapable);
    setBehaviour(999, new WanderBehaviour());
  }

  /**
   * Returns an IntrinsicWeapon that represents the attack of the wandering undead.
   *
   * @return An IntrinsicWeapon that represents the attack
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB,
        BASE_INTRINSIC_HIT_RATE);
  }

  /**
   * Returns the amount of runes that the Wandering Undead will drop when killed by another actor.
   *
   * @return the amount of runes that the Wandering Undead will drop when killed by another actor
   */
  @Override
  protected int getDropRuneAmount() {
    return BASE_RUNES_DROP_AMOUNT;
  }

  /**
   * Drops an item on the game map when the wandering undead is killed. The item can be either a
   * healing vial or a key, with a probability of 20% and 10% respectively. The probability of item
   * dropping is independent to the others. The item is dropped at the location of the wandering
   * undead.
   *
   * @param location The location where the wandering undead is located.
   */
  @Override
  public void drop(Location location) {
    super.drop(location);
    int num = random.nextInt(100);
    if (num < BASE_KEY_DROP_RATE) {
      location.addItem(new Key());
    }
    if (num < BASE_HEALING_VIAL_DROP_RATE) {
      location.addItem(new HealingVial());
    }
  }
}
