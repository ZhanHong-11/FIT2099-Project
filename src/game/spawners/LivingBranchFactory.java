package game.spawners;

import game.actors.enemies.EldentreeGuardian;
import game.actors.enemies.Enemy;
import game.actors.enemies.LivingBranch;
import game.dream.DreamCapable;

import java.util.Random;

public class LivingBranchFactory extends EnemyFactory {

    /**
     * The base spawning rate of the eldentree guardian
     */
    public static final int BASE_SPAWN_RATE = 90;
    private Random random = new Random();

    /**
     * Constructs a new living branch factory with the default spawning rate.
     * @param dreamCapable the Dream Capable Object (player)
     */
    public LivingBranchFactory(DreamCapable dreamCapable) {
        super(BASE_SPAWN_RATE, dreamCapable);

    }

    /**
     * Spawns a living branch.
     *
     * @return The living branch that is spawned
     */
    @Override
    public Enemy spawnEnemy() {
        if (random.nextInt(100) < getSpawningRate()) {
            return new LivingBranch(getDreamCapable());
        }
        return null;
    }
}