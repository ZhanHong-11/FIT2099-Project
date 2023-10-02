package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
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
public class HealingVial extends Item implements Consumable, Buyable, Sellable {
  private Random random = new Random();

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
    actionList.add(new ConsumeAction(this));
    return actionList;
  }

  @Override
  public ActionList allowableActions(Actor otherActor, Location location) {
    ActionList actions = super.allowableActions(otherActor, location);
    if (otherActor.hasCapability(Ability.TRADING)){
      actions.add(new SellAction(this));
    }
    return actions;
  }

  /**
   * Consumes the healing vial and returns the amount of health gained by the actor. The healing
   * vial restores 10% of the actor's maximum health and is removed once it is consumed.
   *
   * @param actor The actor who consumes the healing vial
   * @return The amount of health gained by the actor
   */
  @Override
  public String consume(Actor actor) {
    int healthRecovery = Math.round(actor.getAttributeMaximum(BaseActorAttributes.HEALTH) / 10f);
    actor.heal(healthRecovery);
    actor.removeItemFromInventory(this);
    return actor + " restores the health by " + healthRecovery + " points.";
  }

  @Override
  public String buy(Actor actor) {
    actor.addItemToInventory(new HealingVial());
    return actor + " had purchased a " + this;
  }

  @Override
  public String sell(Actor actor) {
    actor.removeItemFromInventory(this);
    return actor + " had sold a " + this;
  }

  @Override
  public int getSellPrice() {
    int price = 35;
    int luck = 10;
    if (random.nextInt(100) < luck){
      return price * 2;
    }
    return price;
  }
}
