package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * A subclass of Item that represents an old key. An old key is a portable item that can be used to
 * unlock unlockable object.
 *
 * @author Soo Zhan Hong
 * @see Item
 */
public class Key extends Item {

  /**
   * Constructs a key with the default attributes.
   */
  public Key() {
    super("Old Key", '-', true);
  }
}
