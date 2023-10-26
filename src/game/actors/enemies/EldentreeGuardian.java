package game.actors.enemies;

import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.WanderBehaviour;
import game.capabilities.Ability;
import game.dream.DreamCapable;
import game.items.HealingVial;
import game.items.RefreshingFlask;

import java.util.Random;

/**
 * A class that represents an Eldentree Guardian. An Eldentree Guardian is an enemy that can attack
 * other actors who are hostile to them as well as wander around and follow a nearby player. The
 * Living Branch cannot follow nor wander. It can drop refreshing flask and healing vial when killed
 * by another actor
 *
 * @see Enemy
 */
public class EldentreeGuardian extends Enemy {

  /**
   * The base intrinsic weapon damage of the eldentree guardian
   */
  private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 50;

  /**
   * The base intrinsic weapon hit rate of the eldentree guardian
   */
  private static final int BASE_INTRINSIC_HIT_RATE = 80;

  /**
   * The base intrinsic weapon verb of the eldentree guardian
   */
  private static final String BASE_WEAPON_VERB = "pummeled";

  /**
   * The base healing vial drop rate of the eldentree guardian
   */
  private static final int BASE_HEALING_VIAL_DROP_RATE = 25;
  /**
   * The base refreshing flask drop rate of the eldentree guardian
   */
  private static final int BASE_REFRESHING_FLASK_DROP_RATE = 15;

  /**
   * The base runes drop amount of the eldentree guardian
   */
  private static final int BASE_RUNES_DROP_AMOUNT = 250;

  private Random random = new Random();

  /**
   * Constructs a new eldentree guardian
   *
   * @param dreamCapable the Dream Capable Object (player)
   */
  public EldentreeGuardian(DreamCapable dreamCapable) {
    super("Eldentree Guardian", 'e', 250, dreamCapable);
    setBehaviour(999, new WanderBehaviour());
    this.addCapability(Ability.FOLLOW);
    this.addCapability(Ability.IMMUNE_TO_VOID);
  }

  /**
   * Returns an IntrinsicWeapon that represents the attack of the eldentree guardian
   *
   * @return An IntrinsicWeapon that represents the attack
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB,
        BASE_INTRINSIC_HIT_RATE);

  }

  /**
   * Returns the amount of runes that the EldenTree Guardian will drop when it is killed
   *
   * @return the amount of runes that the EldenTree Guardian will drop when it is killed
   */
  @Override
  protected int getDropRuneAmount() {
    return BASE_RUNES_DROP_AMOUNT;
  }

  /**
   * Drops an item on the game map when the EldenTree Guardian is killed. The item can be either a
   * healing vial or a refreshing flask, with a probability of 25% and 15% respectively. The
   * probability of item dropping is independent to the others. The item is dropped at the location
   * of the EldenTree Guardian.
   *
   * @param location The location where the EldenTree Guardian is located.
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
