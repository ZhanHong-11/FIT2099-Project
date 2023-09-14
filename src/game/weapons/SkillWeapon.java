package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ActivateSkillAction;
import game.skills.Skill;

/**
 * Class representing a weapon that has skill to be activated.
 */
public abstract class SkillWeapon extends WeaponItem {

  /**
   * Skill of the weapon
   */
  private final Skill skill;
  /**
   * The time left for the skill to be still activating
   */
  private int skillCountdown;

  /**
   * Constructor.
   *
   * @param name        name of the item
   * @param displayChar character to use for display when item is on the ground
   * @param damage      amount of damage this weapon does
   * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
   * @param hitRate     the probability/chance to hit the target.
   * @param skill       skill of the weapon
   */
  public SkillWeapon(String name, char displayChar, int damage, String verb, int hitRate,
      Skill skill) {
    super(name, displayChar, damage, verb, hitRate);
    this.skill = skill;
  }

  /**
   * Return the skill of the weapon
   *
   * @return the skill of the weapon
   */
  public Skill getSkill() {
    return this.skill;
  }

  /**
   * Get a description of the skill of the weapon. This is for showing a description after the
   * player activated the skill of the weapon
   *
   * @return description of the skill of the weapon
   */
  public String getSkillDescription() {
    return this.skill.skillDescription();
  }

  /**
   * Return the time left for the activating skill in turn
   *
   * @return the time left for the activating skill in turn
   */
  public int getSkillCountdown() {
    return this.skillCountdown;
  }

  /**
   * Activates the skill of the weapon and returns the stamina percentage required to use it.
   *
   * @return The percentage of stamina required to use the skill
   */
  public int activateSkill() {
    this.skillCountdown = skill.getSkillDuration();
    return skill.getSkillStaminaPercent();
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
    if (this.skillCountdown > 0) {
      this.skillCountdown--;
    }
  }

  /**
   * Returns an ActionList that contains a ActivateSkillAction that allows an actor to activate the
   * skill of the broadsword.
   *
   * @param owner the actor that owns the weapon
   * @return an unmodifiable list of Actions
   */
  @Override
  public ActionList allowableActions(Actor owner) {
    ActionList actions = new ActionList();
    actions.add(new ActivateSkillAction(owner, this));
    return actions;
  }

}
