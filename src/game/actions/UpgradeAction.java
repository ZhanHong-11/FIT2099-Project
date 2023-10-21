package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Upgradable;

/**
 * A subclass of Action that represents the action of upgrading an upgradable item.
 */
public class UpgradeAction extends Action {

  /**
   * The upgradable item to be upgraded
   */
  private final Upgradable upgradableItem;

  /**
   * Constructs a new upgrade action with the given upgradable item.
   *
   * @param upgradableItem The upgradable item to be upgraded
   */
  public UpgradeAction(Upgradable upgradableItem) {
    this.upgradableItem = upgradableItem;
  }

  /**
   * Execute the action of upgrading the upgradable item. Returns a description of this action.
   *
   * @param actor The actor performing the action.
   * @param map   The map the actor is on.
   * @return a description of the upgrade action
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    if (actor.getBalance() >= upgradableItem.getUpgradeCost()) {
      actor.deductBalance(upgradableItem.getUpgradeCost());
      return upgradableItem.upgrade();
    }
    return "Insufficient balance!";
  }

  /**
   * Returns a string that describes the action of upgrading an upgradable item to the menu.
   *
   * @param actor The actor performing the action.
   * @return the action description to be displayed on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " upgrades " + upgradableItem;
  }
}
