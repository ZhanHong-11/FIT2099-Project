package game.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.weapons.SkillWeapon;

/**
 * A subclass of Skill that represents a focus skill. A focus skill increases the damage and hit
 * rate of the user for a short duration. It requires a certain percentage of stamina to use.
 *
 * @author Soo Zhan Hong
 * @see Skill
 */
public class FocusSkill extends Skill {
  /**
   * The duration of the skill in turns
   */
  private final int skillDuration;
  /**
   * The percentage of damage multiplier increase when using the skill
   */
  private final int skillDamageMultiplierPercent;
  /**
   * The probability of hitting the target when using the skill
   */
  private final int hitRate;

  /**
   * Constructs a new skill named "Focus" with the default attributes.
   */
  public FocusSkill() {
    super("Focus", 20);
    this.skillDuration = 5;
    this.skillDamageMultiplierPercent = 10;
    this.hitRate = 90;
  }

  @Override
  public void tickSkill(SkillWeapon skillWeapon) {
    super.tickSkill(skillWeapon);
    if (getSkillCountdown() == 0){
      skillWeapon.updateDamageMultiplier(1.0f);
    }
  }

  @Override
  public String activateSkill(Actor player, SkillWeapon weapon) {
    int staminaCost = Math.round(
        player.getAttributeMaximum(BaseActorAttributes.STAMINA) * getSkillStaminaPercent() / 100f);
    if (player.getAttribute(BaseActorAttributes.STAMINA) < staminaCost) {
      return player + " has insufficient stamina.";
    }
    player.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE,
        staminaCost);

    setSkillCountdown(this.skillDuration);
    weapon.updateHitRate(this.hitRate);
    weapon.increaseDamageMultiplier(this.skillDamageMultiplierPercent / 100f);
    return player + " " + this.skillDescription();
  }

  @Override
  public void deactivateSkill(SkillWeapon weapon) {
    weapon.updateDamageMultiplier(1.0f);
    setSkillCountdown(0);
  }

  /**
   * Return a description of Focus skill. This is for showing a description after the player
   * activated this skill
   *
   * @return description of the skill
   */
  @Override
  public String skillDescription() {
    return "takes a deep breath and focuses all their might!!";
  }
}
