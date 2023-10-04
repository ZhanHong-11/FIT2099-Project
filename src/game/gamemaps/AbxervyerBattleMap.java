package game.gamemaps;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;

import game.capabilities.Ability;
import game.capabilities.Status;
import game.gamemaps.weather.Weather;
import game.gamemaps.weather.WeatherCapable;
import game.grounds.spawnlocation.Bush;
import game.grounds.spawnlocation.Hut;
import game.grounds.spawnlocation.SpawnGround;
import game.spawners.ForestKeeperFactory;
import game.spawners.RedWolfFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A subclass of GameMap, representing the map of the Abxervyer Battle Room.
 *
 * @author Alvin Andrean
 * @see GameMap
 */
public class AbxervyerBattleMap extends GameMap implements WeatherCapable {

  private final ArrayList<SpawnGround> huts = new ArrayList<>();
  private final ArrayList<SpawnGround> bushes = new ArrayList<>();
  private Weather weather;
  private int turnCount;
  /**
   * A list of strings that represent the map layout of the abxervyer battle map / room
   */
  private static final List<String> MAP = Arrays.asList(
      "~~~~.......+++......~+++++..............",
      "~~~~.......+++.......+++++..............",
      "~~~++......+++........++++..............",
      "~~~++......++...........+..............+",
      "~~~~~~...........+.......~~~++........++",
      "~~~~~~..........++++....~~~~++++......++",
      "~~~~~~...........+++++++~~~~.++++.....++",
      "~~~~~..............++++++~~...+++.....++",
      "......................+++......++.....++",
      ".......................+~~............++",
      ".......................~~~~...........++",
      "........................~~++...........+",
      ".....++++...............+++++...........",
      ".....++++~..............+++++...........",
      "......+++~~.............++++...........~",
      ".......++..++++.......................~~",
      "...........+++++......................~~",
      "...........++++++.....................~~",
      "..........~~+++++......................~",
      ".........~~~~++++..................~~..~");


  /**
   * Constructs a new abxervyer battle map with the given ground factory.
   *
   * @param groundFactory The ground factory that creates the ground types for the map
   */
  public AbxervyerBattleMap(GroundFactory groundFactory) {
    super(groundFactory, AbxervyerBattleMap.MAP);
    this.huts.add(new Hut(new ForestKeeperFactory()));
    this.huts.add(new Hut(new ForestKeeperFactory()));
    this.bushes.add(new Bush(new RedWolfFactory()));
    this.weather = Weather.SUNNY;
    this.turnCount = 2;
    setGroundSpawnRate();
  }

  @Override
  public void tick() {
    super.tick();

    System.out.println("Weather: " + this.weather);

    for (Actor actor : actorLocations) {
      if (actor.hasCapability(Ability.CONTROL_WEATHER) && this.contains(actor)) {
        turnCounter();
      }
    }
//        turnCounter();
    applyWeatherBuff();
  }

  @Override
  public void switchWeather() {
    if (this.weather == Weather.RAINY) {
      this.weather = Weather.SUNNY;
    } else {
      this.weather = Weather.RAINY;
    }
    setGroundSpawnRate();
  }

  @Override
  public void turnCounter() {
    if (this.turnCount > 0) {
      this.turnCount--;
    } else {
      this.turnCount = 2;
      switchWeather();
    }
  }

  private void applyWeatherBuff() {
    // Add buff for actors
    for (Actor actor : actorLocations) {
      if (this.contains(actor)) {
        if (this.weather == Weather.SUNNY) {
          actor.addCapability(Status.SUNNY_BUFF);
          actor.removeCapability(Status.RAINY_BUFF);
        } else {
          actor.addCapability(Status.RAINY_BUFF);
          actor.removeCapability(Status.SUNNY_BUFF);
        }
      }
    }
  }

  private void setGroundSpawnRate() {
    if (this.weather == Weather.SUNNY) {
      for (SpawnGround hut : this.huts) {
        hut.setEnemyFactory(new ForestKeeperFactory(ForestKeeperFactory.BASE_SPAWN_RATE * 2));
      }
      for (SpawnGround bush : this.bushes) {
        bush.setEnemyFactory(new RedWolfFactory());
      }
    } else {
      for (SpawnGround hut : this.huts) {
        hut.setEnemyFactory(new ForestKeeperFactory());
      }
      for (SpawnGround bush : this.bushes) {
        bush.setEnemyFactory(new RedWolfFactory(Math.round(RedWolfFactory.BASE_SPAWN_RATE * 1.5f)));
      }
    }
  }
}


