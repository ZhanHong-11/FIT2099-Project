package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import game.actors.Blacksmith;
import game.actors.Player;
import game.actors.enemies.Abxervyer;
import game.actors.enemies.LivingBranch;
import game.actors.enemies.WanderingUndead;
import game.actors.merchants.Traveller;
import game.gamemaps.*;
import game.items.Bloodberry;
import game.items.Key;
import game.spawners.*;
import game.display.FancyMessage;
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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

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

    GameMap ancientWoods = new AncientWood(groundFactory);
    world.addGameMap(ancientWoods);

    GameMap abxervyerBattleMap = new AbxervyerBattleMap(groundFactory);
    world.addGameMap(abxervyerBattleMap);

    // A3 : REQ 1
    GameMap overgrownSanctuary = new OvergrownSanctuary(groundFactory);
    world.addGameMap(overgrownSanctuary);

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

    Map<String, Action> abxervyerBattleMapTravelActions = new HashMap<>();
    abxervyerBattleMapTravelActions.put("AncientWoods", new MoveActorAction(ancientWoods.at(55, 0), "to the Ancient Woods!"));
    abxervyerBattleMapTravelActions.put("OvergrownSanctuary", new MoveActorAction(overgrownSanctuary.at(16, 0), "to the Overgrown Sanctuary!"));
    LockedGate gate = new LockedGate(abxervyerBattleMapTravelActions);
    Abxervyer abxervyer = new Abxervyer(gate);

    ancientWoods.at(0, 0).addActor(new Traveller());
    ancientWoods.at(7, 7).addItem(new Bloodberry());
    ancientWoods.at(10, 1).setGround(new Bush(new RedWolfFactory(abxervyer)));
    ancientWoods.at(31, 10).setGround(new Bush(new RedWolfFactory(abxervyer)));
    ancientWoods.at(43, 3).setGround(new Hut(new ForestKeeperFactory(abxervyer)));
    ancientWoods.at(6, 6).setGround(new Hut(new ForestKeeperFactory(abxervyer)));
    ancientWoods.at(0, 7).setGround(new LockedGate(
        new MoveActorAction(burialGround.at(0, 8), "to the Burial Grounds!")));
    ancientWoods.at(55, 0).setGround(
        new LockedGate(
            new MoveActorAction(abxervyerBattleMap.at(38, 19), "to the Abxervyer Battle Room!")));

    abxervyerBattleMap.at(23, 10).addActor(abxervyer);
    abxervyerBattleMap.at(33, 19).addItem(new GiantHammer());
    abxervyerBattleMap.at(1, 8).setGround(new Hut(new ForestKeeperFactory(abxervyer)));
    abxervyerBattleMap.at(8, 1).setGround(new Hut(new ForestKeeperFactory(abxervyer)));
    abxervyerBattleMap.at(33, 17).setGround(new Bush(new RedWolfFactory(abxervyer)));

    overgrownSanctuary.at(16, 1).setGround(new Hut(new EldentreeGuardianFactory()));
    overgrownSanctuary.at(18, 1).setGround(new Bush(new LivingBranchFactory()));





    Player player = new Player("The Abstracted One", '@', 999, 200);
//    world.addPlayer(player, abandonedVillage.at(29, 5));


    world.addPlayer(player, abxervyerBattleMap.at(22, 10));
    player.addItemToInventory(new GiantHammer());
    player.addItemToInventory(new Key());

    world.run();
  }
}
