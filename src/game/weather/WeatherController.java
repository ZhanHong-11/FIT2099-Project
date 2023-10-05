package game.weather;

/**
 * An interface that represents a controller that can control the weather and updates to its
 * subscribers.
 */
public interface WeatherController {

  /**
   * For the enemy that are affected by the weather to subscribe to the weather controller.
   *
   * @param subscriber the subscriber to subscribe
   */
  void subscribe(WeatherSubscriber subscriber);

  /**
   * For the enemy that are affected by the weather to unsubscribe to the weather controller.
   *
   * @param subscriber the subscriber to unsubscribe
   */
  void unsubscribe(WeatherSubscriber subscriber);

  /**
   * For the weather controller to notify the subscribers of the weather update.
   */
  void notifyWeather();

  /**
   * For the weather controller to clear the weather.
   *
   * @return the message to be displayed
   */
  String clearWeather();
}
