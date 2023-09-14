package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Ability;

/**
 * A class that represents the void.
 * Created by:
 * @author Soo Zhan Hong
 */
public class Void extends Ground {

  private Display display = new Display();

  public Void() {
    super('+');
  }

  /**
   * For every turn, detect whether if there is an actor that steps on the void. If the actor is not
   * immune to the void, then it is killed instantly.
   *
   * @param location The location of the Ground
   */
  @Override
  public void tick(Location location) {
    if (location.containsAnActor()) {
      Actor actor = location.getActor();
      if (!actor.hasCapability(Ability.IMMUNE_TO_VOID)) {
        String deadMsg = actor.unconscious(location.map());
        display.println(deadMsg);
      }
    }
  }

}
