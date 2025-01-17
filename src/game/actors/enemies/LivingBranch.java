package game.actors.enemies;

import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.dream.DreamCapable;
import game.items.Bloodberry;

import java.util.Random;

/**
 * A class that represents a Living Branch. A Living Branch is an enemy that can attack other actors
 * who are hostile to them. The Living Branch cannot follow nor wander. It can drop a bloodberry
 * when killed by another actor. This enemy is immune to the void
 *
 * @see Enemy
 */
public class LivingBranch extends Enemy {

  /**
   * The base intrinsic weapon damage of the living branch
   */
  private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 250;

  /**
   * The base intrinsic weapon hit rate of the living branch
   */
  private static final int BASE_INTRINSIC_HIT_RATE = 90;

  /**
   * The base intrinsic weapon verb of the living branch
   */
  private static final String BASE_WEAPON_VERB = "struck";

  /**
   * The base healing vial drop rate of the living branch
   */
  private static final int BASE_BLOODBERRY_DROP_CHANCE = 50;

  /**
   * The base runes drop amount of the living branch
   */
  private static final int BASE_RUNES_DROP_AMOUNT = 500;

  private Random random = new Random();


  /**
   * Constructs a new living branch
   *
   * @param dreamCapable the Dream Capable Object (player)
   */
  public LivingBranch(DreamCapable dreamCapable) {
    super("Living Branch", '?', 75, dreamCapable);
    this.addCapability(Ability.IMMUNE_TO_VOID);
  }

  /**
   * Returns an IntrinsicWeapon that represents the attack of the Living Branch.
   *
   * @return An IntrinsicWeapon that represents the attack
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB,
        BASE_INTRINSIC_HIT_RATE);

  }

  /**
   * Returns the amount of runes that the Living Branch will drop when killed by another actor.
   *
   * @return the amount of runes that the Living Branch will drop when killed by another actor
   */
  @Override
  protected int getDropRuneAmount() {
    return BASE_RUNES_DROP_AMOUNT;
  }

  /**
   * Drops an item on the game map when the Living Branch is killed. The item dropped is Bloodberry
   * with a probability of 50%. The item is dropped at the location of the Living Branch.
   *
   * @param location The location where the Living Branch is located.
   */
  @Override
  public void drop(Location location) {
    super.drop(location);
    int num = random.nextInt(100);
    if (num < BASE_BLOODBERRY_DROP_CHANCE) {
      location.addItem(new Bloodberry());
    }
  }
}
