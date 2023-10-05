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
 * A class that represents a Forest Keeper. A Forest Keeper is an enemy that can attack other actors
 * who are hostile to them and wander around the map. A Forest Keeper has a follow ability which
 * cause them to follow the player when the player is nearby. A Forest Keeper can drop items when
 * killed.
 *
 * @see Enemy
 */
public class ForestKeeper extends Enemy implements WeatherSubscriber {

  /**
   * The base intrinsic weapon damage of the Forest Keeper
   */
  private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 25;
  /**
   * The base intrinsic hit rate of the Forest Keeper
   */
  private static final int BASE_INTRINSIC_HIT_RATE = 75;
  /**
   * The base weapon verb of the Forest Keeper
   */
  private static final String BASE_WEAPON_VERB = "hits";
  /**
   * The base healing vial drop rate of the Forest Keeper
   */
  private static final int BASE_HEALING_VIAL_DROP_RATE = 20;
  /**
   * The base runes drop amount of the Forest Keeper
   */
  private static final int BASE_RUNES_DROP_AMOUNT = 50;
  /**
   * The weather controller
   */
  private WeatherController controller;
  private Random random = new Random();

  /**
   * Constructs a new Forest Keeper.
   */
  public ForestKeeper(WeatherController controller) {
    super("Forest Keeper", '8', 125);
    this.controller = controller;
    this.controller.subscribe(this);
    this.addCapability(Ability.FOLLOW);
  }

  /**
   * Returns an IntrinsicWeapon that represents the attack of the Forest Keeper.
   *
   * @return An IntrinsicWeapon that represents the attack
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB,
        BASE_INTRINSIC_HIT_RATE);
  }

  /**
   * Drops an item on the game map when the Forest Keeper is killed. The item can be either a
   * healing vial or a refreshing flask, with a probability of 20% and 30% respectively. The
   * probability of item dropping is independent to the others. The item is dropped at the location
   * of the Forest Keeper. It will also drop Runes when killed by another actor
   *
   * @param map The game map where the Forest Keeper is located.
   */
  @Override
  public void drop(GameMap map) {
    int num = random.nextInt(100);
    Location location = map.locationOf(this);
    map.at(location.x(), location.y()).addItem(new Rune(BASE_RUNES_DROP_AMOUNT));
    if (num < BASE_HEALING_VIAL_DROP_RATE) {
      map.at(location.x(), location.y()).addItem(new HealingVial());
    }
  }

  /**
   * Updates the Forest Keeper when the weather changes. If the weather is sunny, the Forest Keeper
   * will heal by 10 points.
   *
   * @param currentWeather the current weather
   */
  @Override
  public void update(Weather currentWeather) {
    int healthRecoveryInRain = 10;
    if (currentWeather == Weather.RAINY) {
      heal(healthRecoveryInRain);
      new Display().println("Forest Keeper heals by " + healthRecoveryInRain + " points");
    }
  }

  /**
   * Forest keeper only heal when it is raining. Clearing the weather does not affect anything but
   * not continue to heal.
   */
  @Override
  public void clear() {
  }
}
