package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npc.Speakable;
import game.items.Consumable;

/**
 * A subclass of Action that represents a speak action. A speak action is an action that allows
 * an actor to speak and tell stories.
 *
 * @see Action
 */
public class SpeakAction extends Action {

  /**
   * The Speaker object
   */
  private Speakable speaker;

  /**
   * Constructs a new speak action
   *
   * @param speaker The speaker object
   */
  public SpeakAction(Speakable speaker){
    this.speaker = speaker;
  }

  /**
   * Executes the speak action and returns a string that describes the action executed.
   *
   * @param actor The actor who performs the consume action
   * @param map   The game map that contains the actor
   * @return the action description to be displayed on the menu
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    return this.speaker + " to " + actor + ": " + this.speaker.speak(actor);
  }

  /**
   * Returns a string that describes the consume action in a menu.
   *
   * @param actor The actor performing the action.
   * @return the action description to be displayed on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " listens to the " + this.speaker;
  }
}
