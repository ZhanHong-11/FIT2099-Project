package game;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import game.actors.npc.Blacksmith;
import game.actors.Player;
import game.actors.enemies.Abxervyer;
import game.actors.enemies.WanderingUndead;
import game.actors.npc.Traveller;
import game.gamemaps.*;
import game.items.Bloodberry;
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

    GameMap overgrownSanctuary = new OvergrownSanctuary(groundFactory);
    world.addGameMap(overgrownSanctuary);

    Player player = new Player("The Abstracted One", '@', 150, 200, abandonedVillage.at(29, 5));

    for (String line : FancyMessage.TITLE.split("\n")) {
      new Display().println(line);
      try {
        Thread.sleep(200);
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }

    abandonedVillage.at(45, 11).addActor(new Blacksmith());
    abandonedVillage.at(23, 10).addActor(new WanderingUndead(player));
    abandonedVillage.at(28, 6).addItem(new Broadsword());
    abandonedVillage.at(40, 2).setGround(new Graveyard(new WanderingUndeadFactory(player)));
    abandonedVillage.at(4, 3).setGround(new LockedGate(
        new MoveActorAction(burialGround.at(22, 6), "to the Burial Grounds!"), player));

    burialGround.at(7, 3).setGround(new Graveyard(new HollowSoldierFactory(player)));
    burialGround.at(22, 6).setGround(new LockedGate(
        new MoveActorAction(abandonedVillage.at(4, 3), "to the Abandoned Village!"), player));
    burialGround.at(0, 8).setGround(new LockedGate(
        new MoveActorAction(ancientWoods.at(0, 7), "to the Ancient Woods!"), player));

    Map<String, MoveActorAction> abxervyerBattleMapTravelActions = new HashMap<>();
    abxervyerBattleMapTravelActions.put("AncientWoods",
        new MoveActorAction(ancientWoods.at(55, 0), "to the Ancient Woods!"));
    abxervyerBattleMapTravelActions.put("OvergrownSanctuary",
        new MoveActorAction(overgrownSanctuary.at(47, 0), "to the Overgrown Sanctuary!"));
    LockedGate gate = new LockedGate(abxervyerBattleMapTravelActions, player);
    Abxervyer abxervyer = new Abxervyer(gate, player);

    ancientWoods.at(0, 0).addActor(new Traveller());
    ancientWoods.at(7, 7).addItem(new Bloodberry());
    ancientWoods.at(10, 1).setGround(new Bush(new RedWolfFactory(abxervyer, player)));
    ancientWoods.at(31, 10).setGround(new Bush(new RedWolfFactory(abxervyer, player)));
    ancientWoods.at(43, 3).setGround(new Hut(new ForestKeeperFactory(abxervyer, player)));
    ancientWoods.at(6, 6).setGround(new Hut(new ForestKeeperFactory(abxervyer, player)));
    ancientWoods.at(0, 7).setGround(new LockedGate(
        new MoveActorAction(burialGround.at(0, 8), "to the Burial Grounds!"), player));
    ancientWoods.at(55, 0).setGround(
        new LockedGate(
            new MoveActorAction(abxervyerBattleMap.at(31, 1), "to the Abxervyer Battle Room!"),
            player));

    abxervyerBattleMap.at(20, 10).addActor(abxervyer);
    abxervyerBattleMap.at(34, 4).addItem(new GiantHammer());
    abxervyerBattleMap.at(1, 8).setGround(new Hut(new ForestKeeperFactory(abxervyer, player)));
    abxervyerBattleMap.at(8, 1).setGround(new Hut(new ForestKeeperFactory(abxervyer, player)));
    abxervyerBattleMap.at(33, 17).setGround(new Bush(new RedWolfFactory(abxervyer, player)));

    overgrownSanctuary.at(33, 13).setGround(new Graveyard(new HollowSoldierFactory(player)));
    overgrownSanctuary.at(3, 5).setGround(new Hut(new EldentreeGuardianFactory(player)));
    overgrownSanctuary.at(18, 1).setGround(new Bush(new LivingBranchFactory(player)));
    overgrownSanctuary.at(21, 8).setGround(new Bush(new LivingBranchFactory(player)));
    overgrownSanctuary.at(47, 0).setGround(
        new LockedGate(
            new MoveActorAction(abxervyerBattleMap.at(31, 1), "to the Abxervyer Battle Room!"),
            player));

    world.addPlayer(player, abandonedVillage.at(29, 5));
    world.run();
  }
}
