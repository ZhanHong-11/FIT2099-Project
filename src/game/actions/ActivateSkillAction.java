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
  private final SkillWeapon skillWeapon;
  private final Skill skill;

  /**
   * Constructs a new activate skill action with the given actor and weapon.
   *
   * @param skillWeapon The weapon that has skill to activate
   */
  public ActivateSkillAction(SkillWeapon skillWeapon, Skill skill) {
    this.skillWeapon = skillWeapon;
    this.skill = skill;
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
    return this.skill.activateSkill(actor, this.skillWeapon);
  }

  /**
   * Returns a string that describes activating the skill action in a menu.
   *
   * @param actor The actor performing the action.
   * @return the action description to be displayed on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " activates " + this.skill + " on the " + this.skillWeapon;
  }
}
