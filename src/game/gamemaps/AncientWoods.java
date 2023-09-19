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
public class AncientWoods extends GameMap {
    private static final List<String> MAP = Arrays.asList(
            "....+++..............................+++++++++....~~~....~~~",
            "+...+++...m..........................++++++++.....~~~.....~~",
            "++...............#######..............++++.........~~.......",
            "++...............#_____#......................h....~~~......",
            "+................#_____#............................~~......",
            ".................###_###............~...............~~.....~",
            "...............................~.+++~~..............~~....~~",
            "......h..............~........~~+++++...............~~~...~~",
            "....................~~~.........++++............~~~~~~~...~~",
            "....................~~~~.~~~~..........~........~~~~~~.....~",
            "++++...............~~~~~~~~~~~...m....~~~.......~~~~~~......",
            "+++++..............~~~~~~~~~~~........~~~........~~~~~......");

    public AncientWoods(GroundFactory groundFactory) {
      super(groundFactory, AncientWoods.MAP);
    }
}
