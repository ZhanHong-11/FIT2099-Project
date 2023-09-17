package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DrinkWaterAction;
import game.capabilities.Status;

/**
 * A class that represents puddle.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Puddle extends Ground {
    public Puddle() {
        super('~');
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new DrinkWaterAction());
        }
        return actions;
    }
}
