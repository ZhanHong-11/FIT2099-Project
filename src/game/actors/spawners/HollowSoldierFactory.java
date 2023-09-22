package game.actors.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.HollowSoldier;
import java.util.Random;

public class HollowSoldierFactory implements EnemyFactory{
  private Random random = new Random();
  private static final int BASE_SPAWN_RATE = 10;

  private int spawningRate;

  public HollowSoldierFactory(){
    this.spawningRate = BASE_SPAWN_RATE;
  }

  public HollowSoldierFactory(int spawningRate){
    this.spawningRate = spawningRate;
  }

  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < spawningRate){
      return new HollowSoldier();
    }
    return null;
  }
}
