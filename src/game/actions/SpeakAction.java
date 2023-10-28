package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npc.Speakable;

/**
 * A subclass of Action that represents a speak action. Speak action is an action allowing an npc to
 * speak to the player.
 *
 * @see Action
 */
public class SpeakAction extends Action {

  /**
   * The actor that speaks
   */
  private Speakable speaker;

  /**
   * Constructs a new speak action
   *
   * @param speaker The actor that speaks
   */
  public SpeakAction(Speakable speaker) {
    this.speaker = speaker;
  }

  /**
   * Executes the speak action and returns a monologue.
   *
   * @param actor The actor that listen to the monologue
   * @param map   The game map that contains the actor
   * @return the action description to be displayed on the menu
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    return this.speaker + " to " + actor + ": " + this.speaker.speak(actor);
  }

  /**
   * Returns a string that describes the speak action in a menu.
   *
   * @param actor The actor that listen.
   * @return the action description to be displayed on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " listens to the " + this.speaker;
  }
}
