package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.UnlockAction;
import game.dream.DreamCapable;
import game.dream.Resettable;

import java.util.HashMap;
import java.util.Map;

/**
 * A subclass of Ground that implements Unlockable interface. A locked gate is a ground that can be
 * unlocked by an unlock action, and allows an actor to travel to another map.
 *
 * @see Ground
 * @see Unlockable
 * @see Resettable
 */
public class LockedGate extends Ground implements Unlockable, Resettable {

  /**
   * A boolean flag that indicates whether the gate is locked or not
   */
  private boolean isLocked;

  /**
   * A map containing actions that allows an actor to travel to another map through the gate
   */
  private Map<String, MoveActorAction> travelActionList;

  /**
   * The travel action that allows an actor to travel to another map through the gate
   */
  private MoveActorAction travelAction;

  /**
   * The Dream Capable object (player)
   */
  private DreamCapable dreamCapable;

  /**
   * Constructs a new locked gate with the given travel actions list and the default display
   * character and state. This constructor is specifically used for the locked gate that has
   * multiple destination.
   *
   * @param travelActions The travel actions that allows an actor to travel to another map through
   *                      the gate (multiple travel action)
   * @param dreamCapable  the Dream Capable Object (player)
   */
  public LockedGate(Map<String, MoveActorAction> travelActions, DreamCapable dreamCapable) {
    super('=');
    this.isLocked = true;
    this.travelActionList = new HashMap<>(travelActions);
    this.dreamCapable = dreamCapable;
    this.dreamCapable.subscribe(this);
  }

  /**
   * Constructs a new locked gate with the given travel action and the default display character and
   * state. This constructor is specifically used for the locked gate that has only one
   * destination.
   *
   * @param travelAction The action that allows an actor to travel to another map through the gate
   * @param dreamCapable the Dream Capable Object (player)
   */
  public LockedGate(MoveActorAction travelAction, DreamCapable dreamCapable) {
    super('=');
    this.isLocked = true;
    this.travelAction = travelAction;
    this.dreamCapable = dreamCapable;
    this.dreamCapable.subscribe(this);
  }

  /**
   * Returns whether an actor can enter the gate or not. An actor can only enter the gate if it is
   * unlocked.
   *
   * @param actor The actor who tries to enter the gate
   * @return true if the gate is unlocked, false otherwise
   */
  @Override
  public boolean canActorEnter(Actor actor) {
    return !this.isLocked;
  }

  /**
   * Returns an ActionList that contains the actions that can be performed on the gate. If the gate
   * is locked, the ActionList contains an UnlockAction that allows an actor to unlock the gate with
   * a key. If the gate is unlocked, the ActionList contains the travel action/actions that allows
   * an actor to travel to another map through the gate. e
   *
   * @param actor     the Actor acting
   * @param location  The location of the gate
   * @param direction The direction of the gate from the actor
   * @return an ActionList that contains the actions that can be performed on the gate
   */
  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    ActionList actions = new ActionList();
    if (isLocked) {
      actions.add(new UnlockAction(this));
    } else {
      if (this.travelAction != null) {
        actions.add(this.travelAction);
      } else {
        if (!this.travelActionList.isEmpty()) {
          for (Action travel : travelActionList.values()) {
            if (travel != null) {
              actions.add(travel);
            }
          }
        }
      }
    }
    return actions;
  }

  /**
   * Unlocks the gate and changes its state to unlocked.
   */
  @Override
  public void unlocked() {
    this.isLocked = !this.isLocked;
  }

  /**
   * Returns a string that represents the gate.
   *
   * @return A string that represents the gate
   */
  @Override
  public String toString() {
    return "locked gate";
  }

  /**
   * Returns a string that represents the gate.
   */
  @Override
  public void reset(GameMap map) {
    this.isLocked = true;
  }
}
