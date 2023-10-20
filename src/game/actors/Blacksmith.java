package game.actors;

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

public class Blacksmith extends Actor implements Speakable {

  public Blacksmith() {
    super("Blacksmith", 'B', 999);
    this.addCapability(Status.NEUTRAL);
    this.addCapability(Ability.CRAFTING);

  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    actions.add(new SpeakAction(this));
    return actions;
  }

  @Override
  public String speak(Actor listener) {
    Random random = new Random();
    ArrayList<String> monologues = new ArrayList<>();

    if (listener.hasCapability(Status.HOSTILE_TO_ENEMY)){
      monologues.add("I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing.");
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
    }
    int index = random.nextInt(monologues.size());
    return monologues.get(index);
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }
}
