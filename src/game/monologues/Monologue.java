package game.monologues;

import edu.monash.fit2099.engine.actors.Actor;


public class Monologue {

  private Actor player;

  private String monologue;
  private String speaker;

  public Monologue(Actor player, String monologue, String speaker ){
    this.player = player;
    this.monologue = monologue;
    this.speaker = speaker;
  }

  public String getMonologue() {
    return this.monologue;
  }

  public String getSpeaker(){
    return this.speaker;
  }
}
