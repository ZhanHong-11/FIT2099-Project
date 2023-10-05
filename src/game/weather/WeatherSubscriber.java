package game.weather;

/**
 * An interface that represents a subscriber that can subscribe to a weather controller for weather
 * updates.
 */
public interface WeatherSubscriber {

  /**
   * For the subscriber to update the weather.
   *
   * @param currentWeather the current weather
   */
  void update(Weather currentWeather);

  /**
   * For the subscriber to clear the weather effect if the weather is cleared.
   */
  void clear();
}
