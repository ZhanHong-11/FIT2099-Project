package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.grounds.LockedGate;
import game.items.Runes;

public class Abxervyer extends Enemy{
  private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 80;
  private static final int BASE_INTRINSIC_HIT_RATE = 25;
  private static final String BASE_WEAPON_VERB = "batters";
  public static final int BASE_HIT_POINTS = 2000;
  public static final int BASE_RUNES_DROP_AMOUNT = 5000;
  private LockedGate gate;

  public Abxervyer(LockedGate gate) {
    super("Abxervyer, the Forest Watcher", 'Y', BASE_HIT_POINTS);
    this.gate = gate;
    this.addCapability(Ability.IMMUNE_TO_VOID);
    this.addCapability(Ability.FOLLOW);
    this.addCapability(Ability.CONTROL_WEATHER);
  }

  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB, BASE_INTRINSIC_HIT_RATE);
  }

  @Override
  public void drop(GameMap map) {
    Location location = map.locationOf(this);
    map.at(location.x(), location.y()).addItem(new Runes(BASE_RUNES_DROP_AMOUNT));
    map.at(location.x(), location.y()).setGround(gate);
  }

  @Override
  public String unconscious(Actor actor, GameMap map) {
    return super.unconscious(actor, map) + "\n" + this + " is dead!!";
  }
}
