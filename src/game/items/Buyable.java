package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface that represents an item that can be bought by the player
 */
public interface Buyable {

  /**
   * Buys the item.
   *
   * @param actor The actor who buys the item
   * @return Description after the item is bought
   */
  String buy(Actor actor);
}
