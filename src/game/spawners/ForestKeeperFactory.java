package game.spawners;

import edu.monash.fit2099.engine.displays.Display;
import game.actors.enemies.Enemy;
import game.actors.enemies.ForestKeeper;
import game.dream.DreamCapable;
import game.weather.Weather;
import game.weather.WeatherController;
import game.weather.WeatherSubscriber;

import java.util.Random;

/**
 * A factory that can spawn a forest keeper.
 *
 * @see EnemyFactory
 * @see WeatherSubscriber
 */
public class ForestKeeperFactory extends EnemyFactory implements WeatherSubscriber {

  /**
   * The base spawning rate of the forest keeper
   */
  public static final int BASE_SPAWN_RATE = 15;
  /**
   * The weather controller that this factory subscribes to
   */
  private WeatherController controller;
  private Random random = new Random();

  /**
   * Constructs a new forest keeper factory with the default spawning rate.
   *
   * @param controller   The weather controller (abxervyer)
   * @param dreamCapable the Dream Capable Object (player)
   */
  public ForestKeeperFactory(WeatherController controller, DreamCapable dreamCapable) {
    super(BASE_SPAWN_RATE, dreamCapable);
    this.controller = controller;
    this.controller.subscribe(this);
  }

  /**
   * Spawns a forest keeper.
   *
   * @return The forest keeper that is spawned
   */
  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < getSpawningRate()) {
      return new ForestKeeper(this.controller, getDreamCapable());
    }
    return null;
  }

  /**
   * Updates the spawning rate of the forest keeper based on the current weather.
   *
   * @param currentWeather The current weather
   */
  @Override
  public void update(Weather currentWeather) {
    if (currentWeather == Weather.SUNNY) {
      setSpawningRate(BASE_SPAWN_RATE * 2);
      new Display().println("The forest-keepers are more active!");
    } else {
      setSpawningRate(BASE_SPAWN_RATE);
      new Display().println("The forest-keepers are less active.");
    }
  }

  /**
   * Resets the spawning rate of the forest keeper to the default spawning rate.
   */
  @Override
  public void clear() {
    setSpawningRate(BASE_SPAWN_RATE);
  }
}
