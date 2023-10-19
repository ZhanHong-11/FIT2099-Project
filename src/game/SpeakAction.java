package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class SpeakAction extends Action {
  private Speakable speakable;
  private String monologue;
  public  SpeakAction(Speakable speakable, String monologue){
    this.speakable = speakable;
    this.monologue = monologue;
  }
  @Override
  public String execute(Actor actor, GameMap map) {
    return this.speakable.speak(this.monologue);
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " listens to the " + this.speakable;
  }
}
