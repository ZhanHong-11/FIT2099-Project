package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.monologues.Monologue;
import game.monologues.Speakable;

public class SpeakAction extends Action {
  private Speakable speakable;
  private Monologue monologue;
  public SpeakAction(Speakable speakable, Monologue monologue){
    this.speakable = speakable;
    this.monologue = monologue;
  }
  @Override
  public String execute(Actor actor, GameMap map) {
    return this.monologue.getSpeaker() + " to " + actor + ": " + this.monologue.getMonologue();
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " listens to the " + this.speakable;
  }
}
