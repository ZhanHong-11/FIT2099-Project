package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ActivateSkillAction;
import game.actions.SellAction;
import game.capabilities.Ability;
import game.items.Buyable;
import game.items.Sellable;
import game.skills.FocusSkill;

/**
 * A subclass of SkillWeapon that represents a broadsword that have skill. A broadsword is a
 * powerful weapon that can slash enemies with high damage. It also has a special skill that can
 * increase its damage and hit rate for a short duration. This weapon can be bought and sold.
 *
 * @author Soo Zhan Hong
 * @see SkillWeapon
 * @see Buyable
 * @see Sellable
 */
public class Broadsword extends SkillWeapon implements Buyable, Sellable {

  /**
   * The default hit rate of the broadsword
   */
  private static final int BASE_HIT_RATE = 80;
  /**
   * The default sell price of the broadsword
   */
  private static final int BASE_SELL_PRICE = 100;

  /**
   * Constructs a new broadsword with the default attributes and skill.
   */
  public Broadsword() {
    super("Broadsword", '1', 110, "slashes", BASE_HIT_RATE);
    this.setSkill(new FocusSkill());
  }

  /**
   * Resets the hit rate and damage multiplier of the broadsword to its default value.
   */
  @Override
  public void resetWeapon() {
    this.updateHitRate(BASE_HIT_RATE);
    this.updateDamageMultiplier(1.0f);
    getSkill().deactivateSkill(this);
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
    ActionList actions = super.allowableActions(otherActor, location);
    if (otherActor.hasCapability(Ability.TRADING)) {
      actions.add(new SellAction(this));
    }
    return actions;
  }

  /**
   * Returns an ActionList that contains an ActivateSkillAction that allows an actor to activate the
   * skill of the broadsword.
   *
   * @param owner the actor that owns the broadsword
   * @return an unmodifiable list of Actions
   */
  @Override
  public ActionList allowableActions(Actor owner) {
    ActionList actionList = super.allowableActions(owner);
    actionList.add(new ActivateSkillAction(this, getSkill()));
    return actionList;
  }

  /**
   * Reset the weapon when the weapon is picked up by an actor.
   *
   * @param actor the actor that picks up the broadsword
   * @return a PickUpAction
   */
  @Override
  public PickUpAction getPickUpAction(Actor actor) {
    resetWeapon();
    return super.getPickUpAction(actor);
  }

  /**
   * Skill should only last for some turns. Decrement the number of turns left for the activated
   * skill
   *
   * @param currentLocation The location of the actor carrying this weapon.
   * @param actor           The actor carrying this weapon.
   */
  @Override
  public void tick(Location currentLocation, Actor actor) {
    getSkill().tickSkill(this);
  }

  /**
   * Buy the broadsword and add it to the inventory of the actor.
   *
   * @return A string describing the buying action
   */
  @Override
  public String buy(Actor actor) {
    actor.addItemToInventory(new Broadsword());
    return actor + " had purchased a " + this;
  }

  /**
   * Sell the broadsword and remove it from the inventory of the actor.
   *
   * @return A string describing the selling action
   */
  @Override
  public String sell(Actor actor) {
    actor.removeItemFromInventory(this);
    return actor + " had sold a " + this;
  }

  /**
   * Returns the sell price of the broadsword.
   *
   * @return the sell price of the broadsword
   */
  @Override
  public int getSellPrice() {
    return BASE_SELL_PRICE;
  }
}
