package game.actors.enemies;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.weather.Weather;
import game.weather.WeatherController;
import game.weather.WeatherSubscriber;
import game.items.HealingVial;
import game.items.Rune;

import java.util.Random;

/**
 * A class that represents a Red Wolf. A Red Wolf is an enemy that can attack other actors who are
 * hostile to them and wander around the map. A Red Wolf has a follow ability which cause them to
 * follow the player when the player is nearby.
 * @see Enemy
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
   */
  public RedWolf(WeatherController controller) {
    super("Red Wolf", 'r', 25);
    this.controller = controller;
    this.controller.subscribe(this);
    this.addCapability(Ability.FOLLOW);
  }

  /**
   * Returns an IntrinsicWeapon that represents the attack of the Red Wolf. If the Red Wolf has the
   * sunny buff, the damage of the attack is tripled.
   *
   * @return An IntrinsicWeapon that represents the attack
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(this.intrinsicWeaponDamage, BASE_WEAPON_VERB,
        BASE_INTRINSIC_HIT_RATE);
  }

  @Override
  protected int getDropRuneAmount() {
    return BASE_RUNES_DROP_AMOUNT;
  }

  /**
   * Drops an item on the game map when the Red Wolf is killed. The item can be either a healing
   * vial or a refreshing flask, with a probability of 10% and 20% respectively. The probability of
   * item dropping is independent to the others. The item is dropped at the location of the Red
   * Wolf. Red Wolf also drop runes when killed by another actor.
   *
   * @param map The game map where the Red Wolf is located.
   */
  @Override
  public void drop(GameMap map) {
    super.drop(map);
    int num = random.nextInt(100);
    Location location = map.locationOf(this);
    if (num < BASE_HEALING_VIAL_DROP_RATE) {
      map.at(location.x(), location.y()).addItem(new HealingVial());
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
