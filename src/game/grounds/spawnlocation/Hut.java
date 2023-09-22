package game.grounds.spawnlocation;

import game.actors.spawners.EnemyFactory;

public class Hut extends SpawnGround {

    public Hut(EnemyFactory enemyFactory) {
        super('h', enemyFactory);
    }
}
