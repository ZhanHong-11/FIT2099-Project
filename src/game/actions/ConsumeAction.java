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
   * The menu description, for objects that are not a type of item
   */
  private String description;

  /**
   * Constructs a new consume action with the given consumable item.
   *
   * @param consumableItem The consumable item that is to be consumed by the player
   */
  public ConsumeAction(Consumable consumableItem) {
    this.consumableItem = consumableItem;
  }

  /**
   * Constructs a new consume action with the given consumable item and description. This
   * constructor is useful for those items that have a different menu description. (especially for
   * non-item type)
   *
   * @param consumableItem The consumable item that is to be consumed by the player
   * @param description    The action description to be display on the menu
   */
  public ConsumeAction(Consumable consumableItem, String description) {
    this.consumableItem = consumableItem;
    this.description = description;
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
    return this.consumableItem.consume(actor);
  }

  /**
   * Returns a string that describes the consume action in a menu.
   *
   * @param actor The actor performing the action.
   * @return the action description to be displayed on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    if (description == null) {
      return actor + " consumes " + consumableItem;
    } else {
      return actor + " " + description;
    }
  }
}
