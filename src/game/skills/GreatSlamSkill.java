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
    Location targetLocation = map.locationOf(target);

    String result = actor + " " + skillDescription();
    Action attackAction = new AttackAction(target, direction, weapon, weapon.damage());
    result += "\n" + attackAction.execute(actor, map);

    for (Exit exit: targetLocation.getExits()){
      Location location = exit.getDestination();
      if (location.containsAnActor() && !location.getActor().hasCapability(Status.NEUTRAL)){
        int damage = Math.round(weapon.damage() / 2f);
        Action attackAction2 = new AttackAction(location.getActor(), direction, weapon, damage);
        result += "\n" + attackAction2.execute(actor, map);
      }
    }
    return result;
  }

  @Override
  public String skillDescription() {
    return "slams the ground!";
  }
}
