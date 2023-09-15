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
        Location targetLocation = null;

        for (Exit exit1 : actorLocation.getExits()) {
            Location firstDestination = exit1.getDestination();
            if (firstDestination.containsAnActor() && firstDestination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                targetLocation = firstDestination;
                break;
            }

            for (Exit exit2 : firstDestination.getExits()) {
                Location secondDestination = exit2.getDestination();
                if (exit1.getName().equals(exit2.getName()) && secondDestination.containsAnActor() && secondDestination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    targetLocation = secondDestination;
                    break;
                }
            }

            if (targetLocation != null) {
                break;
            }
        }

        if (targetLocation == null) {
            return null;
        }

        int currentDistance = distance(actorLocation, targetLocation);

        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, targetLocation);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }

        return null;
    }

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
