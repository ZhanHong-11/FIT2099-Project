package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * A subclass of Action that represents a consume action. A consume action is an action that allows
 * an actor to consume a consumable item and gain some recovery or effects.
 *
 * @see Action
 * @see Consumable
 */
public class ConsumeAction extends Action {

  /**
   * The consumable item that to be consumed by the player
   */
  private final Consumable consumableItem;

  /**
   * Constructs a new consume action with the given consumable item.
   *
   * @param consumableItem The consumable item that is to be consumed by the player
   */
  public ConsumeAction(Consumable consumableItem) {
    this.consumableItem = consumableItem;
  }

  /**
   * Executes the consume action and returns a string that describes the action executed.
   *
   * @param actor The actor who performs the consume action
   * @param map   The game map that contains the actor
   * @return the action description to be displayed on the menu
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    int recoveryPoints = this.consumableItem.consume(actor);
    return actor + " restores the " + consumableItem.getAttribute() + " by " + recoveryPoints
        + " points.";
  }

  /**
   * Returns a string that describes the consume action in a menu.
   *
   * @param actor The actor performing the action.
   * @return the action description to be displayed on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " consumes " + consumableItem;
  }
}
