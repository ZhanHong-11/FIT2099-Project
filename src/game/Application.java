package game;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import game.actors.Player;
import game.actors.enemies.WanderingUndead;
import game.display.FancyMessage;
import game.gamemaps.AbandonedVillage;
import game.gamemaps.BurialGround;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.Graveyard;
import game.grounds.LockedGate;
import game.grounds.Puddle;
import game.grounds.Void;
import game.grounds.Wall;
import game.weapons.Broadsword;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

/**
 * The main class to start the game. Created by:
 *
 * @author Adrian Kristanto
 * Modified by: Soo Zhan Hong
 */
public class Application {

  public static void main(String[] args) {

    World world = new World(new Display());

    FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
        new Wall(), new Floor(), new Puddle(), new Void(), new Graveyard(), new LockedGate());

    GameMap abandonedVillage = new AbandonedVillage(groundFactory);
    world.addGameMap(abandonedVillage);

    GameMap burialGround = new BurialGround(groundFactory);
    world.addGameMap(burialGround);

    for (String line : FancyMessage.TITLE.split("\n")) {
      new Display().println(line);
      try {
        Thread.sleep(200);
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }

    abandonedVillage.at(23, 10).addActor(new WanderingUndead());
    abandonedVillage.at(28, 6).addItem(new Broadsword());
    abandonedVillage.at(4, 3).setGround(
        new LockedGate(new MoveActorAction(burialGround.at(22, 7), "to the Burial Ground!")));

    burialGround.at(22, 6).setGround(new LockedGate(
        new MoveActorAction(abandonedVillage.at(5, 3), "to the Abandoned Village!")));

    Player player = new Player("The Abstracted One", '@', 150, 200);
    world.addPlayer(player, abandonedVillage.at(29, 5));

    world.run();
  }
}
