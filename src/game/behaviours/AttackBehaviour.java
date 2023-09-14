package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;
import game.actions.AttackAction;
import java.util.ArrayList;
import java.util.Random;

/**
 * A subclass of Behaviour that represents an attack behaviour. An attack behaviour is a behaviour
 * that cause the enemy to attack the player who is hostile to them and near to them.
 *
 * @author Soo Zhan Hong
 * @see Behaviour
 */
public class AttackBehaviour implements Behaviour {

  private final Random random = new Random();

  /**
   * Returns an AttackAction that allows the actor to attack a hostile target, or null if none is
   * available. The method checks all the exits from the actor's location and finds any actors who
   * have the Status.HOSTILE_TO_ENEMY capability. If there are multiple targets, it randomly selects
   * one to attack.
   *
   * @param actor The actor who performs the attack
   * @param map   The game map that contains the actor and the target
   * @return An AttackAction that allows the actor to attack a hostile target, or null if none is
   * available
   */
  @Override
  public Action getAction(Actor actor, GameMap map) {
    ArrayList<Action> actions = new ArrayList<>();

    for (Exit exit : map.locationOf(actor).getExits()) {
      Location destination = exit.getDestination();
      if (destination.containsAnActor()) {
        Actor target = destination.getActor();
        if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
          actions.add(new AttackAction(target, destination.toString()));
        }
      }
    }

    if (!actions.isEmpty()) {
      return actions.get(random.nextInt(actions.size()));
    } else {
      return null;
    }
  }

}
