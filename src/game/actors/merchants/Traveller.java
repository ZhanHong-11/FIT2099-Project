package game.actors.merchants;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;
import game.capabilities.Ability;
import game.monologues.Speakable;
import game.capabilities.Status;
import java.util.ArrayList;
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
public class Traveller extends Merchant implements Speakable {

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

    String chosenMonologue = this.speak(otherActor);
    actions.add(new SpeakAction(this,chosenMonologue));

    return actions;
  }

  @Override
  public String speak(Actor listener) {
    Random random = new Random();
    ArrayList<String> monologues = new ArrayList<>();

    if (listener.hasCapability(Status.NEUTRAL)){
      monologues.add("Of course, I will never give you up, valuable customer!");
      monologues.add("I promise I will never let you down with the quality of the items that I sell.");
      monologues.add("You can always find me here. I'm never gonna run around and desert you, dear customer!");
      monologues.add("I'm never gonna make you cry with unfair prices.");
      monologues.add("Trust is essential in this business. I promise I’m never gonna say goodbye to a valuable customer like you.");
      monologues.add("Don't worry, I’m never gonna tell a lie and hurt you.");

      if (listener.hasCapability(Ability.HAS_HAMMER)){
        monologues.add("Ooh, that’s a fascinating weapon you got there. I will pay a good price for it. You wouldn't get this price from any other guy.");

      }

      if (!listener.hasCapability(Status.ABXERVYER_KILLER)){
        monologues.add("You know the rules of this world, and so do I. Each area is ruled by a lord. Defeat the lord of this area, Abxervyer, and you may proceed to the next area.");

      }

      if (listener.hasCapability(Ability.HAS_HAMMER) && listener.hasCapability(Status.ABXERVYER_KILLER)){
        monologues.add("Congratulations on defeating the lord of this area. I noticed you still hold on to that hammer. Why don’t you sell it to me? We've known each other for so long. I can tell you probably don’t need that weapon any longer.");
      }
    }
    int index = random.nextInt(monologues.size());
    return monologues.get(index);
  }
}
