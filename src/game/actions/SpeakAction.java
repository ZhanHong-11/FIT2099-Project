package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Speakable;

public class SpeakAction extends Action {
  private Speakable speaker;
  private String monologue;
  public SpeakAction(Speakable speaker, String monologue){
    this.speaker = speaker;
    this.monologue = monologue;
  }
  @Override
  public String execute(Actor actor, GameMap map) {
    return this.speaker + " to " + actor + ": " + this.monologue;
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " listens to the " + this.speaker;
  }
}
