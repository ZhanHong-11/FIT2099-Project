package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * An abstract class that represents a merchant. A merchant is an actor that can trade with the
 * player.
 *
 * @see Actor
 */
public abstract class Merchant extends Actor {

  /**
   * Constructs a new merchant with the given name, display character, and hit points. The merchant
   * will have a neutral status which cannot be attacked by any actor. It also has a trading ability
   * which allows the player to sell item to him
   *
   * @param name        The name of the merchant
   * @param displayChar The character to display for the merchant
   */
  public Merchant(String name, char displayChar) {
    super(name, displayChar, 999);
    this.addCapability(Status.NEUTRAL);
    this.addCapability(Ability.TRADING);
  }

  /**
   * The merchant cannot move around the map.
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }
}
