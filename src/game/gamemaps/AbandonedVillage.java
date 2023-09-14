package game.gamemaps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import java.util.Arrays;
import java.util.List;

/**
 * A subclass of GameMap, representing the map of the Abandoned Village.
 *
 * @author Soo Zhan Hong
 * @see GameMap
 */
public class AbandonedVillage extends GameMap {

  /**
   * A list of strings that represent the map layout of the abandoned village
   */
  private static final List<String> MAP = Arrays.asList(
      "...........................................................",
      "...#######.................................................",
      "...#___....................................................",
      "...#..___#......................................n..........",
      "...###.###................#######..........................",
      "..........................#_____#..........................",
      "........~~................#_____#..........................",
      ".........~~~..............###_###..........................",
      "...~~~~~~~~...............++...............................",
      "....~~~~~.................................###..##..........",
      "~~~~~~~...................................#___..#..........",
      "~~~~~~....................................#..___#..........",
      "~~~~~~~~~.................................#######..........");

  /**
   * Constructs a new abandoned village with the given ground factory.
   *
   * @param groundFactory The ground factory that creates the ground types for the map
   */
  public AbandonedVillage(GroundFactory groundFactory) {
    super(groundFactory, AbandonedVillage.MAP);
  }
}
