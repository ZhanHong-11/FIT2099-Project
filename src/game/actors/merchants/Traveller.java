package game.actors.merchants;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.weapons.Broadsword;
import game.weapons.GreatKnife;
import java.util.Random;

/**
 * A subclass of Merchant that represents a traveller. A traveller is a merchant that can sell
 * healing vials, refreshing flasks, broadswords and great knives.
 *
 * @see Merchant
 */
public class Traveller extends Merchant {

  /**
   * The base healing vial buy price of the traveller
   */
  private static final int BASE_HEALING_VIAL_BUY_PRICE = 100;
  /**
   * The base refreshing flask buy price of the traveller
   */
  private static final int BASE_REFRESHING_FLASK_BUY_PRICE = 75;
  /**
   * The base broadsword buy price of the traveller
   */
  private static final int BASE_BROADSWORD_BUY_PRICE = 250;
  /**
   * The base great knife buy price of the traveller
   */
  private static final int BASE_GREAT_KNIFE_BUY_PRICE = 300;
  private Random random = new Random();

  /**
   * Constructs a new traveller.
   */
  public Traveller() {
    super("Traveller", 'ඞ');
  }

  /**
   * Creates buyActions for the items that are sold by the traveller. The traveller can declare
   * their own selling price.
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = super.allowableActions(otherActor, direction, map);
    if (random.nextInt(100) < 25) {
      actions.add(new BuyAction(new HealingVial(), Math.round(BASE_HEALING_VIAL_BUY_PRICE * 1.5f)));
    } else {
      actions.add(new BuyAction(new HealingVial(), BASE_HEALING_VIAL_BUY_PRICE));
    }

    if (random.nextInt(100) < 10) {
      actions.add(
          new BuyAction(new RefreshingFlask(), Math.round(BASE_REFRESHING_FLASK_BUY_PRICE * 0.8f)));
    } else {
      actions.add(new BuyAction(new RefreshingFlask(), BASE_REFRESHING_FLASK_BUY_PRICE));
    }

    if (random.nextInt(100) < 5) {
      actions.add(new BuyAction(new Broadsword(), BASE_BROADSWORD_BUY_PRICE, false));
    } else {
      actions.add(new BuyAction(new Broadsword(), BASE_BROADSWORD_BUY_PRICE));
    }

    if (random.nextInt(100) < 5) {
      actions.add(new BuyAction(new GreatKnife(), BASE_GREAT_KNIFE_BUY_PRICE * 3));
    } else {
      actions.add(new BuyAction(new GreatKnife(), BASE_GREAT_KNIFE_BUY_PRICE));
    }

    return actions;
  }
}
