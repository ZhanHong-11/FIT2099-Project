package game.actors.npc;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface that represents a speakable subject. A speakable subject is actors that can speak.
 */
public interface Speakable {

  /**
   * Speaks when the SpeakAction is chosen by the listener.
   * The implementation of this method should have all the monologues
   * that the actor can say.
   *
   * @param listener The other actor that will listen
   */
  String speak(Actor listener);




}
