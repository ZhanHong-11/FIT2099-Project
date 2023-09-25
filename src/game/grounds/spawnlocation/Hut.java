package game.grounds.spawnlocation;

import game.spawners.EnemyFactory;

public class Hut extends SpawnGround {

    public Hut(EnemyFactory enemyFactory) {
        super('h', enemyFactory);
    }
}
