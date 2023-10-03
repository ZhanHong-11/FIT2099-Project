package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;

/**
 * A subclass of Behaviour that represents follow behaviour. A follow behaviour is a behaviour
 * that cause the enemy to follow the player who is hostile to them and near to them.
 * The enemy that starts following won't stop following until either the target is unconscious
 * or the enemy is unconscious
 *
 * @author Soo Zhan Hong
 * @author Alvin Andrean
 * @author Marco Gotama
 * @author Vihanga Mihiranga Malaviarachchi
 * @see Behaviour
 */
public class FollowBehaviour implements Behaviour {
    private Actor target;

    /**
     * Constructs a new Follow Behaviour
     * @param target
     */
    public FollowBehaviour(Actor target) {
        this.target = target;
    }

    /**
     * Returns a MoveActorAction that allows the actor to move closer to a hostile target, or null if none is
     * available. The method checks all the exits from the actor's location and finds any actors who
     * have the Status.HOSTILE_TO_ENEMY capability.
     *
     * @param actor The actor who performs the move action
     * @param map   The game map that contains the actor and the target
     * @return A MoveActorAction that allows the actor to follow a hostile target, or null if none is
     * available
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor)) {
            return null;
        }

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = distance(here, there);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, there);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }

        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the second location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
