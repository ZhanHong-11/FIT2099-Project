package game.grounds.spawnlocation;

import game.spawners.EnemyFactory;

/**
 * a class which represents a Hut where it extends to SpawnGround
 * @author Soo Zhan Hong
 * @author Marco Gotama
 * @see SpawnGround
 */
public class Hut extends SpawnGround {

    /**
     * Constructs a new Hut
     * @param enemyFactory represents an Enemy Factory that will spawn a certain type of enemy in this type of ground
     */
    public Hut(EnemyFactory enemyFactory) {
        super('h', enemyFactory);
    }
}
