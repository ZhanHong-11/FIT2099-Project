package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.capabilities.Status;
import game.skills.Skill;

/**
 * Class representing a weapon that has skill to be activated.
 */
public abstract class SkillWeapon extends WeaponItem {

  /**
   * Skill of the weapon
   */
  private Skill skill;

  /**
   * Constructor.
   *
   * @param name        name of the item
   * @param displayChar character to use for display when item is on the ground
   * @param damage      amount of damage this weapon does
   * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
   * @param hitRate     the probability/chance to hit the target.
   */
  public SkillWeapon(String name, char displayChar, int damage, String verb, int hitRate) {
    super(name, displayChar, damage, verb, hitRate);
  }

  public void setSkill(Skill skill){
    this.skill = skill;
  }

  public Skill getSkill() {
    return this.skill;
  }

  /**
   * Returns an ActionList that contains an AttackAction that allows an actor to attack another
   * actor with the weapon.
   *
   * @param otherActor the other actor
   * @param location   the location of the other actor
   * @return an unmodifiable list of Actions
   */
  @Override
  public ActionList allowableActions(Actor otherActor, Location location) {
    ActionList actions = super.allowableActions(otherActor, location);
    if (!otherActor.hasCapability(Status.NEUTRAL)){
      actions.add(new AttackAction(otherActor, location.toString(), this, this.damage()));
    }
    return actions;
  }

  public void resetWeapon(){}
}
