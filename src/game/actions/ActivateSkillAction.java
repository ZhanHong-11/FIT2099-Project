package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.SkillWeapon;

/**
 * A subclass of Action that represents activating a skill action. Activate skill action is an
 * action that allows an actor to activate the skill of a skill weapon.
 *
 * @see Action
 */
public class ActivateSkillAction extends Action {

  /**
   * The actor who activate the skill
   */
  private final Actor player;
  /**
   * The weapon that has skill to activate
   */
  private final SkillWeapon skillWeapon;

  /**
   * Constructs a new activate skill action with the given actor and weapon.
   *
   * @param player      The actor who activate the skill
   * @param skillWeapon The weapon that has skill to activate
   */
  public ActivateSkillAction(Actor player, SkillWeapon skillWeapon) {
    this.player = player;
    this.skillWeapon = skillWeapon;
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
    int staminaCostPercent = skillWeapon.activateSkill();
    int staminaCost = Math.round(
        player.getAttributeMaximum(BaseActorAttributes.STAMINA) * staminaCostPercent / 100f);
    if (player.getAttribute(BaseActorAttributes.STAMINA) < staminaCost) {
      return actor + " has insufficient stamina.";
    }
    player.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE,
        staminaCost);
    return actor + " " + skillWeapon.getSkillDescription();
  }

  /**
   * Returns a string that describes activating the skill action in a menu.
   *
   * @param actor The actor performing the action.
   * @return the action description to be displayed on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " activates " + this.skillWeapon.getSkill() + " on the " + this.skillWeapon;
  }
}
