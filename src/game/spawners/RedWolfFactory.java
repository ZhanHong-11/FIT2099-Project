package game.spawners;

import edu.monash.fit2099.engine.displays.Display;
import game.actors.enemies.Enemy;
import game.actors.enemies.RedWolf;
import game.dream.DreamCapable;
import game.weather.Weather;
import game.weather.WeatherController;
import game.weather.WeatherSubscriber;

import java.util.Random;

/**
 * A factory that can spawn a red wolf.
 *
 * @see EnemyFactory
 * @see WeatherSubscriber
 */
public class RedWolfFactory extends EnemyFactory implements WeatherSubscriber {

  /**
   * The base spawning rate of the red wolf
   */
  public static final int BASE_SPAWN_RATE = 30;
  /**
   * The weather controller
   */
  private WeatherController controller;
  private Random random = new Random();

  /**
   * Constructs a new red wolf factory with the default spawning rate.
   * @param dreamCapable the Dream Capable Object (player)
   */
  public RedWolfFactory(WeatherController controller, DreamCapable dreamCapable) {
    super(BASE_SPAWN_RATE, dreamCapable);
    this.controller = controller;
    this.controller.subscribe(this);
  }

  /**
   * Spawns a red wolf.
   *
   * @return The red wolf that is spawned
   */
  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < getSpawningRate()) {
      return new RedWolf(this.controller, getDreamCapable());
    }
    return null;
  }

  /**
   * Updates the spawning rate of the red wolf based on the current weather.
   *
   * @param currentWeather The current weather
   */
  @Override
  public void update(Weather currentWeather) {
    if (currentWeather == Weather.RAINY) {
      setSpawningRate(Math.round(BASE_SPAWN_RATE * 1.5f));
      new Display().println("The red-wolves are more active!");
    } else {
      setSpawningRate(BASE_SPAWN_RATE);
      new Display().println("The red-wolves are less active.");
    }
  }

  /**
   * Clears the weather effect if the weather is cleared.
   */
  @Override
  public void clear() {
    setSpawningRate(BASE_SPAWN_RATE);
  }
}
