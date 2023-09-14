package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Unlockable;
import game.items.Key;
import java.util.List;

/**
 * A subclass of Action that represents an unlock action. An unlock action is an action that allows
 * an actor to unlock an unlockable object, such as a door or a chest, with a key.
 *
 * @see Action
 * @see Unlockable
 */
public class UnlockAction extends Action {

  /**
   * The unlockable object that is to be unlocked by this action
   */
  private final Unlockable sealedObject;

  /**
   * Constructs a new unlock action with the given unlockable object.
   *
   * @param sealedObject The unlockable object that is to be unlocked by this action
   */
  public UnlockAction(Unlockable sealedObject) {
    this.sealedObject = sealedObject;
  }

  /**
   * Executes the unlock action and returns a string that describes what happened. The method checks
   * if the actor has a key in their inventory and calls the unlocked method of the unlockable
   * object if they do. The method also returns a message that indicates whether the object is
   * unlocked or locked.
   *
   * @param actor The actor who performs the unlock action
   * @param map   The game map that contains the actor
   * @return the action description to be displayed on the menu
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    List<Item> items = actor.getItemInventory();
    for (Item item : items) {
      if (item instanceof Key) {
        sealedObject.unlocked();
        return "The " + sealedObject + " is unlocked.";
      }
    }
    return "The " + sealedObject + " is locked.";
  }

  /**
   * Returns a string that describes the unlock action in a menu.
   *
   * @param actor The actor performing the action.
   * @return the action description to be displayed on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " opens the " + this.sealedObject.toString();
  }
}
