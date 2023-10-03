package game.skills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.weapons.SkillWeapon;
import java.util.ArrayList;
import java.util.Random;

public class StabStepSkill extends Skill {
  private Random random = new Random();

  public StabStepSkill() {
    super("Stab and step", 25);
  }

  @Override
  public String activateSkill(Actor actor, SkillWeapon weapon, Actor target, GameMap map, String direction) {
    String result = actor + " " + skillDescription();
    Action attackAction = new AttackAction(target, direction, weapon, weapon.damage());
    result += "\n" + attackAction.execute(actor, map);

    Action moveAction = findMoveAction(actor, map);
    if (moveAction != null){
      return result + "\n" + moveAction.execute(actor, map);
    }
    else {
      return result + "\n" + actor + " fails to step away";
    }

  }

  private Action findMoveAction(Actor actor, GameMap map){
    ArrayList<Action> actions = new ArrayList<>();

    for (Exit exit : map.locationOf(actor).getExits()) {
      Location destination = exit.getDestination();
      if (destination.canActorEnter(actor)) {
        actions.add(new MoveActorAction(destination, " to " + destination));
      }
    }

    if (!actions.isEmpty()) {
      return actions.get(random.nextInt(actions.size()));
    }
    else {
      return null;
    }

  }

  @Override
  public String skillDescription() {
    return "perform stab and step!";
  }
}
