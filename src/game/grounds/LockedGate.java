package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.UnlockAction;
import game.capabilities.Status;
import game.dream.DreamCapable;
import game.dream.Resettable;

import java.util.ArrayList;

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
  private ArrayList<MoveActorAction> travelActionList = new ArrayList<>();

  /**
   * The Dream Capable object (player)
   */
  private DreamCapable dreamCapable;

  /**
   * Constructs a new locked gate with the given travel actions and the default display character
   * and state.
   *
   * @param travelAction The travel action that allows an actor to travel to another map through the
   *                     gate (multiple travel action)
   * @param dreamCapable the Dream Capable Object (player)
   */
  public LockedGate(MoveActorAction travelAction, DreamCapable dreamCapable) {
    super('=');
    this.isLocked = true;
    this.travelActionList.add(travelAction);
    this.dreamCapable = dreamCapable;
    this.dreamCapable.subscribe(this);
  }

  /**
   * Returns whether an actor can enter the gate or not. An actor can only enter the gate if it is
   * unlocked.
   *
   * @param actor The actor who tries to enter the gate
   * @return false if the actor is an enemy, and true for the player if the gate is unlocked, false
   * otherwise
   */
  @Override
  public boolean canActorEnter(Actor actor) {
    if (actor.hasCapability(Status.DANGER)) {
      return false;
    }
    return !this.isLocked;
  }

  /**
   * Returns an ActionList that contains the actions that can be performed on the gate. If the gate
   * is locked, the ActionList contains an UnlockAction that allows an actor to unlock the gate with
   * a key. If the gate is unlocked, the ActionList contains the travel action/actions that allows
   * an actor to travel to another map through the gate.
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
      for (MoveActorAction travelAction : travelActionList) {
        actions.add(travelAction);
      }
    }
    return actions;
  }

  /**
   * Adds a travel action to the gate. This method allows the gate to have multiple destinations.
   *
   * @param travelAction The travel action that allows an actor to travel to another map through the
   *                     gate
   */
  public void addTravelAction(MoveActorAction travelAction) {
    this.travelActionList.add(travelAction);
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
