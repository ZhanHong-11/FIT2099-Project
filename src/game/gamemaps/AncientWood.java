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

  public AncientWood(GroundFactory groundFactory) {
    super(groundFactory, AncientWood.MAP);
  }
}
