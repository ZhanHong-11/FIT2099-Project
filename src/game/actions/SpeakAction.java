package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npc.Speakable;

public class SpeakAction extends Action {
  private Speakable speaker;
  public SpeakAction(Speakable speaker){
    this.speaker = speaker;
  }
  @Override
  public String execute(Actor actor, GameMap map) {
    return this.speaker + " to " + actor + ": " + this.speaker.speak(actor);
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " listens to the " + this.speaker;
  }
}
