package game.actors.enemies;

import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.WanderBehaviour;
import game.dream.DreamCapable;
import game.items.HealingVial;
import game.items.RefreshingFlask;

import java.util.Random;

/**
 * A subclass of Enemy that represents a hollow soldier. A hollow soldier is an enemy that can
 * wander and attack those that are hostile to enemy. It can drop healing vials or refreshing flasks
 * when killed with a certain probability.
 *
 * @see Enemy
 */
public class HollowSoldier extends Enemy {

  /**
   * The base intrinsic weapon damage of the hollow soldier
   */
  private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 50;
  /**
   * The base intrinsic hit rate of the hollow soldier
   */
  private static final int BASE_INTRINSIC_HIT_RATE = 50;
  /**
   * The base weapon verb of the hollow soldier
   */
  private static final String BASE_WEAPON_VERB = "whacked";
  /**
   * The base healing vial drop rate of the hollow soldier
   */
  private static final int BASE_HEALING_VIAL_DROP_RATE = 20;
  /**
   * The base refreshing flask drop rate of the hollow soldier
   */
  private static final int BASE_REFRESHING_FLASK_DROP_RATE = 30;
  /**
   * The base runes drop amount of the hollow soldier
   */
  private static final int BASE_RUNES_DROP_AMOUNT = 100;
  private Random random = new Random();

  /**
   * Constructs a new hollow soldier.
   * @param dreamCapable the Dream Capable Object (player)
   */
  public HollowSoldier(DreamCapable dreamCapable) {
    super("Hollow Soldier", '&', 200, dreamCapable);
    setBehaviour(999, new WanderBehaviour());
  }

  /**
   * Returns an IntrinsicWeapon that represents the attack of the hollow soldier. The attack has a
   * damage of 50 and a hit rate of 50%.
   *
   * @return An IntrinsicWeapon that represents the attack
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB,
        BASE_INTRINSIC_HIT_RATE);
  }

  @Override
  protected int getDropRuneAmount() {
    return BASE_RUNES_DROP_AMOUNT;
  }

  /**
   * Drops an item on the game map when the hollow soldier is killed. The item can be either a
   * healing vial or a refreshing flask, with a probability of 20% and 30% respectively. The
   * probability of item dropping is independent to the others. The item is dropped at the location
   * of the hollow soldier. Will also drop Runes when killed by another actor
   *
   * @param location The location where the hollow soldier is located.
   */
  @Override
  public void drop(Location location) {
    super.drop(location);
    int num = random.nextInt(100);
    if (num < BASE_HEALING_VIAL_DROP_RATE) {
      location.addItem(new HealingVial());
    }
    if (num < BASE_REFRESHING_FLASK_DROP_RATE) {
      location.addItem(new RefreshingFlask());
    }
  }


}
