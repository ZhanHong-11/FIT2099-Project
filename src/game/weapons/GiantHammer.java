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
import game.skills.GreatSlamSkill;

/**
 * A subclass of SkillWeapon that represents a Giant Hammer that have skill. A giant hammer is a
 * powerful weapon that can slam the enemies with high damage. It also has a special skill that can
 * attack the surrounding enemies including the player. This weapon can be sold but cannot be
 * bought.
 *
 * @see SkillWeapon
 * @see Buyable
 * @see Sellable
 */

public class GiantHammer extends SkillWeapon implements Sellable {

  /**
   * The default sell price of the giant hammer
   */
  private static final int BASE_SELL_PRICE = 250;

  /**
   * Constructs a new giant hammer with the default attributes and skill.
   */
  public GiantHammer() {
    super("Giant Hammer", 'P', 160, "slam", 90);
    this.setSkill(new GreatSlamSkill());
  }

  /**
   * Returns an ActionList that contains an ActivateSkillAction that allows an actor to attack
   * another actor with the giant hammer's skill. If the other actor has a neutral status, it will
   * not add this action. This method also adds a SellAction if the other actor has a trading
   * capability.
   *
   * @param otherActor the other actor
   * @param location   the location of the other actor
   * @return a list of possible actions
   */
  @Override
  public ActionList allowableActions(Actor otherActor, Location location) {
    ActionList actions = super.allowableActions(otherActor, location);
    if (!otherActor.hasCapability(Status.NEUTRAL)) {
      actions.add(new ActivateSkillAction(this, getSkill(), otherActor, location.toString()));
    }
    if (otherActor.hasCapability(Ability.TRADING)) {
      actions.add(new SellAction(this));
    }
    return actions;
  }

  /**
   * Sell the giant hammer
   *
   * @param actor the actor that sells the giant hammer
   * @return a string describing the result of the selling of the giant hammer
   */
  @Override
  public String sell(Actor actor) {
    actor.removeItemFromInventory(this);
    return actor + " had sold the " + this;
  }

  /**
   * Returns the sell price of the giant hammer
   *
   * @return the sell price of the giant hammer
   */
  @Override
  public int getSellPrice() {
    return BASE_SELL_PRICE;
  }
}
