package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.UnlockAction;

/**
 * A subclass of Ground that implements Unlockable interface. A locked gate is a ground that can be
 * unlocked by an unlock action, and allows an actor to travel to another map.
 *
 * @see Ground
 * @see Unlockable
 * @see UnlockAction
 */
public class LockedGate extends Ground implements Unlockable {

  /**
   * A boolean flag that indicates whether the gate is locked or not
   */
  private boolean isLocked;
  /**
   * An action that allows an actor to travel to another map through the gate
   */
  private Action travelAction;

  /**
   * Constructs a new locked gate with the default display character and state.
   */
  public LockedGate() {
    super('=');
    this.isLocked = true;
  }

  /**
   * Constructs a new locked gate with the given travel action and the default display character and
   * state.
   *
   * @param travelAction The action that allows an actor to travel to another map through the gate
   */
  public LockedGate(Action travelAction) {
    super('=');
    this.isLocked = true;
    this.travelAction = travelAction;
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
   * a key. If the gate is unlocked, the ActionList contains the travel action that allows an actor
   * to travel to another map through the gate.
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
      actions.add(this.travelAction);
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

}
