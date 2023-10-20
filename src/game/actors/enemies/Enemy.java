package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.behaviours.WanderBehaviour;
import game.actions.AttackAction;
import game.dream.Resettable;
import game.items.Rune;
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
public abstract class Enemy extends Actor implements Droppable, Resettable {

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
    this.behaviours.put(1, new AttackBehaviour());
    this.addCapability(Status.DANGER);
  }

  protected void setBehaviour(int priority, Behaviour behaviour) {
    this.behaviours.put(priority, behaviour);
  }

  /**
   * At each turn, select a valid action to perform. If the enemy has the capability of
   * Ability.FOLLOW, it will add a follow behaviour to the enemy when a nearby actor which is
   * hostile to the enemy is detected.
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
    if (this.hasCapability(Ability.FOLLOW)) {
      addFollowBehaviour(map);
    }
    for (Behaviour behaviour : behaviours.values()) {
      Action action = behaviour.getAction(this, map);
      if (action != null) {
        return action;
      }
    }
    return new DoNothingAction();
  }

  private void addFollowBehaviour(GameMap map) {
    for (Exit exit : map.locationOf(this).getExits()) {
      Location firstDestination = exit.getDestination();
      for (Exit exitTwo : firstDestination.getExits()) {
        Location secondDestination = exitTwo.getDestination();
        if (secondDestination.containsAnActor() && secondDestination.getActor()
            .hasCapability(Status.HOSTILE_TO_ENEMY)) {
          this.behaviours.put(500, new FollowBehaviour(secondDestination.getActor()));
        }
      }
    }
  }

  /**
   * Any player or actor that has the Status.HOSTILE_TO_ENEMY capability can attack this actor
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

  protected int getDropRuneAmount() {
    return 0;
  }

  /**
   * Drops an item when the enemy is killed. The implementation of this method should specify what
   * item to drop, the probability and where to drop it.
   *
   * @param location The location where the enemy is located.
   */
  @Override
  public void drop(Location location) {
    location.addItem(new Rune(getDropRuneAmount()));
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
    drop(map.locationOf(this));
    return super.unconscious(actor, map);
  }

  @Override
  public void reset(GameMap map) {
    map.removeActor(this);
  }
}
