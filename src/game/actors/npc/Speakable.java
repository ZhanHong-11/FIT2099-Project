package game.actors.npc;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface that represents a speakable object. A speakable object is the actors that can
 * speak.
 */
public interface Speakable {

  /**
   * Speaks when the SpeakAction is chosen by the listener. The implementation of this method should
   * consist of the possible monologues and decide which monologue to speak to the listener.
   *
   * @param listener The actor that listen to the monologue
   */
  String speak(Actor listener);
}
