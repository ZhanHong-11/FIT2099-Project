package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Buyable;

public class BuyAction extends Action {
  private final Buyable item;
  private final int price;
  private boolean isLucky = true;

  public BuyAction(Buyable item, int price){
    this.item = item;
    this.price = price;
  }

  public BuyAction(Buyable item, int price, boolean isLucky){
    this.item = item;
    this.price = price;
    this.isLucky = isLucky;
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    if (actor.getBalance() >= this.price){
      actor.deductBalance(this.price);
      if (this.isLucky){
        return this.item.buy(actor);
      }
      else {
        return actor + " had been scammed!";}
    }
    else {
      return "Insufficient balance!";
    }
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " buys " + this.item + " at $" + this.price;
  }
}

