package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ActivateSkillAction;
import game.actions.SellAction;
import game.actions.UpgradeAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.items.Buyable;
import game.items.Sellable;
import game.items.Upgradable;
import game.skills.StabStepSkill;

import java.util.Random;

/**
 * A subclass of SkillWeapon that represents a great knife that have skill. A great knife is a
 * powerful weapon that can stab enemies with high damage. It also has a special skill that can
 * attack a nearby enemy and move to a random location. This weapon can be bought and sold.
 *
 * @see SkillWeapon
 * @see Buyable
 * @see Sellable
 */
public class GreatKnife extends SkillWeapon implements Buyable, Sellable, Upgradable {

  /**
   * The base hit rate of the great knife
   */
  private static final int BASE_HIT_RATE = 70;
  /**
   * The default sell price of the great knife
   */
  private static final int BASE_SELL_PRICE = 175;
  /**
   * The default upgrade cost of the great knife
   */
  private static final int BASE_UPGRADE_COST = 2000;
  private Random random = new Random();

  /**
   * Constructs a new great knife with the default attributes and skill.
   */
  public GreatKnife() {
    super("Great Knife", '>', 75, "stab", BASE_HIT_RATE);
    this.addCapability(Ability.STAB_STEP);
    this.setSkill(new StabStepSkill());
  }

  /**
   * Returns an ActionList that contains an ActivateSkillAction that allows an actor to attack
   * another actor with the great knife's skill. If the other actor has a neutral status, it will
   * not add this action. This method also adds a SellAction if the other actor has a trading
   * capability. This method also adds an UpgradeAction if the other actor has a crafting ability.
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
    if (otherActor.hasCapability(Ability.CRAFTING)) {
      actions.add(new UpgradeAction(this));
    }
    return actions;
  }

  /**
   * Buys the item and adds it to the actor's inventory.
   *
   * @param actor The actor who buys the item
   * @return String describing the result of the purchase
   */
  @Override
  public String buy(Actor actor) {
    actor.addItemToInventory(new GreatKnife());
    return actor + " had purchased the " + this;
  }

  /**
   * Sells the item and removes it from the actor's inventory.
   *
   * @param actor The actor who sells the item
   * @return String describing the result of the sale
   */
  @Override
  public String sell(Actor actor) {
    int luck = 10;
    actor.removeItemFromInventory(this);

    if (random.nextInt(100) < luck) {
      if (actor.getBalance() > getSellPrice() * 2) {
        actor.deductBalance(getSellPrice() * 2);
      } else {
        actor.deductBalance(actor.getBalance());
      }
      return actor + " had been scammed!";
    }
    return actor + " had sold a " + this;
  }

  /**
   * Returns the sell price of the great knife
   *
   * @return the sell price of the great knife
   */
  @Override
  public int getSellPrice() {
    return BASE_SELL_PRICE;
  }

  /**
   * Upgrade the great knife by increasing its hit rate.
   *
   * @return A string describing the upgrading action
   */
  @Override
  public String upgrade() {
    int percent = 1;
    this.increaseHitRate(Math.round(BASE_HIT_RATE * percent / 100f));
    return this + " has been upgraded!\nThe hit rate of " + this + " has been increased by "
        + percent + "%.";
  }

  /**
   * Returns the upgrade cost of the great knife.
   *
   * @return the upgrade cost of the great knife
   */
  @Override
  public int getUpgradeCost() {
    return BASE_UPGRADE_COST;
  }
}
