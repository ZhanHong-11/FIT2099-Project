package game.skills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.weapons.SkillWeapon;
import java.util.ArrayList;
import java.util.Random;

/**
 * A subclass of Skill that represents a Stab and Step skill. This skill can be used to attack an
 * enemy and then move away. This skill requires a certain percentage of stamina to use.
 *
 * @see Skill
 */
public class StabStepSkill extends Skill {

  private Random random = new Random();

  /**
   * Constructs a new skill named "Stab and Step" with the default attributes.
   */
  public StabStepSkill() {
    super("Stab and step", 25);
  }

  /**
   * Activate the skill. This method will attack the target and then move to a random location.
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

    String result = actor + " " + skillDescription();
    Action attackAction = new AttackAction(target, direction, weapon, weapon.damage());
    result += "\n" + attackAction.execute(actor, map);

    Action moveAction = findMoveAction(actor, map);
    if (moveAction != null) {
      return result + "\n" + moveAction.execute(actor, map);

    } else {
      return result + "\n" + actor + " fails to step away";
    }

  }

  /**
   * Find a random location that the actor can move to.
   *
   * @param actor The actor that uses the skill
   * @param map   The map that the actor is currently in
   * @return A MoveActorAction that move the actor to a random location
   */
  private Action findMoveAction(Actor actor, GameMap map) {
    ArrayList<Action> actions = new ArrayList<>();

    for (Exit exit : map.locationOf(actor).getExits()) {
      Location destination = exit.getDestination();
      if (destination.canActorEnter(actor)) {
        actions.add(new MoveActorAction(destination, " to " + destination));
      }
    }

    if (!actions.isEmpty()) {
      return actions.get(random.nextInt(actions.size()));
    } else {
      return null;
    }

  }

  /**
   * Returns a string describing the skill.
   *
   * @return A string describing the skill
   */
  @Override
  public String skillDescription() {
    return "perform stab and step!";
  }
}
