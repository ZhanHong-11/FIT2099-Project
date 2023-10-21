package game.actors.enemies;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.WanderBehaviour;
import game.capabilities.Ability;
import game.dream.DreamCapable;
import game.weather.Weather;
import game.weather.WeatherController;
import game.weather.WeatherSubscriber;
import game.items.HealingVial;

import java.util.Random;

/**
 * A class that represents a Red Wolf. A Red Wolf is an enemy that can attack other actors who are
 * hostile to them and wander around the map. A Red Wolf has a follow ability which cause them to
 * follow the player when the player is nearby.
 *
 * @see Enemy
 * @see WeatherController
 */
public class RedWolf extends Enemy implements WeatherSubscriber {

  /**
   * The base intrinsic weapon damage of the Red Wolf
   */
  private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 15;
  /**
   * The base intrinsic hit rate of the Red Wolf
   */
  private static final int BASE_INTRINSIC_HIT_RATE = 80;
  /**
   * The base weapon verb of the Red Wolf
   */
  private static final String BASE_WEAPON_VERB = "bites";
  /**
   * The base healing vial drop rate of the Red Wolf
   */
  private static final int BASE_HEALING_VIAL_DROP_RATE = 10;
  /**
   * The base runes drop amount of the Red Wolf
   */
  private static final int BASE_RUNES_DROP_AMOUNT = 25;
  /**
   * The current intrinsic weapon damage of the Red Wolf
   */
  private int intrinsicWeaponDamage = BASE_INTRINSIC_WEAPON_DAMAGE;
  /**
   * The weather controller that the Red Wolf is subscribed to
   */
  private WeatherController controller;
  private Random random = new Random();

  /**
   * Constructs a new Red Wolf.
   *
   * @param controller   the Weather Controller object (abxervyer)
   * @param dreamCapable the Dream Capable Object (player)
   */
  public RedWolf(WeatherController controller, DreamCapable dreamCapable) {
    super("Red Wolf", 'r', 25, dreamCapable);
    setBehaviour(999, new WanderBehaviour());
    this.controller = controller;
    this.controller.subscribe(this);
    this.addCapability(Ability.FOLLOW);
  }

  /**
   * Returns an IntrinsicWeapon that represents the attack of the Red Wolf.
   *
   * @return An IntrinsicWeapon that represents the attack
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(this.intrinsicWeaponDamage, BASE_WEAPON_VERB,
        BASE_INTRINSIC_HIT_RATE);
  }

  /**
   * Returns the amount of runes that the Red Wolf will drop when killed by another actor.
   *
   * @return the amount of runes that the Red Wolf will drop when killed by another actor
   */
  @Override
  protected int getDropRuneAmount() {
    return BASE_RUNES_DROP_AMOUNT;
  }

  /**
   * Drops an item on the game map when the Red Wolf is killed. The item can be either a healing
   * vial with a probability of 10%. The item is dropped at the last location of the Red Wolf.
   *
   * @param location The location where the Red Wolf is located.
   */
  @Override
  public void drop(Location location) {
    super.drop(location);
    int num = random.nextInt(100);
    if (num < BASE_HEALING_VIAL_DROP_RATE) {
      location.addItem(new HealingVial());
    }
  }


  /**
   * Updates the intrinsic weapon damage of the Red Wolf when the weather is updated. If the weather
   * is sunny, the intrinsic weapon damage is tripled. If the weather is rainy, the intrinsic weapon
   * damage is set to the base value.
   *
   * @param currentWeather the current weather
   */
  @Override
  public void update(Weather currentWeather) {
    if (currentWeather == Weather.RAINY) {
      this.intrinsicWeaponDamage = BASE_INTRINSIC_WEAPON_DAMAGE;
      new Display().println("Red wolf is less aggressive");
    } else {
      this.intrinsicWeaponDamage = BASE_INTRINSIC_WEAPON_DAMAGE * 3;
      new Display().println("Red wolf is more aggressive");
    }
  }

  /**
   * Clears the intrinsic weapon damage of the Red Wolf when the weather is cleared.
   */
  @Override
  public void clear() {
    this.intrinsicWeaponDamage = BASE_INTRINSIC_WEAPON_DAMAGE;
  }
}
