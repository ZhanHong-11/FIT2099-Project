package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;
import game.dream.DreamCapable;
import game.actions.ConsumeAction;
import game.dream.Resettable;

/**
 * A subclass of Item which implements the Consumable Interface, representing a consumable Item
 * It also implements the Resettable Interface, representing a resettable item
 *
 * @author Alvin Andrean
 * @see Item
 * @see Consumable
 * @see Resettable
 */
public class Rune extends Item implements Consumable, Resettable {

  /**
   * The amount of the rune
   */
  private final int value;

  /**
   * the Dream Capable Object (player)
   */
  private final DreamCapable dreamCapable;

  /**
   * Constructs a new rune with the default attributes and the value of the rune.
   * @param value the value of the runes
   * @param dreamCapable the Dream Capable Object (player)
   */
  public Rune(int value, DreamCapable dreamCapable) {
    super("Rune", '$', true);
    this.value = value;
    this.dreamCapable = dreamCapable;
    this.dreamCapable.subscribe(this);
  }

  /**
   * List of allowable actions that the runes can perform to the current actor
   *
   * @param owner the actor that owns the item
   * @return an ActionList that contain the ConsumeAction
   */
  @Override
  public ActionList allowableActions(Actor owner) {
    ActionList actionList = new ActionList();
    actionList.add(new ConsumeAction(this));
    return actionList;
  }

  /**
   * Consumes the runes and add the amount of the rune to the actor's balance
   *
   * @param actor The actor who consumes the rune
   * @return a string representing a message that the actor successfully consumed the runes with the
   * value/amount shown in the end.
   */
  @Override
  public String consume(Actor actor) {
    actor.addBalance(this.value);
    actor.removeItemFromInventory(this);
    return actor + " had consumed the runes and gained $" + this.value;
  }

  /**
   * Called every turn for each instance of this item, to execute actions
   * or modify its status based on its current location or state.
   *
   * If the item has the capability `Status.RESET`, it will be removed
   * from its current location.
   *
   * @param currentLocation The location where the item currently resides.
   */
  @Override
  public void tick(Location currentLocation) {
    super.tick(currentLocation);
    if (this.hasCapability(Status.RESET)) {
      currentLocation.removeItem(this);
    }
  }

  /**
   * Introduces a status change to the item, particularly adding the
   * `Status.RESET` capability.
   * This indicates that the item is flagged for a reset, which might
   * lead to its removal or other changes in subsequent turns or actions.
   *
   * @param map The game map, representing the current game state.
   *             Currently unused in this method but can be utilized for
   *             future extensions or modifications.
   */
  @Override
  public void reset(GameMap map) {
    this.addCapability(Status.RESET);
  }

}
