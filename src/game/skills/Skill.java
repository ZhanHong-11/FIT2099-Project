package game.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.SkillWeapon;

/**
 * An abstract class that represents a skill that can be used by a game character. Subclasses of
 * this class should implement the specific logic of how the skill is executed and what effects it
 * has.
 *
 * @author Soo Zhan Hong
 */
public abstract class Skill {

  /**
   * The name of the skill
   */
  private final String skillName;
  /**
   * The percentage of stamina required to use the skill
   */
  private final int skillStaminaPercent;
  /**
   * The countdown of the skill. Use for skills that can last for several turns
   */
  private int skillCountdown;

  /**
   * Constructs a new skill with the given attributes.
   *
   * @param skillName           The name of the skill
   * @param skillStaminaPercent The percentage of stamina required to use the skill
   */
  public Skill(String skillName, int skillStaminaPercent) {
    this.skillName = skillName;
    this.skillStaminaPercent = skillStaminaPercent;
    this.skillCountdown = 0;
  }

  /**
   * Decrease the stamina of the player based on the stamina required by the activated skill
   */
  protected void consumeStamina(Actor actor, int staminaReduction) {
    actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE,
        staminaReduction);
  }

  /**
   * Returns the percentage of stamina cost.
   *
   * @return The percentage of stamina cost
   */
  public int getSkillStaminaPercent() {
    return this.skillStaminaPercent;
  }

  /**
   * Returns the countdown of the skill.
   *
   * @return The countdown of the skill
   */
  public int getSkillCountdown() {
    return this.skillCountdown;
  }

  /**
   * Sets the countdown of the skill. Useful for skill that can lasts for several turns.
   *
   * @param skillCountdown The countdown of the skill
   */
  public void setSkillCountdown(int skillCountdown) {
    this.skillCountdown = skillCountdown;
  }

  /**
   * Countdown the skill. For skill that can lasts for several turns.
   */
  public void tickSkill(SkillWeapon weapon) {
    if (this.skillCountdown > 0) {
      this.skillCountdown--;
    }
    if (this.skillCountdown == 0) {
      deactivateSkill(weapon);
    }
  }

  /**
   * Activate the skill.
   *
   * @return The description after activating the skill
   */
  public String activateSkill(Actor actor, SkillWeapon weapon) {
    return "";
  }

  /**
   * Activate the skill. For skill that requires a target (an attack skill).
   *
   * @return The description after activating the skill
   */
  public String activateSkill(Actor actor, SkillWeapon weapon, Actor target, GameMap map,
      String direction) {
    return "";
  }

  /**
   * Deactivate the skill. For skill that can lasts for several turns. Basically remove the effect
   * and reset the countdown.
   */
  public void deactivateSkill(SkillWeapon weapon) {
  }

  /**
   * Return a description of the skill. This is for showing a description after the player activated
   * the skill
   *
   * @return description of the skill
   */
  public abstract String skillDescription();

  @Override
  public String toString() {
    return this.skillName + " Skill";
  }
}
