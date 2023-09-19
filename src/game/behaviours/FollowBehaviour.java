package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;

public class FollowBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location actorLocation = map.locationOf(actor);

        for (Exit exit1 : actorLocation.getExits()) {
            Location firstDestination = exit1.getDestination();
            // actor's exit contains a player so prioritize attack behaviour
            if (firstDestination.containsAnActor() && firstDestination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                return null;
            }
            for (Exit exit2 : firstDestination.getExits()) {
                Location secondDestination = exit2.getDestination();
                // follow player if one block away from actor
                if (secondDestination.containsAnActor() && secondDestination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)
                     && firstDestination.canActorEnter(actor)) {

                    return new MoveActorAction(firstDestination,exit1.getName());
                }


            }
        }
        return null;
    }

    private double distance(Location actorLocation, Location playerLocation){
        return Math.sqrt(Math.pow(actorLocation.x() - playerLocation.x(),2) + Math.pow(actorLocation.y() - playerLocation.y(),2));
    }

}
