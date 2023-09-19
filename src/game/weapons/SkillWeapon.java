package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ActivateSkillAction;
import game.actions.AttackAction;
import game.skills.Skill;
import java.util.ArrayList;

/**
 * Class representing a weapon that has skill to be activated.
 */
public abstract class SkillWeapon extends WeaponItem {

  /**
   * Skill of the weapon
   */
  private ArrayList<Skill> skills = new ArrayList<>();

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

  public void addSkill(Skill skill){
    this.skills.add(skill);
  }

  public ArrayList<Skill> getSkills() {
    return this.skills;
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
    for (Skill skill: skills){
      skill.tickSkill(this);
    }
  }

  @Override
  public PickUpAction getPickUpAction(Actor actor) {
    resetWeapon();
    return super.getPickUpAction(actor);
  }

  @Override
  public ActionList allowableActions(Actor owner) {
    ActionList actions = super.allowableActions(owner);
    for (Skill skill: skills){
      actions.add(new ActivateSkillAction(this, skill));
    }
    return actions;
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
    actions.add(new AttackAction(otherActor, location.toString(), this));
    return actions;
  }

  public void resetWeapon(){}
}
