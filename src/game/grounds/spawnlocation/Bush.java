package game.grounds.spawnlocation;

import game.actors.spawners.EnemyFactory;

public class Bush extends SpawnGround {

    public Bush(EnemyFactory enemyFactory) {
        super('m', enemyFactory);
    }
}
