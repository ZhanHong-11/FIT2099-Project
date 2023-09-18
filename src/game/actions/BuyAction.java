package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Buyable;

public class BuyAction extends Action {
  private final Buyable item;
  private final int amount;

  public BuyAction(Buyable item){
    this.item = item;
    this.amount = this.item.getBuyPrice();
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    if (actor.getBalance() >= this.amount){
      actor.deductBalance(this.amount);
      return this.item.buy(actor);
    }
    else {
      return "Insufficient balance!";
    }
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " buys " + this.item + " at $" + this.amount;
  }
}

