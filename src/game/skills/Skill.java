package game.skills;

import edu.monash.fit2099.engine.actors.Actor;
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
  private int skillCountdown;

  /**
   * Constructs a new skill with the given attributes.
   *
   * @param skillName                    The name of the skill
   * @param skillStaminaPercent          The percentage of stamina required to use the skill
   */
  public Skill(String skillName, int skillStaminaPercent) {
    this.skillName = skillName;
    this.skillStaminaPercent = skillStaminaPercent;
    this.skillCountdown = 0;
  }

  /**
   * Returns the percentage of stamina cost.
   *
   * @return The percentage of stamina cost
   */
  public int getSkillStaminaPercent() {
    return this.skillStaminaPercent;
  }

  public int getSkillCountdown() {
    return this.skillCountdown;
  }

  public void setSkillCountdown(int skillCountdown) {
    this.skillCountdown = skillCountdown;
  }

  public void tickSkill(SkillWeapon weapon){
    if (this.skillCountdown > 0){
      this.skillCountdown--;
    }
    if (this.skillCountdown == 0){
      deactivateSkill(weapon);
    }
  }

  public String activateSkill(Actor actor, SkillWeapon weapon){
    return "";
  }

  public String activateSkill(Actor actor, SkillWeapon weapon, Actor target, GameMap map, String direction){
    return "";
  }

  public void deactivateSkill(SkillWeapon weapon){}

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
