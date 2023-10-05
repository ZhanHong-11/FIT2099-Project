package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.RedWolf;
import game.gamemaps.weather.Weather;
import game.gamemaps.weather.WeatherPublisher;
import game.gamemaps.weather.WeatherSubscriber;

import java.util.Random;

/**
 * A factory that can spawn a red wolf.
 *
 * @see EnemyFactory
 */
public class RedWolfFactory implements EnemyFactory, WeatherSubscriber {
  /**
   * The base spawning rate of the red wolf
   */
  public static final int BASE_SPAWN_RATE = 30;
  /**
   * The spawning rate of the red wolf
   */
  private int spawningRate;
  private Random random = new Random();
  private WeatherPublisher publisher;

  /**
   * Constructs a new red wolf factory with the default spawning rate.
   */
  public RedWolfFactory(WeatherPublisher publisher){
    this.spawningRate = BASE_SPAWN_RATE;
    this.publisher = publisher;
    this.publisher.subscribe(Weather.SUNNY, this);
    this.publisher.subscribe(Weather.RAINY, this);
  }

//  /**
//   * Constructs a new red wolf factory with the specified spawning rate. Useful for those
//   * ability that can change the spawning rate of a spawning ground
//   *
//   * @param spawningRate The spawning rate of the red wolf
//   */
//  public RedWolfFactory(int spawningRate){
//    this.spawningRate = spawningRate;
//  }

  /**
   * Spawns a red wolf.
   *
   * @return The red wolf that is spawned
   */
  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < spawningRate){
      return new RedWolf(this.publisher);
    }
    return null;
  }

  @Override
  public void update(Weather currentWeather) {
    if (currentWeather == Weather.RAINY) {
      this.spawningRate = Math.round(BASE_SPAWN_RATE * 1.5f);
      System.out.println("Spawning rate increased" + this.spawningRate);
    } else {
      this.spawningRate = BASE_SPAWN_RATE;
      System.out.println("Spawning rate reset");
    }
  }
}
