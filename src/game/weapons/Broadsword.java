package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.skills.FocusSkill;

/**
 * A subclass of SkillWeapon that represents a broadsword that have skill. A broadsword is a
 * powerful weapon that can slash enemies with high damage. It also has a special skill that can
 * increase its damage and hit rate for a short duration.
 *
 * @author Soo Zhan Hong
 * @see SkillWeapon
 */
public class Broadsword extends SkillWeapon {

  /**
   * The default hit rate of the broadsword
   */
  private final int defaultHitRate;

  /**
   * Constructs a new broadsword with the default attributes and skill.
   */
  public Broadsword() {
    super("Broadsword", '1', 110, "slashes", 80, new FocusSkill());
    this.defaultHitRate = 80;
  }

  /**
   * Activates the skill of the broadsword and returns the stamina percentage required to use it.
   *
   * @return The percentage of stamina required to use the skill
   */
  @Override
  public int activateSkill() {
    updateHitRate(getSkill().getHitRate());
    increaseDamageMultiplier(getSkill().getSkillDamageMultiplierPercent() / 100f);
    return super.activateSkill();
  }

  /**
   * Skill should only last for some turns. Decrement the number of turn left for the activated
   * skill. When skill duration is finished, update the damage multiplier back to the default
   * value.
   *
   * @param currentLocation The location of the actor carrying this weapon.
   * @param actor           The actor carrying this weapon.
   */
  @Override
  public void tick(Location currentLocation, Actor actor) {
    super.tick(currentLocation, actor);
    if (getSkillCountdown() == 0) {
      updateDamageMultiplier(1.0f);
    }
  }

  /**
   * Create and return an action to pick this Item up. Resets the hit rate of the broadsword to its
   * default value when picked up.
   *
   * @return A PickUpAction that allows picking up the broadsword
   */
  @Override
  public PickUpAction getPickUpAction(Actor actor) {
    updateHitRate(this.defaultHitRate);
    return super.getPickUpAction(actor);
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
    ActionList actions = new ActionList();
    actions.add(new AttackAction(otherActor, location.toString(), this));
    return actions;
  }
}
