package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.capabilities.Status;
import game.items.Consumable;

/**
 * A class that represents puddle. Extending to the Ground class, and implements the Consumable
 * Interface.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Alvin Andrean
 * @author Soo Zhan Hong
 * @see Ground
 * @see Consumable
 */
public class Puddle extends Ground implements Consumable {

  /**
   * Constructs a new blood berry with the default attributes.
   */
  public Puddle() {
    super('~');
  }

  /**
   * List of allowable actions that blood-berry can perform to the current actor
   *
   * @param actor    the actor that's standing in the puddle
   * @param location the location of the puddle
   * @return an ActionList that contain the ConsumeAction
   */
  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    ActionList actions = super.allowableActions(actor, location, direction);
    if (location.containsAnActor() && location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
      actions.add(new ConsumeAction(this, menuDescription()));
    }
    return actions;
  }

  /**
   * Consumes the puddle and returns a message with the amount of health gained by the actor.
   * drinking from the puddle increases the actor's health and stamina by 1 points
   *
   * @param actor The actor who consumes the water in the puddle
   * @return a string representing a message with the amount of health increased by the actor
   */
  @Override
  public String consume(Actor actor) {
    int healthRecovery = 1;
    actor.heal(healthRecovery);
    actor.modifyAttribute(
        BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,
        Math.round(0.01f * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)));
    return actor
        + " drank water from the puddle. Health Increased by 1 and Stamina Increased by 1%";
  }

  /**
   * The description of the action
   *
   * @return a String of the action's description.
   */
  private String menuDescription() {
    return "drinks water from the puddle";
  }
}
