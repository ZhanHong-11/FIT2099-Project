package game.dream;

import edu.monash.fit2099.engine.positions.GameMap;

public interface DreamCapable {
  void subscribe(Resettable resettable);
  void unsubscribe(Resettable resettable);
  void respawn(GameMap map);
}
