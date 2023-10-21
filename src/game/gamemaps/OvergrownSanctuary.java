package game.gamemaps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;

import java.util.Arrays;
import java.util.List;

/**
 * A subclass of GameMap, representing the map of the Overgrown Sanctuary.
 *
 * @author Alvin Andrean
 * @see GameMap
 */
public class OvergrownSanctuary extends GameMap {

  /**
   * A list of strings that represent the map layout of the burial ground
   */
  private static final List<String> MAP = Arrays.asList(
      "++++.....++++........++++~~~~~.......~~~..........",
      "++++......++.........++++~~~~.........~...........",
      "+++..................+++++~~.......+++............",
      "....................++++++......++++++............",
      "...................++++........++++++~~...........",
      "...................+++.........+++..~~~...........",
      "..................+++..........++...~~~...........",
      "~~~...........................~~~..~~~~...........",
      "~~~~............+++..........~~~~~~~~~~...........",
      "~~~~............+++.........~~~~~~~~~~~~..........",
      "++~..............+++.......+~~........~~..........",
      "+++..............+++......+++..........~~.........",
      "+++..............+++......+++..........~~.........",
      "~~~..............+++......+++..........~~~........",
      "~~~~.............+++......+++..........~~~........");

  /**
   * Constructs a new overground sanctuary with the given ground factory.
   *
   * @param groundFactory The ground factory that creates the ground types for the map
   */
  public OvergrownSanctuary(GroundFactory groundFactory) {
    super(groundFactory, OvergrownSanctuary.MAP);
  }

}
