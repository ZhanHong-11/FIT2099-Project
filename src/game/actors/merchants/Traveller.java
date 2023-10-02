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

public class Traveller extends Merchant {
  private static final int BASE_HEALING_VIAL_PRICE = 100;
  private static final int BASE_REFRESHING_FLASK_PRICE = 75;
  private static final int BASE_BROADSWORD_PRICE = 250;
  private static final int BASE_GREAT_KNIFE_PRICE = 300;
  private Random random = new Random();

  public Traveller() {
    super("Traveller", 'ඞ');
  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = super.allowableActions(otherActor, direction, map);
    if (random.nextInt(100) < 25){
      actions.add(new BuyAction(new HealingVial(), Math.round(BASE_HEALING_VIAL_PRICE * 1.5f)));
    }
    else {
      actions.add(new BuyAction(new HealingVial(), BASE_HEALING_VIAL_PRICE));
    }

    if (random.nextInt(100) < 10){
      actions.add(new BuyAction(new RefreshingFlask(), Math.round(BASE_REFRESHING_FLASK_PRICE * 0.8f)));
    }
    else {
      actions.add(new BuyAction(new RefreshingFlask(), BASE_REFRESHING_FLASK_PRICE));
    }

    if (random.nextInt(100) < 5){
      actions.add(new BuyAction(new Broadsword(), BASE_BROADSWORD_PRICE, false));
    }
    else {
      actions.add(new BuyAction(new Broadsword(), BASE_BROADSWORD_PRICE));
    }

    if (random.nextInt(100) < 5){
      actions.add(new BuyAction(new GreatKnife(), BASE_GREAT_KNIFE_PRICE * 3));
    }
    else {
      actions.add(new BuyAction(new GreatKnife(), BASE_GREAT_KNIFE_PRICE));
    }

    return actions;
  }
}
