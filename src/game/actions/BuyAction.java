package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Buyable;

/**
 * A subclass of Action that represents the action of buying an item from a merchant. Buy action is
 * an action that allows an actor to choose and buy an item from a merchant.
 */
public class BuyAction extends Action {

  /**
   * The item to be bought
   */
  private final Buyable item;
  /**
   * The price of the item
   */
  private final int price;
  /**
   * The luck of the actor
   */
  private boolean isLucky = true;

  /**
   * Constructor with item and price
   *
   * @param item  the item to be bought
   * @param price the price of the item
   */
  public BuyAction(Buyable item, int price) {
    this.item = item;
    this.price = price;
  }

  /**
   * Constructor with item, price and luck. This constructor is useful for those items that may scam
   * the buyer.
   *
   * @param item    the item to be bought
   * @param price   the price of the item
   * @param isLucky the luck of the actor
   */
  public BuyAction(Buyable item, int price, boolean isLucky) {
    this.item = item;
    this.price = price;
    this.isLucky = isLucky;
  }

  /**
   * Execute the action of buying the item from a merchant. Returns a description of this action to
   * display in the menu.
   *
   * @param actor The actor performing the action.
   * @return a description of this action (whether it is successful or not)
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    if (actor.getBalance() >= this.price) {
      actor.deductBalance(this.price);
      if (this.isLucky) {
        return this.item.buy(actor);
      } else {
        return actor + " had been scammed!";
      }
    } else {
      return "Insufficient balance!";
    }
  }

  /**
   * Returns a string that describes the action of buying an item to the menu.
   *
   * @param actor The actor performing the action.
   * @return the action description to be displayed on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " buys " + this.item + " at $" + this.price;
  }
}

