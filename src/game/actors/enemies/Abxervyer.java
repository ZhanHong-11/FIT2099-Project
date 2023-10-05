package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.gamemaps.weather.Weather;
import game.gamemaps.weather.WeatherPublisher;
import game.gamemaps.weather.WeatherSubscriber;
import game.grounds.LockedGate;
import game.items.Rune;

import java.util.ArrayList;

/**
 * A class that represents the Abxervyer. The Abxervyer is a boss that can attack other actors who
 * are hostile to them and wander around the map. The Abxervyer has a follow ability which cause
 * them to follow the player when the player is nearby. The Abxervyer can drop items when killed.
 * This boss is immune to void damage and has the ability to control the weather of the map it
 * stayed.
 *
 * @see Enemy
 */
public class Abxervyer extends Enemy implements WeatherPublisher {

  /**
   * The base intrinsic weapon damage of the Abxervyer
   */
  private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 80;
  /**
   * The base intrinsic hit rate of the Abxervyer
   */
  private static final int BASE_INTRINSIC_HIT_RATE = 25;
  /**
   * The base weapon verb of the Abxervyer
   */
  private static final String BASE_WEAPON_VERB = "batters";
  /**
   * The base runes drop amount of the Abxervyer
   */
  private static final int BASE_RUNES_DROP_AMOUNT = 5000;
  /**
   * The gate that will appear on the map after the Abxervyer is killed
   */
  private LockedGate gate;
  private Weather currentWeather = Weather.SUNNY;

  private int turnCount = 3;

  private ArrayList<WeatherSubscriber> subscribers = new ArrayList<>();

  /**
   * Constructs a new Abxervyer.
   *
   * @param gate the gate that will appear on the map after the Abxervyer is killed
   */
  public Abxervyer(LockedGate gate) {
    super("Abxervyer, the Forest Watcher", 'Y', 2000);
    this.gate = gate;
    this.addCapability(Ability.IMMUNE_TO_VOID);
    this.addCapability(Ability.FOLLOW);
    this.addCapability(Ability.CONTROL_WEATHER);

  }

  /**
   * Returns an IntrinsicWeapon that represents the attack of the Abxervyer.
   *
   * @return An IntrinsicWeapon that represents the attack
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB,
        BASE_INTRINSIC_HIT_RATE);
  }

  /**
   * The Abxervyer will drop runes when killed. The location of the boss will be replaced by a gate
   *
   * @param map the map that the Abxervyer is on
   */
  @Override
  public void drop(GameMap map) {
    Location location = map.locationOf(this);
    map.at(location.x(), location.y()).addItem(new Rune(BASE_RUNES_DROP_AMOUNT));
    map.at(location.x(), location.y()).setGround(gate);
  }

  /**
   * A dead message will be return after the boss is dead
   */
  @Override
  public String unconscious(Actor actor, GameMap map) {
    return super.unconscious(actor, map) + "\n" + this + " is dead!!";

  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    turnCounter();
    display.println("Weather is now " + this.currentWeather);


    return super.playTurn(actions, lastAction, map, display);
  }

  private void switchWeather() {
    if (this.currentWeather == Weather.SUNNY) {
      this.currentWeather = Weather.RAINY;
    } else {
      this.currentWeather = Weather.SUNNY;
    }
    notifyWeather();
  }

  private void turnCounter() {
    if (this.turnCount > 1) {
      this.turnCount--;
    }
    else {
      this.turnCount = 3;
      switchWeather();
    }
  }


  @Override
  public void subscribe(Weather weather, WeatherSubscriber subscriber) {
    this.subscribers.add(subscriber);

  }

  @Override
  public void unsubscribe() {

  }

  @Override
  public void notifyWeather() {
    for (WeatherSubscriber subscriber : subscribers) {
      subscriber.update(this.currentWeather);
    }
  }
}
