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
 * A class that represents puddle.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Puddle extends Ground implements Consumable {
    public Puddle() {
        super('~');
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (location.containsAnActor() && location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new ConsumeAction(this, menuDescription()));
        }
        return actions;
    }

    @Override
    public String consume(Actor actor) {
        int healthRecovery = 1;
        actor.heal(healthRecovery);
        actor.modifyAttribute(
            BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, Math.round(0.01f * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)));
        return actor + " drank water from the puddle. Health Increased by 1 and Stamina Increased by 1%";
    }

    private String menuDescription() {
        return "drinks water from the puddle";
    }
}
