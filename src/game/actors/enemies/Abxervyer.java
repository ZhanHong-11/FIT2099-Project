package game.actors.enemies;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.items.Runes;
import java.util.Random;

public class Abxervyer extends Enemy{
  private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 80;
  private static final int BASE_INTRINSIC_HIT_RATE = 25;
  private static final String BASE_WEAPON_VERB = "batters";
  public static final int BASE_HIT_POINTS = 2000;
  public static final int BASE_RUNES_DROP_AMOUNT = 5000;

  public Abxervyer() {
    super("Abxervyer, the Forest Watcher", 'Y', BASE_HIT_POINTS);
    this.addCapability(Ability.IMMUNE_TO_VOID);
    this.addCapability(Ability.FOLLOW);
  }

  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB, BASE_INTRINSIC_HIT_RATE);
  }

  @Override
  public void drop(GameMap map) {
    Location location = map.locationOf(this);
    map.at(location.x(), location.y()).addItem(new Runes(BASE_RUNES_DROP_AMOUNT));
  }
}
