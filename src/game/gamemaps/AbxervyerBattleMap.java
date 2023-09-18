package game.gamemaps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;

import java.util.Arrays;
import java.util.List;

/**
 * A subclass of GameMap, representing the map of the Abxervyer Battle Room.
 *
 * @author Alvin Andrean
 * @see GameMap
 */
public class AbxervyerBattleMap extends GameMap {

    /**
     * A list of strings that represent the map layout of the abxervyer battle map / room
     */
    private static final List<String> MAP = Arrays.asList(
            "~~~~..h....+++......~+++++..............",
            "~~~~.......+++.......+++++..............",
            "~~~++......+++........++++.......h......",
            "~~~++......++...........+..............+",
            "~~~~~~...........+.......~~~++........++",
            "~~~~~~..........++++....~~~~++++......++",
            "~~~~~~...........+++++++~~~~.++++.....++",
            "~~~~~..............++++++~~...+++.....++",
            "......................+++......++.....++",
            ".......................+~~............++",
            ".......................~~~~...........++",
            "........................~~++.....m.....+",
            ".....++++...............+++++...........",
            ".....++++~..m...........+++++...........",
            "......+++~~.............++++...........~",
            ".......++..++++.......................~~",
            "...........+++++......................~~",
            "....h......++++++.....................~~",
            "..........~~+++++......................~",
            ".........~~~~++++..................~~..~");

    /**
     * Constructs a new abxervyer battle map with the given ground factory.
     *
     * @param groundFactory The ground factory that creates the ground types for the map
     */
    public AbxervyerBattleMap(GroundFactory groundFactory) {
        super(groundFactory, AbxervyerBattleMap.MAP);
    }
}


