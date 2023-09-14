package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.AttackBehaviour;
import game.capabilities.Status;
import game.behaviours.WanderBehaviour;
import game.actions.AttackAction;
import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class that extends Actor and implements Droppable interface. An enemy is NPC that can
 * attack other actors who are hostile to them and wander around the map. An enemy has a capability
 * of Status.DANGER which cause them unable to enter the floor. Enemy can drop items when killed.
 *
 * @see Actor
 * @see Droppable
 */
public abstract class Enemy extends Actor implements Droppable {

  /**
   * A map of behaviours that the enemy can perform, with key as the priority
   */
  private Map<Integer, Behaviour> behaviours = new HashMap<>();

  /**
   * Constructs a new enemy with the given name, display character, and hit points. The enemy also
   * has two default behaviours: wander and attack, with different priorities.
   *
   * @param name        The name of the enemy
   * @param displayChar The character to display for the enemy
   * @param hitPoints   The hit points of the enemy
   */
  public Enemy(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    this.behaviours.put(999, new WanderBehaviour());
    this.behaviours.put(1, new AttackBehaviour());
    this.addCapability(Status.DANGER);
  }

  /**
   * At each turn, select a valid action to perform.
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in
   *                   conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return the valid action that can be performed in that iteration or null if no valid action is
   * found
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    for (Behaviour behaviour : behaviours.values()) {
      Action action = behaviour.getAction(this, map);
      if (action != null) {
        return action;
      }
    }
    return new DoNothingAction();
  }

  /**
   * The enemy can attack any player or actor that has the Status.HOSTILE_TO_ENEMY capability
   *
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return an ActionList that contains an AttackAction to allow the enemy to attack the player
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
      actions.add(new AttackAction(this, direction));
    }
    return actions;
  }

  /**
   * Drops an item when the enemy is killed. The implementation of this method should specify what
   * item to drop, the probability and where to drop it.
   *
   * @param map The game map where the enemy is located.
   */
  @Override
  public void drop(GameMap map) {
  }

  /**
   * When the enemy is defeated, it may drop some item.
   *
   * @param actor the perpetrator
   * @param map   where the actor fell unconscious
   * @return a string describing what happened when the actor is unconscious
   */
  @Override
  public String unconscious(Actor actor, GameMap map) {
    drop(map);
    return super.unconscious(actor, map);
  }
}
