package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Sellable;

/**
 * A subclass of Action that represents the action of selling an item to a merchant. Sell action is
 * an action that allows an actor to choose and sell an item to a merchant.
 */
public class SellAction extends Action {

  /**
   * The item to be sold
   */
  private final Sellable item;
  /**
   * The selling price of the item
   */
  private final int price;

  /**
   * Constructor with item and price
   *
   * @param item the item to be sold
   */
  public SellAction(Sellable item) {
    this.item = item;
    this.price = item.getSellPrice();
  }

  /**
   * Execute the action of selling the item to a merchant. Returns a description of this action to
   * display in the menu.
   *
   * @param actor The actor performing the action.
   * @return a description of the sell action
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    actor.addBalance(this.price);
    return this.item.sell(actor);
  }

  /**
   * Returns a string that describes the action of selling an item to the menu.
   *
   * @param actor The actor performing the action.
   * @return the action description to be displayed on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " sells " + this.item + " at $" + this.price;
  }
}
