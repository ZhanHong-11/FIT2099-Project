package game;

import edu.monash.fit2099.engine.actors.Actor;
import game.capabilities.Status;

public class Monologue {

  private Actor actor;

  private String monologue;
  private String speaker;

  public Monologue(Actor actor, String monologue, String speaker ){
    this.actor = actor;
    this.monologue = monologue;
    this.speaker = speaker;
  }

  public Boolean canSpeak(){
    return this.actor.hasCapability(Status.DEFEATED_ABXERVYER);
  }


}
