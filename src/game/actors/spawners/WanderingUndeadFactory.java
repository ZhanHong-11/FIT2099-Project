package game.actors.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.WanderingUndead;
import java.util.Random;

public class WanderingUndeadFactory implements EnemyFactory{
  private Random random = new Random();
  private static final int BASE_SPAWN_RATE = 25;

  private int spawningRate;

  public WanderingUndeadFactory(){
    this.spawningRate = BASE_SPAWN_RATE;
  }

  public WanderingUndeadFactory(int spawningRate){
    this.spawningRate = spawningRate;
  }

  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < spawningRate){
      return new WanderingUndead();
    }
    return null;
  }
}
