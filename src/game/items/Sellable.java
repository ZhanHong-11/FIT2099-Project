package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface that represents an item that can be sold by the player
 */
public interface Sellable {

  /**
   * Sells the item.
   *
   * @param actor The actor who sells the item
   * @return Description after the item is sold
   */
  String sell(Actor actor);

  /**
   * Gets the sell price of the item.
   *
   * @return the sell price of the item
   */
  int getSellPrice();
}
