package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.BuyAction;
import game.actions.ConsumeAction;
import game.capabilities.Ability;
import java.util.Random;

/**
 * A subclass of Item that implements Consumable interface. A healing vial is a consumable item that
 * can restore a fraction of the actor's health.
 *
 * @author Soo Zhan Hong
 * @see Item
 * @see Consumable
 */
public class HealingVial extends Item implements Consumable, Buyable {

  /**
   * The type of attribute that is affected by consuming the healing vial
   */
  private final static String ATTRIBUTE = "health";

  /**
   * Constructs a new healing vial with the default attributes.
   */
  public HealingVial() {
    super("Healing Vial", 'a', true);
  }

  /**
   * List of allowable actions that healing vial can perform to the current actor
   *
   * @param owner the actor that owns the item
   * @return an ActionList that contain the ConsumeAction
   */
  @Override
  public ActionList allowableActions(Actor owner) {
    ActionList actionList = new ActionList();
    if (!owner.hasCapability(Ability.TRADING)){
      actionList.add(new ConsumeAction(this));
    }
    else {
      actionList.add(new BuyAction(this));
    }
    return actionList;
  }

  /**
   * Consumes the healing vial and returns the amount of health gained by the actor. The healing
   * vial restores 10% of the actor's maximum health and is removed once it is consumed.
   *
   * @param actor The actor who consumes the healing vial
   * @return The amount of health gained by the actor
   */
  @Override
  public int consume(Actor actor) {
    int healthRecovery = Math.round(actor.getAttributeMaximum(BaseActorAttributes.HEALTH) / 10f);
    actor.heal(healthRecovery);
    actor.removeItemFromInventory(this);
    return healthRecovery;
  }

  /**
   * Returns the type of attribute that is affected by consuming the healing vial.
   *
   * @return The type of attribute that is affected by consuming the healing vial
   */
  @Override
  public String getAttribute() {
    return HealingVial.ATTRIBUTE;
  }

  @Override
  public String buy(Actor actor) {
    actor.addItemToInventory(new HealingVial());
    return actor + " had purchased a " + this;
  }

  @Override
  public int getBuyPrice() {
    Random random = new Random();
    int price = 100;
    int luck = 25;
    if (random.nextInt(100) < luck){
      return Math.round(price * 1.5f);
    }
    return price;
  }
}
