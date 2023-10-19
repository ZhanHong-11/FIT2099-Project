package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Upgradable;

public class UpgradeAction extends Action {
  private final Upgradable upgradableItem;

  public UpgradeAction(Upgradable upgradableItem) {
    this.upgradableItem = upgradableItem;
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    if (actor.getBalance() >= upgradableItem.getUpgradeCost()){
      actor.deductBalance(upgradableItem.getUpgradeCost());
      return upgradableItem.upgrade();
    }
    return "Insufficient balance!";
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " upgrades " + upgradableItem;
  }
}
