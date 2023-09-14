package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * A subclass of Item that implements Consumable interface. A refreshing flask is a consumable item
 * that can restore a fraction of the actor's stamina.
 *
 * @author Soo Zhan Hong
 * @see Item
 * @see Consumable
 */
public class RefreshingFlask extends Item implements Consumable {

  /**
   * The type of attribute that is affected by consuming the refreshing flask
   */
  private final static String ATTRIBUTE = "stamina";

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
   * Consumes the refreshing flask and returns the amount of stamina gained by the actor. The
   * refreshing flask restores 20% of the actor's maximum stamina and is removed after being
   * consumed.
   *
   * @param actor The actor who consumes the refreshing flask
   * @return The amount of stamina gained by the actor
   */
  @Override
  public int consume(Actor actor) {
    int staminaRecovery = Math.round(actor.getAttributeMaximum(BaseActorAttributes.STAMINA) / 5f);
    actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,
        staminaRecovery);
    actor.removeItemFromInventory(this);
    return staminaRecovery;
  }

  /**
   * Returns the type of attribute that is affected by consuming the refreshing flask.
   *
   * @return The type of attribute that is affected by consuming the refreshing flask
   */
  @Override
  public String getAttribute() {
    return RefreshingFlask.ATTRIBUTE;
  }
}
