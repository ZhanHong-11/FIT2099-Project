package game.skills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.capabilities.Status;
import game.weapons.SkillWeapon;

public class GreatSlamSkill extends Skill{

  public GreatSlamSkill() {
    super("Great Slam", 5);
  }

  @Override
  public String activateSkill(Actor actor, SkillWeapon weapon, Actor target, GameMap map, String direction) {
    String result = actor + " " + skillDescription();
    Action attackAction = new AttackAction(target, direction, weapon);
    result += "\n" + attackAction.execute(actor, map);

    for (Exit exit: map.locationOf(target).getExits()){
      Location location = exit.getDestination();
      if (location.containsAnActor() && !location.getActor().hasCapability(Status.NEUTRAL)){
        int damage = Math.round(weapon.damage() / 2f);
        result += "\n" + location.getActor() + " is hurt by " + damage + " damage from the shockwave.";
        location.getActor().hurt(damage);
      }
    }
    return result;
  }

  @Override
  public String skillDescription() {
    return "slams the ground!";
  }
}
