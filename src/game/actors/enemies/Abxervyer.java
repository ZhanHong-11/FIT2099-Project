package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.FollowBehaviour;
import game.capabilities.Status;
import game.grounds.LockedGate;
import game.items.Runes;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Abxervyer extends Enemy{

  private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 80;
  private static final int BASE_INTRINSIC_HIT_RATE = 25;
  private static final String BASE_WEAPON_VERB = "batters";
  public static final int BASE_HIT_POINTS = 2000;
  public static final int BASE_RUNES_DROP_AMOUNT = 5000;

  private Map<Integer, Behaviour> behaviours = new HashMap<>();
  private Random random = new Random();

  public Abxervyer() {
    super("Abxervyer, the Forest Watcher", 'Y', BASE_HIT_POINTS);

  }

  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB, BASE_INTRINSIC_HIT_RATE);
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    for (Exit exit : map.locationOf(this).getExits()) {
      Location firstDestination = exit.getDestination();
      for (Exit exitTwo : firstDestination.getExits()) {
        Location secondDestination = exitTwo.getDestination();
        if (secondDestination.containsAnActor() && secondDestination.getActor().hasCapability(
            Status.HOSTILE_TO_ENEMY)) {
          this.behaviours.put(500, new FollowBehaviour(secondDestination.getActor()));
        }
      }
    }
    for (Behaviour behaviour : behaviours.values()) {
      Action action = behaviour.getAction(this, map);
      if (action != null) {
        return action;
      }
    }
    return new DoNothingAction();
  }

  public void drop(GameMap map) {
    Location location = map.locationOf(this);
    // drop 5000 runes onto ground upon death
    map.at(location.x(), location.y()).addItem(new Runes(BASE_RUNES_DROP_AMOUNT));

  }
  @Override
  public String unconscious(Actor actor, GameMap map) {
  drop(map);
  return super.unconscious(actor,map);

  }






}
