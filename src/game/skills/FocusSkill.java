package game.skills;

/**
 * A subclass of Skill that represents a focus skill. A focus skill increases the damage and hit
 * rate of the user for a short duration. It requires a certain percentage of stamina to use.
 *
 * @author Soo Zhan Hong
 * @see Skill
 */
public class FocusSkill extends Skill {

  /**
   * Constructs a new skill named "Focus" with the default attributes.
   */
  public FocusSkill() {
    super("Focus", 20, 5, 10, 90);
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
