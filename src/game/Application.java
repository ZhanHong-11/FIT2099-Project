package game;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import game.actors.Player;
import game.actors.enemies.Abxervyer;
import game.actors.enemies.WanderingUndead;
import game.actors.merchants.Traveller;
import game.items.Bloodberry;
import game.spawners.ForestKeeperFactory;
import game.spawners.HollowSoldierFactory;
import game.spawners.RedWolfFactory;
import game.spawners.WanderingUndeadFactory;
import game.display.FancyMessage;
import game.gamemaps.AbandonedVillage;
import game.gamemaps.AbxervyerBattleMap;
import game.gamemaps.AncientWood;
import game.gamemaps.BurialGround;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.LockedGate;
import game.grounds.Puddle;
import game.grounds.Void;
import game.grounds.Wall;
import game.grounds.spawnlocation.Bush;
import game.grounds.spawnlocation.Graveyard;
import game.grounds.spawnlocation.Hut;
import game.weapons.Broadsword;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.weapons.GiantHammer;

/**
 * The main class to start the game. Created by:
 *
 * @author Adrian Kristanto Modified by: Soo Zhan Hong
 */
public class Application {

  public static void main(String[] args) {

    World world = new World(new Display());

    FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
        new Wall(), new Floor(), new Puddle(), new Void());

    GameMap abandonedVillage = new AbandonedVillage(groundFactory);
    world.addGameMap(abandonedVillage);

    GameMap burialGround = new BurialGround(groundFactory);
    world.addGameMap(burialGround);

    // REQ1: The Ancient Woods map
    GameMap ancientWoods = new AncientWood(groundFactory);
    world.addGameMap(ancientWoods);

    GameMap abxervyerBattleMap = new AbxervyerBattleMap(groundFactory);
    world.addGameMap(abxervyerBattleMap);

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
    abandonedVillage.at(40, 2).setGround(new Graveyard(new WanderingUndeadFactory()));
    abandonedVillage.at(4, 3).setGround(new LockedGate(
        new MoveActorAction(burialGround.at(22, 7), "to the Burial Grounds!")));

    burialGround.at(7, 3).setGround(new Graveyard(new HollowSoldierFactory()));
    burialGround.at(22, 6).setGround(new LockedGate(
        new MoveActorAction(abandonedVillage.at(5, 3), "to the Abandoned Village!")));
    burialGround.at(0, 8).setGround(new LockedGate(
        new MoveActorAction(ancientWoods.at(1, 7), "to the Ancient Woods!")));

    ancientWoods.at(0, 0).addActor(new Traveller());
    ancientWoods.at(7, 7).addItem(new Bloodberry());
    ancientWoods.at(10, 1).setGround(new Bush(new RedWolfFactory()));
    ancientWoods.at(31, 10).setGround(new Bush(new RedWolfFactory()));
    ancientWoods.at(43, 3).setGround(new Hut(new ForestKeeperFactory()));
    ancientWoods.at(6, 6).setGround(new Hut(new ForestKeeperFactory()));
    ancientWoods.at(0, 7).setGround(new LockedGate(
        new MoveActorAction(burialGround.at(0, 8), "to the Burial Grounds!")));
    ancientWoods.at(55, 0).setGround(
            new LockedGate(new MoveActorAction(abxervyerBattleMap.at(38, 19), "to the Abxervyer Battle Room!")));

    abxervyerBattleMap.at(1, 8).setGround(new Hut(new ForestKeeperFactory()));
    abxervyerBattleMap.at(8, 1).setGround(new Hut(new ForestKeeperFactory()));
    abxervyerBattleMap.at(33, 17).setGround(new Bush(new RedWolfFactory()));
    LockedGate gate = new LockedGate(new MoveActorAction(ancientWoods.at(55, 0), "to the Ancient Woods!"));
    abxervyerBattleMap.at(23, 10).addActor(new Abxervyer(gate));
    abxervyerBattleMap.at(33, 19).addItem(new GiantHammer());

    Player player = new Player("The Abstracted One", '@', 150, 200);

    world.run();
  }
}
