package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologue;
import game.Speakable;
import game.capabilities.Ability;
import game.capabilities.Status;
import java.util.ArrayList;

public class Blacksmith extends Actor implements Speakable {

  private ArrayList<Monologue> monologues = new ArrayList<>();

  public Blacksmith() {
    super("Blacksmith", 'B', 999);
    this.addCapability(Status.NEUTRAL);
    this.addCapability(Ability.CRAFTING);


  }
  @Override
  public void speak(){
    this.monologues.add(new Monologue(this,"I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing.", this.name));
    this.monologues.add(new Monologue(this,"It’s dangerous to go alone. Take my creation with you on your adventure!", this.name));
    this.monologues.add(new Monologue(this, "Ah, it’s you. Let’s get back to make your weapons stronger.", this.name));

  }
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }
}
