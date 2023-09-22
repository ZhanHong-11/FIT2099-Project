package game.actors.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.ForestKeeper;
import java.util.Random;

public class ForestKeeperFactory implements EnemyFactory{
  private Random random = new Random();
  private static final int BASE_SPAWN_RATE = 15;

  private int spawningRate;

  public ForestKeeperFactory(){
    this.spawningRate = BASE_SPAWN_RATE;
  }

  public ForestKeeperFactory(int spawningRate){
    this.spawningRate = spawningRate;
  }

  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < spawningRate){
      return new ForestKeeper();
    }
    return null;
  }
}
