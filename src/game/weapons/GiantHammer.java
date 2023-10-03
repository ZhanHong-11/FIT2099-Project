package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ActivateSkillAction;
import game.actions.SellAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.items.Sellable;
import game.skills.GreatSlamSkill;

public class GiantHammer extends SkillWeapon implements Sellable {
  private static final int BASE_SELL_PRICE = 250;

  public GiantHammer() {
    super("Giant Hammer", 'P', 160, "slam", 90);
    this.setSkill(new GreatSlamSkill());
  }

  @Override
  public ActionList allowableActions(Actor otherActor, Location location) {
    ActionList actions = super.allowableActions(otherActor, location);
    if (!otherActor.hasCapability(Status.NEUTRAL)){
      actions.add(new ActivateSkillAction(this, getSkill(), otherActor, location.toString()));
    }
    if (otherActor.hasCapability(Ability.TRADING)){
      actions.add(new SellAction(this));
    }
    return actions;
  }

  @Override
  public String sell(Actor actor) {
    actor.removeItemFromInventory(this);
    return actor + " had sold the " + this;
  }

  @Override
  public int getSellPrice() {
    return BASE_SELL_PRICE;
  }
}
