package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.capabilities.Ability;
import java.util.Random;

/**
 * A subclass of Item that implements Consumable interface. A refreshing flask is a consumable item
 * that can restore a fraction of the actor's stamina. It can be bought and sold by the player.
 *
 * @author Soo Zhan Hong
 * @see Item
 * @see Consumable
 * @see Buyable
 * @see Sellable
 */
public class RefreshingFlask extends Item implements Consumable, Buyable, Sellable {

  /**
   * The base selling price of the refreshing flask
   */
  private static final int BASE_SELL_PRICE = 25;
  private Random random = new Random();

  /**
   * Constructs a new refreshing flask with the default attributes.
   */
  public RefreshingFlask() {
    super("Refreshing Flask", 'u', true);
  }

  /**
   * List of allowable actions that refreshing flask can perform to the current actor
   *
   * @param owner the actor that owns the item
   * @return an ActionList that contain the ConsumeAction
   */
  @Override
  public ActionList allowableActions(Actor owner) {
    ActionList actionList = new ActionList();
    actionList.add(new ConsumeAction(this));
    return actionList;
  }

  /**
   * List of allowable actions that refreshing flask can perform to the current actor This item can
   * be sold to actor that has the trading ability
   *
   * @param otherActor the other actor
   * @return an ActionList that contain the SellAction
   */
  @Override
  public ActionList allowableActions(Actor otherActor, Location location) {
    ActionList actions = super.allowableActions(otherActor, location);
    if (otherActor.hasCapability(Ability.TRADING)) {
      actions.add(new SellAction(this));
    }
    return actions;
  }

  /**
   * Consumes the refreshing flask and returns the amount of stamina gained by the actor. The
   * refreshing flask restores 20% of the actor's maximum stamina and is removed after being
   * consumed.
   *
   * @param actor The actor who consumes the refreshing flask
   * @return The amount of stamina gained by the actor
   */
  @Override
  public String consume(Actor actor) {
    int staminaRecovery = Math.round(actor.getAttributeMaximum(BaseActorAttributes.STAMINA) / 5f);
    actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,
        staminaRecovery);
    actor.removeItemFromInventory(this);
    return actor + " restores the stamina by " + staminaRecovery + " points.";
  }

  /**
   * Buys the refreshing flask.
   *
   * @param actor The actor who buys the refreshing flask
   * @return Description after the refreshing flask is bought
   */
  @Override
  public String buy(Actor actor) {
    actor.addItemToInventory(new RefreshingFlask());
    return actor + " had purchased a " + this;
  }

  /**
   * Sells the refreshing flask and returns a message showing that the player has sold the item
   * after selling the item, the refreshing flask is removed from the player's inventory
   *
   * @param actor The actor who's selling the refreshing flask
   * @return a message showing that the player has sold the refreshing flask
   */
  @Override
  public String sell(Actor actor) {
    int luck = 50;
    actor.removeItemFromInventory(this);
    if (random.nextInt(100) < luck) {
      actor.deductBalance(BASE_SELL_PRICE);
      return actor + " had been scammed!";
    } else {
      return actor + " had sold a " + this;
    }
  }

  /**
   * Returns the selling price of the refreshing flask. The selling price of the refreshing flask is
   * 25 coins.
   *
   * @return the selling price of the refreshing flask
   */
  @Override
  public int getSellPrice() {
    return BASE_SELL_PRICE;
  }
}
