package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.monologues.Monologue;
import game.actions.SpeakAction;
import game.monologues.Speakable;
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
    Monologue chosenMonologue = this.speak(otherActor);
    actions.add(new SpeakAction(this,chosenMonologue));
    return actions;
  }

  @Override
  public Monologue speak(Actor listener) {
    Random random = new Random();
    ArrayList<Monologue> monologues = new ArrayList<>();

    if (listener.hasCapability(Status.HOSTILE_TO_ENEMY)){
      monologues.add(new Monologue(listener,"I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing.",this.name));
      monologues.add(new Monologue(listener,"It’s dangerous to go alone. Take my creation with you on your adventure!",this.name));
      monologues.add(new Monologue(listener,"Ah, it’s you. Let’s get back to make your weapons stronger.",this.name));

      if (listener.hasCapability(Status.ABXERVYER_KILLER)){
        monologues.add(new Monologue(listener,"Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.",this.name));

      }
      else{
        monologues.add(new Monologue(listener,"Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!",this.name));

      }
      if (listener.hasCapability(Ability.STAB_STEP)){
        monologues.add(new Monologue(listener,"Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.",this.name));
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
