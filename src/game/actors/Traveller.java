package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Ability;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.weapons.Broadsword;

public class Traveller extends Actor {

  public Traveller() {
    super("Traveller", 'ඞ', 1);
    this.addCapability(Ability.TRADING);
    this.addItemToInventory(new HealingVial());
    this.addItemToInventory(new RefreshingFlask());
    this.addItemToInventory(new Broadsword());
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    for (Item item: getItemInventory()){
      actions.add(item.allowableActions(this));
    }
    return actions;
  }
}
