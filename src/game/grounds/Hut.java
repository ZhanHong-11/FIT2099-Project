package game.grounds;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.ForestKeeper;

import java.util.Random;

public class Hut extends Ground {
    private Random random = new Random();
    private static final int BASE_FOREST_KEEPER_SPAWN_CHANCE = 15;
    /**
     * Constructor.
     */
    public Hut() {
        super('h');

    }

    @Override
    public void tick(Location location) {
        int spawnChance = random.nextInt(100);
        if (spawnChance < BASE_FOREST_KEEPER_SPAWN_CHANCE && !location.containsAnActor()) {
            location.addActor(new ForestKeeper());
            new Display().println("a Forest Keeper has spawned at " + location);
        }
    }
}
