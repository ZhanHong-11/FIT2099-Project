package game.actors.merchants;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Ability;
import game.capabilities.Status;

public abstract class Merchant extends Actor {

  public Merchant(String name, char displayChar) {
    super(name, displayChar, 999);
    this.addCapability(Status.NEUTRAL);
    this.addCapability(Ability.TRADING);
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }
}
