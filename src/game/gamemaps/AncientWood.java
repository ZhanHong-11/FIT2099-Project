package game.gamemaps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;

import java.util.Arrays;
import java.util.List;

/**
 * A subclass of GameMap, representing the map of the Ancient Woods
 *
 * @author Alvin Andrean
 * @see GameMap
 */
public class AncientWood extends GameMap {

  private static final List<String> MAP = Arrays.asList(
      "....+++..............................+++++++++....~~~....~~~",
      "+...+++..............................++++++++.....~~~.....~~",
      "++...............#######..............++++.........~~.......",
      "++...............#_____#...........................~~~......",
      "+................#_____#............................~~......",
      ".................###_###............~...............~~.....~",
      "...............................~.+++~~..............~~....~~",
      ".....................~........~~+++++...............~~~...~~",
      "....................~~~.........++++............~~~~~~~...~~",
      "....................~~~~.~~~~..........~........~~~~~~.....~",
      "++++...............~~~~~~~~~~~........~~~.......~~~~~~......",
      "+++++..............~~~~~~~~~~~........~~~........~~~~~......");

  /**
   * Constructs a new ancient wood with the given ground factory.
   *
   * @param groundFactory The ground factory that creates the ground types for the map
   */
  public AncientWood(GroundFactory groundFactory) {
    super(groundFactory, AncientWood.MAP);
  }
}
