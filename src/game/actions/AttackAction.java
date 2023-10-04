package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

public class AttackAction extends Action {

  /**
   * The Actor that is to be attacked
   */
  private Actor target;
  /**
   * The direction the attack.
   */
  private String direction;
  /**
   * Weapon used for the attack
   */
  private Weapon weapon;
  /**
   * Damage dealt by the attack
   */
  private int damage;
  /**
   * Random number generator
   */
  private Random rand = new Random();

  /**
   * Constructor with weapon and damage
   *
   * @param target    the Actor to attack
   * @param direction the direction where the attack should be performed (only used for display
   *                  purposes)
   * @param weapon    the weapon used for the attack
   * @param damage    the damage dealt by the attack
   */
  public AttackAction(Actor target, String direction, Weapon weapon, int damage) {
    this.target = target;
    this.direction = direction;
    this.weapon = weapon;
    this.damage = damage;
  }

  /**
   * Constructor with intrinsic weapon as default
   *
   * @param target    the actor to attack
   * @param direction the direction where the attack should be performed (only used for display
   *                  purposes)
   */
  public AttackAction(Actor target, String direction) {
    this.target = target;
    this.direction = direction;
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    if (weapon == null) {
      weapon = actor.getIntrinsicWeapon();
      this.damage = weapon.damage();
    }

    if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
      return actor + " misses " + target + ".";
    }

    String result = actor + " " + weapon.verb() + " " + target + " for " + this.damage + " damage.";
    target.hurt(this.damage);
    if (!target.isConscious()) {
      result += "\n" + target.unconscious(actor, map);
    }

    return result;
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon
        : "Intrinsic Weapon");
  }
}
