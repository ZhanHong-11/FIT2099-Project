package game.items;

/**
 * An interface that represents an item that can be upgraded by the player
 */
public interface Upgradable {

  /**
   * Upgrades the item. The actual implementation should be completed in the concrete class.
   *
   * @return Description after the item is upgraded
   */
  String upgrade();

  /**
   * Gets the upgrade cost of the item.
   *
   * @return the upgrade cost of the item
   */
  int getUpgradeCost();
}
