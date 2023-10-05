package game.skills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.capabilities.Status;
import game.weapons.SkillWeapon;

/**
 * A subclass of Skill that represents a Great Slam skill. This skill can be used to attack an enemy
 * and deal damage to the surrounding including the player. It requires a certain percentage of
 * stamina to use.
 *
 * @author Soo Zhan Hong
 * @see Skill
 */
public class GreatSlamSkill extends Skill {

  /**
   * Constructs a new skill named "Great Slam" with the default attributes.
   */
  public GreatSlamSkill() {
    super("Great Slam", 5);
  }

  /**
   * Activate the skill. This method will attack the target and deal damage to the surrounding
   * including the actor that perform this skill.
   *
   * @param actor     The actor that uses the skill
   * @param weapon    The weapon that has the skill
   * @param target    The target to be attacked by this skill
   * @param map       The map that the actor is currently in
   * @param direction The direction of the target from the actor
   * @return A string describing the result of the skill activation
   */
  @Override
  public String activateSkill(Actor actor, SkillWeapon weapon, Actor target, GameMap map,
      String direction) {
    int staminaCost = Math.round(
        actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * getSkillStaminaPercent() / 100f);
    if (actor.getAttribute(BaseActorAttributes.STAMINA) < staminaCost) {
      return actor + " has insufficient stamina.";
    }
    consumeStamina(actor, staminaCost);

    Location targetLocation = map.locationOf(target);

    String result = actor + " " + skillDescription();
    Action attackAction = new AttackAction(target, direction, weapon, weapon.damage());
    result += "\n" + attackAction.execute(actor, map);

    for (Exit exit : targetLocation.getExits()) {
      Location location = exit.getDestination();
      if (location.containsAnActor() && !location.getActor().hasCapability(Status.NEUTRAL)) {
        int damage = Math.round(weapon.damage() / 2f);
        Action attackAction2 = new AttackAction(location.getActor(), direction, weapon, damage);
        result += "\n" + attackAction2.execute(actor, map);
      }
    }
    return result;
  }

  /**
   * Returns a string describing the skill.
   *
   * @return A string describing the skill
   */
  @Override
  public String skillDescription() {
    return "slams the ground!";
  }
}
