package game.grounds;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.RedWolf;

import java.util.Random;

public class Bushes extends Ground {
    private Random random = new Random();
    private static final int BASE_RED_WOLF_SPAWN_CHANCE = 30;
    /**
     * Constructor.
     */
    public Bushes() {
        super('m');

    }

    @Override
    public void tick(Location location) {
        int spawnChance = random.nextInt(100);
        if (spawnChance < BASE_RED_WOLF_SPAWN_CHANCE && !location.containsAnActor()) {
            location.addActor(new RedWolf());
            new Display().println("a Red Wolf has spawned at " + location);
        }
    }
}
