package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Sellable;

public class SellAction extends Action {
  private final Sellable item;
  private final int amount;

  public SellAction(Sellable item){
    this.item = item;
    this.amount = item.getSellPrice();
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    actor.addBalance(this.amount);
    return this.item.sell(actor);
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " sells " + this.item + " at $" + this.amount;
  }
}
