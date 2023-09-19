package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.BuyAction;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.capabilities.Ability;
import game.items.Buyable;
import game.items.HealingVial;
import game.items.Sellable;
import game.skills.FocusSkill;
import game.skills.Skill;
import java.util.Random;

/**
 * A subclass of SkillWeapon that represents a broadsword that have skill. A broadsword is a
 * powerful weapon that can slash enemies with high damage. It also has a special skill that can
 * increase its damage and hit rate for a short duration.
 *
 * @author Soo Zhan Hong
 * @see SkillWeapon
 */
public class Broadsword extends SkillWeapon implements Buyable, Sellable {

  /**
   * The default hit rate of the broadsword
   */
  private static final int BASE_HIT_RATE = 80;

  /**
   * Constructs a new broadsword with the default attributes and skill.
   */
  public Broadsword() {
    super("Broadsword", '1', 110, "slashes", BASE_HIT_RATE);
    this.addSkill(new FocusSkill());
  }

  @Override
  public void resetWeapon() {
    this.updateHitRate(BASE_HIT_RATE);
    this.updateDamageMultiplier(1.0f);
    for (Skill skill: getSkills()){
      skill.deactivateSkill(this);
    }
  }

  /**
   * Returns an ActionList that contains an AttackAction that allows an actor to attack another
   * actor with the broadsword.
   *
   * @param otherActor the other actor
   * @param location   the location of the other actor
   * @return an unmodifiable list of Actions
   */
  @Override
  public ActionList allowableActions(Actor otherActor, Location location) {
    ActionList actions = new ActionList();
    if (!otherActor.hasCapability(Ability.TRADING)){
      actions.add(new AttackAction(otherActor, location.toString(), this));
    }
    else {
      actions.add(new SellAction(this));
    }
    return actions;
  }

  @Override
  public ActionList allowableActions(Actor owner) {
    ActionList actionList = super.allowableActions(owner);
    if (owner.hasCapability(Ability.TRADING)){
      actionList.add(new BuyAction(this));
    }
    return actionList;
  }

  @Override
  public String buy(Actor actor) {
    Random random = new Random();
    int luck = 5;
    if (random.nextInt(100) < luck){
      return actor + " had been scammed!";
    }
    actor.addItemToInventory(new Broadsword());
    return actor + " had purchased a " + this;
  }

  @Override
  public int getBuyPrice() {
    int price = 250;
    return price;
  }

  @Override
  public String sell(Actor actor) {
    actor.removeItemFromInventory(this);
    return actor + " had sold a " + this;
  }

  @Override
  public int getSellPrice() {
    int price = 100;
    return price;
  }
}
