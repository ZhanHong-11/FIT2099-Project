package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.dream.DreamCapable;
import game.dream.Resettable;
import game.capabilities.Status;
import game.display.FancyMessage;
import game.items.Rune;
import java.util.ArrayList;

/**
 * Class representing the Player.
 *
 * @author Adrian Kristanto Modified by: Soo Zhan Hong
 * @see Actor
 * @see DreamCapable
 * @see Droppable Created by:
 */
public class Player extends Actor implements DreamCapable, Droppable {

  /**
   * The location where the player will respawn.
   */
  private Location respawnLocation;
  /**
   * A list of resettable objects.
   */
  private ArrayList<Resettable> resettables = new ArrayList<>();

  /**
   * Constructor.
   *
   * @param name            Name to call the player in the UI
   * @param displayChar     Character to represent the player in the UI
   * @param hitPoints       Player's starting number of hitpoints
   * @param stamina         Player's starting stamina
   * @param respawnLocation The location where the player will respawn.
   */
  public Player(String name, char displayChar, int hitPoints, int stamina,
      Location respawnLocation) {
    super(name, displayChar, hitPoints);
    this.respawnLocation = respawnLocation;
    this.addCapability(Status.HOSTILE_TO_ENEMY);
    this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));
  }

  /**
   * Determines and returns the action to be performed by the player in their current turn.
   *
   * @param actions    A list of possible actions the player can perform during this turn.
   * @param lastAction The last action that the player took.
   * @param map        The game map, representing the current game state.
   * @param display    The display used to present information to the player.
   * @return The action the player has chosen to take this turn.
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    // Handle multi-turn Actions
    if (lastAction.getNextAction() != null) {
      return lastAction.getNextAction();
    }

    // Recover the player's stamina
    tickPlayer();

    // return/print the console menu
    display.println(getPlayerAttribute());
    Menu menu = new Menu(actions);
    return menu.showMenu(this, display);
  }

  /**
   * Returns an IntrinsicWeapon that represents the attack of the player without weapon. The attack
   * has a damage of 15 and a hit rate of 80%.
   *
   * @return An IntrinsicWeapon that represents the attack
   */
  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(15, "strikes", 80);
  }

  /**
   * Inform the player the passage of time. For example: recover player's stamina for 1% every
   * turn.
   */
  private void tickPlayer() {
    int staminaRecovery = Math.round(this.getAttributeMaximum(BaseActorAttributes.STAMINA) / 100f);
    this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,
        staminaRecovery);
  }

  /**
   * Handles player's death by another actor. The player will respawn in the starting position. The
   * wallet balance of the players will be dropped on the death location as runes.
   *
   * @param actor an actor representing the killer
   * @param map   the Game Map
   */
  @Override
  public String unconscious(Actor actor, GameMap map) {
    Location deadLocation = map.locationOf(this);
    respawn(map);
    drop(deadLocation);
    return "\n" + FancyMessage.YOU_DIED + "\n" + this + " met their demise in the hand of " + actor
        + "\n" + this + " is respawned in the Abandoned Village!";
  }

  /**
   * Handles player's natural death (Example: stepping on the void). The player will respawn in the
   * starting position. The wallet balance of the players will be dropped on the death location as
   * runes. If the player died on the void, the runes will still be dropped on the void. This
   * basically means that the runes cannot be picked up again.
   *
   * @param map the Game Map
   */
  @Override
  public String unconscious(GameMap map) {
    Location deadLocation = map.locationOf(this);
    respawn(map);
    drop(deadLocation);
    return "\n" + FancyMessage.YOU_DIED + "\n" + this + " ceased to exist!\n" + this
        + " is respawned in the Abandoned Village!";
  }

  @Override
  public String toString() {
    return name + " (" +
        this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
        this.getAttributeMaximum(BaseActorAttributes.HEALTH) + ", " +
        this.getAttribute(BaseActorAttributes.STAMINA) + "/" +
        this.getAttributeMaximum(BaseActorAttributes.STAMINA) +
        ")";
  }

  /**
   * Return the current attributes of the player to be displayed on the console, e.g. health,
   * stamina, etc.
   *
   * @return The current attributes of the player.
   */
  public String getPlayerAttribute() {
    return this.name + "\n" +
        "HP: " +
        this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
        this.getAttributeMaximum(BaseActorAttributes.HEALTH) + "\n" +
        "Stamina: " +
        this.getAttribute(BaseActorAttributes.STAMINA) + "/" +
        this.getAttributeMaximum(BaseActorAttributes.STAMINA) + "\n" +
        "Wallet: $" +
        this.getBalance();
  }

  /**
   * Adds the resettable object to the resettable list
   *
   * @param resettable a resettable object
   */
  @Override
  public void subscribe(Resettable resettable) {
    this.resettables.add(resettable);
  }

  /**
   * Remove the resettable object from the resettable list
   *
   * @param resettable a resettable object
   */
  @Override
  public void unsubscribe(Resettable resettable) {
    this.resettables.remove(resettable);
  }

  /**
   * Respawns the player in the starting position, and resets the resettable objects. Remove the
   * reset status from the items in the player's inventory as those items should not be affected.
   *
   * @param map The Game Map
   */
  @Override
  public void respawn(GameMap map) {
    map.moveActor(this, this.respawnLocation);
    this.heal(getAttributeMaximum(BaseActorAttributes.HEALTH) - getAttribute(BaseActorAttributes.HEALTH));
    this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,
        getAttributeMaximum(BaseActorAttributes.STAMINA) - getAttribute(BaseActorAttributes.STAMINA));
    for (Resettable resettable : this.resettables) {
      resettable.reset(map);
    }
    for (Item item : this.getItemInventory()) {
      item.removeCapability(Status.RESET);
    }
  }

  /**
   * Drop items when the player is killed. The player will drop the wallet balance as runes.
   *
   * @param location the location of the actor.
   */
  @Override
  public void drop(Location location) {
    int walletBalance = getBalance();
    deductBalance(walletBalance);
    location.addItem(new Rune(walletBalance, this));
  }
}
