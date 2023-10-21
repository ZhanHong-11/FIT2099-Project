package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import java.util.ArrayList;
import java.util.Random;

/**
 * A subclass of Actor that represents a blacksmith who can speak to the player.
 * A blacksmith is a npc that can upgrade healing vials, refreshing flasks,
 * broadswords and great knives.
 *
 * @see Actor
 * @see Speakable
 */
public class Blacksmith extends Actor implements Speakable {

  /**
   * Constructs a new blacksmith.
   */
  public Blacksmith() {
    super("Blacksmith", 'B', 999);
    this.addCapability(Status.NEUTRAL);
    this.addCapability(Ability.CRAFTING);

  }

  /**
   * The allowable actions that the blacksmith provide.
   *
   * @param direction a string representing direction
   * @param map the game map
   * @param otherActor the other actor that will see the possible action
   * @return actions
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = super.allowableActions(otherActor, direction, map);
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
      actions.add(new SpeakAction(this));
    }
    return actions;
  }

  /**
   * Speak method of the blacksmith
   * @param listener the other actor which will listen to the blacksmith
   * @return a string representing a monologue
   */
  @Override
  public String speak(Actor listener) {
    Random random = new Random();
    ArrayList<String> monologues = new ArrayList<>();

    monologues.add("I used to be an adventurer like you, but then .... Nevermind, let’s get back to smithing.");
    monologues.add("It’s dangerous to go alone. Take my creation with you on your adventure!");
    monologues.add("Ah, it’s you. Let’s get back to make your weapons stronger.");

    if (listener.hasCapability(Status.ABXERVYER_KILLER)){
      monologues.add("Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.");

    }
    else{
      monologues.add("Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!");

    }
    if (listener.hasCapability(Ability.STAB_STEP)){
      monologues.add("Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.");
    }

    int index = random.nextInt(monologues.size());
    return monologues.get(index);
  }

  /**
   * The blacksmith cannot move around the map.
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }
}
