package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class SpeakAction extends Action {
  private Speakable speakable;
  public  SpeakAction(Speakable speakable){
    this.speakable = speakable;
  }
  @Override
  public String execute(Actor actor, GameMap map) {
    return this.speakable.speak();
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " listens to the " + this.speakable;
  }
}
