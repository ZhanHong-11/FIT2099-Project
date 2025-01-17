package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.skills.Skill;
import game.weapons.SkillWeapon;

/**
 * A subclass of Action that represents activating a skill action. Activate skill action is an
 * action that allows an actor to activate the skill of a skill weapon.
 *
 * @see Action
 */
public class ActivateSkillAction extends Action {

  /**
   * The weapon that has skill to activate
   */
  private SkillWeapon skillWeapon;
  /**
   * The skill to activate
   */
  private Skill skill;
  /**
   * The target to be attacked. This only apply for skill that can attack other actor.
   */
  private Actor target;
  /**
   * The direction of the target. This only apply for skill that can attack other actor.
   */
  private String direction;

  /**
   * Constructs a new activate skill action with the given actor and weapon.
   *
   * @param skillWeapon The weapon that has skill to activate
   * @param skill       The skill to activate
   */
  public ActivateSkillAction(SkillWeapon skillWeapon, Skill skill) {
    this.skillWeapon = skillWeapon;
    this.skill = skill;
  }

  /**
   * Constructs a new activate skill action with the given actor, weapon, target and direction. This
   * constructor is used for skill that can attack other actor.
   *
   * @param skillWeapon The weapon that has skill to activate
   * @param skill       The skill to activate
   * @param target      The target to be attacked
   * @param direction   The direction of the target
   */
  public ActivateSkillAction(SkillWeapon skillWeapon, Skill skill, Actor target, String direction) {
    this.skillWeapon = skillWeapon;
    this.skill = skill;
    this.target = target;
    this.direction = direction;
  }

  /**
   * Executes the activating skill action and returns a string that describes what happened.
   *
   * @param actor The actor who performs the action
   * @param map   The game map that contains the actor
   * @return the action description to be displayed on the menu
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    if (target == null) {
      return this.skill.activateSkill(actor, this.skillWeapon);
    } else {
      return this.skill.activateSkill(actor, this.skillWeapon, this.target, map, this.direction);
    }
  }

  /**
   * Returns a string that describes the activation of the skill to the menu.
   *
   * @param actor The actor performing the action.
   * @return the action description to be displayed on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    String result = actor + " activates " + this.skill + " on the " + this.skillWeapon;

    if (target == null) {
      return result;
    } else {
      return result + " and attack " + target + " at " + direction;
    }
  }
}
