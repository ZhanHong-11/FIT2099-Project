package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

public class DrinkWaterAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 1);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, Math.round(0.01f * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)));
        return actor + " drank water from the puddle. Health Increased by 1 and Stamina Increased by 1%";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from the puddle";
    }
}
