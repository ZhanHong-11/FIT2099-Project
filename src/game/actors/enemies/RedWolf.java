package game.actors.enemies;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.gamemaps.weather.Weather;
import game.gamemaps.weather.WeatherPublisher;
import game.gamemaps.weather.WeatherSubscriber;
import game.items.HealingVial;
import game.items.Rune;

import java.util.Random;

/**
 * A class that represents a Red Wolf. A Red Wolf is an enemy that can attack other actors who are
 * hostile to them and wander around the map. A Red Wolf has a follow ability which cause them to
 * follow the player when the player is nearby. A Forest Keeper can drop items when killed.
 *
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
  public static final int BASE_HEALING_VIAL_DROP_RATE = 10;
  /**
   * The base runes drop amount of the Red Wolf
   */
  public static final int BASE_RUNES_DROP_AMOUNT = 25;
  private Random random = new Random();

  private WeatherPublisher publisher;

  /**
   * Constructs a new Red Wolf.
   */
  public RedWolf(WeatherPublisher publisher) {
    super("Red Wolf", 'r', 25);
    this.addCapability(Ability.FOLLOW);
    this.publisher = publisher;
    this.publisher.subscribe(Weather.SUNNY, this);
    this.publisher.subscribe(Weather.RAINY, this);
  }

  /**
   * Returns an IntrinsicWeapon that represents the attack of the Red Wolf. If the Red Wolf has the
   * sunny buff, the damage of the attack is tripled.
   *
   * @return An IntrinsicWeapon that represents the attack
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    if (!this.hasCapability(Status.SUNNY_BUFF)) {
      return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB,
          BASE_INTRINSIC_HIT_RATE);
    } else {
      return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE * 3, BASE_WEAPON_VERB,
          BASE_INTRINSIC_HIT_RATE);
    }
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
    int num = random.nextInt(100);
    Location location = map.locationOf(this);
    map.at(location.x(), location.y()).addItem(new Rune(BASE_RUNES_DROP_AMOUNT));
    if (num < BASE_HEALING_VIAL_DROP_RATE) {
      map.at(location.x(), location.y()).addItem(new HealingVial());
    }
  }

  @Override
  public void update(Weather currentWeather) {
    if (currentWeather == Weather.RAINY) {
      this.updateDamageMultiplier(BASE_INTRINSIC_WEAPON_DAMAGE);
      System.out.println("Red wolf become less aggressive");
    } else {
      this.updateDamageMultiplier(BASE_INTRINSIC_WEAPON_DAMAGE * 3);
      System.out.println("Red wolf become aggressive");
    }
  }
}
