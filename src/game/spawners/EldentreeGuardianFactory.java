package game.spawners;

import game.actors.enemies.EldentreeGuardian;
import game.actors.enemies.Enemy;


import java.util.Random;

public class EldentreeGuardianFactory extends EnemyFactory {

    /**
     * The base spawning rate of the eldentree guardian
     */
    public static final int BASE_SPAWN_RATE = 20;
    private Random random = new Random();

    /**
     * Constructs a new eldentree guardian factory with the default spawning rate.
     */
    public EldentreeGuardianFactory() {
        super(BASE_SPAWN_RATE);

    }

    /**
     * Spawns a eldentree guardian.
     *
     * @return The eldentree guardian that is spawned
     */
    @Override
    public Enemy spawnEnemy() {
        if (random.nextInt(100) < getSpawningRate()) {
            return new EldentreeGuardian();
        }
        return null;
    }
}
