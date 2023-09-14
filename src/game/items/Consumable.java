package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface that represents an item that is consumable. A consumable item is an item that can be
 * consumed by an actor to gain some benefits or effects, but only for a certain times. For example,
 * a potion, a food, or a medicine.
 *
 * @author Soo Zhan Hong
 */
public interface Consumable {

  /**
   * Consumes the item.
   *
   * @param actor The actor who consumes the item
   * @return the effect of the consumable item
   */
  int consume(Actor actor);

  /**
   * Returns the attribute that is affected by consuming the item. For example, health, stamina,
   * etc.
   *
   * @return The type of the attribute, such as 'health', 'stamina', etc.
   */
  String getAttribute();
}
