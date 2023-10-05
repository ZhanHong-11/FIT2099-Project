package game.gamemaps.weather;

public interface WeatherPublisher {

    void subscribe(Weather weather, WeatherSubscriber subscriber);
    void unsubscribe();
    void notifyWeather();
}
