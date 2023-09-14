package game.gamemaps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import java.util.Arrays;
import java.util.List;

/**
 * A subclass of GameMap, representing the map of the Burial Ground.
 *
 * @author Soo Zhan Hong
 * @see GameMap
 */
public class BurialGround extends GameMap {

  /**
   * A list of strings that represent the map layout of the burial ground
   */
  private static final List<String> MAP = Arrays.asList(
      "...........+++++++........~~~~~~++....~~",
      "....n......++++++.........~~~~~~+.....~~",
      "............++++.....n.....~~~~~......++",
      "............+.+.............~~~.......++",
      "..........++~~~.......................++",
      ".........+++~~~....#######...........+++",
      ".........++++~.....#_____#.........+++++",
      "..........+++......#_____#........++++++",
      "..........+++......###_###.......~~+++++",
      "..........~~.....................~~...++",
      "..........~~~..................++.......",
      "...........~~....~~~~~.........++.......",
      "......~~....++..~~~~~~~~~~~......~...n..",
      "....+~~~~..++++++++~~~~~~~~~....~~~.....",
      "..n.+~~~~..++++++++~~~..~~~~~..~~~~~....");

  /**
   * Constructs a new burial ground with the given ground factory.
   *
   * @param groundFactory The ground factory that creates the ground types for the map
   */
  public BurialGround(GroundFactory groundFactory) {
    super(groundFactory, BurialGround.MAP);
  }
}
