package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ActivateSkillAction;
import game.actions.SellAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.items.Buyable;
import game.items.Sellable;
import game.skills.StabStepSkill;
import java.util.Random;

public class GreatKnife extends SkillWeapon implements Buyable, Sellable {
  private static final int BASE_SELL_PRICE = 175;
  private Random random = new Random();

  public GreatKnife() {
    super("Great Knife", '>', 75, "stab", 70);
    this.setSkill(new StabStepSkill());
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
  public String buy(Actor actor) {
    actor.addItemToInventory(new GreatKnife());
    return actor + " had purchased the " + this;
  }

  @Override
  public String sell(Actor actor) {
    int luck = 10;
    actor.removeItemFromInventory(this);

    if (random.nextInt(100) < luck){
      if (actor.getBalance() > getSellPrice() * 2){
        actor.deductBalance(getSellPrice() * 2);
      }
      else {
        actor.deductBalance(actor.getBalance());
      }
      return actor + " had been scammed!";
    }
    return actor + " had sold a " + this;
  }

  @Override
  public int getSellPrice() {
    return BASE_SELL_PRICE;
  }
}
