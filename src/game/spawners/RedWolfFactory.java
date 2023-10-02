package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.RedWolf;
import java.util.Random;

public class RedWolfFactory implements EnemyFactory{
  public static final int BASE_SPAWN_RATE = 30;
  private int spawningRate;
  private Random random = new Random();

  public RedWolfFactory(){
    this.spawningRate = BASE_SPAWN_RATE;
  }

  public RedWolfFactory(int spawningRate){
    this.spawningRate = spawningRate;
  }

  @Override
  public Enemy spawnEnemy() {
    if (random.nextInt(100) < spawningRate){
      return new RedWolf();
    }
    return null;
  }
}
