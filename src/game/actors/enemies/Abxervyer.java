package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.weather.Weather;
import game.weather.WeatherController;
import game.weather.WeatherSubscriber;
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
public class Abxervyer extends Enemy implements WeatherController {

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
  /**
   * The current weather that the boss is affecting
   */
  private Weather currentWeather = Weather.SUNNY;
  /**
   * The turn count for the boss to switch the weather
   */
  private int turnCount = 3;
  /**
   * The list of subscribers that are subscribed to the weather of the boss
   */
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

  @Override
  protected int getDropRuneAmount() {
    return BASE_RUNES_DROP_AMOUNT;
  }

  /**
   * The Abxervyer will drop runes when killed. The location of the boss will be replaced by a gate
   *
   * @param map the map that the Abxervyer is on
   */
  @Override
  public void drop(GameMap map) {
    super.drop(map);
    Location location = map.locationOf(this);
    map.at(location.x(), location.y()).setGround(gate);
  }

  /**
   * A dead message will be return after the boss is dead The weather will be cleared after the boss
   * is dead
   */
  @Override
  public String unconscious(Actor actor, GameMap map) {
    String result = clearWeather();
    actor.addCapability(Status.ABXERVYER_KILLER);
    return super.unconscious(actor, map) + "\n" + this + " meets his end, " +
        "and the forest falls silent. Moonlight reveals twisted roots, casting eerie shadows.\n" +
        "The night chills, and leaves rustle ominously. " +
        "From the shadows, a ghostly voice emerges, whispering, " +
        "'In death, Abxervyer's gaze finds you, merging you with the haunted woods.\n" + result;

  }

  /**
   * The boss will switch the weather every 3 turns. It will notify the enemies that are affected by
   * weather
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in
   *                   conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    turnCounter();
    notifyWeather();
    return super.playTurn(actions, lastAction, map, display);
  }

  /**
   * Switch weather
   */
  private void switchWeather() {
    if (this.currentWeather == Weather.SUNNY) {
      this.currentWeather = Weather.RAINY;
      new Display().println(
          "The weather is now " + this.currentWeather.name().toLowerCase() + "...");
    } else {
      this.currentWeather = Weather.SUNNY;
      new Display().println(
          "The weather is now " + this.currentWeather.name().toLowerCase() + "...");
    }
  }

  /**
   * Count down the turn
   */
  private void turnCounter() {
    if (this.turnCount > 1) {
      this.turnCount--;
    } else {
      this.turnCount = 3;
      switchWeather();
    }
  }

  /**
   * For the enemy that are affected by the weather to subscribe to the weather controller.
   *
   * @param subscriber the subscriber to subscribe
   */
  @Override
  public void subscribe(WeatherSubscriber subscriber) {
    this.subscribers.add(subscriber);
  }

  /**
   * For the enemy that are affected by the weather to unsubscribe to the weather controller.
   *
   * @param subscriber the subscriber to unsubscribe
   */
  @Override
  public void unsubscribe(WeatherSubscriber subscriber) {
    this.subscribers.remove(subscriber);
  }

  /**
   * For the weather controller to notify the subscribers of the weather update.
   */
  @Override
  public void notifyWeather() {
    for (WeatherSubscriber subscriber : subscribers) {
      subscriber.update(this.currentWeather);
    }
  }

  /**
   * For the weather controller to clear the weather.
   *
   * @return the message to be displayed
   */
  @Override
  public String clearWeather() {
    for (WeatherSubscriber subscriber : subscribers) {
      subscriber.clear();
    }
    return "The weather is back to normal.";
  }
}
