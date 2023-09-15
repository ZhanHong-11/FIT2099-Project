package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Status;
import game.items.HealingVial;


import java.util.*;

public class ForestKeeper extends Actor {

    private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 25;
    private static final int BASE_INTRINSIC_HIT_RATE = 75;
    public static final int  BASE_ITEM_DROP_RATE = 20;
    private static final String BASE_WEAPON_VERB = "Attacks";
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private Random random = new Random();

    public ForestKeeper() {
        super("Forest Keeper", '8', 125);
        this.behaviours.put(999, new WanderBehaviour());
        this.behaviours.put(998, new AttackBehaviour());
    }

    /**x
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB, BASE_INTRINSIC_HIT_RATE);
    }

    @Override
    public String unconscious(GameMap map) {
        return dropItem(map);
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        return dropItem(map);
    }

    private String dropItem(GameMap map) {
        Location actorLocation = map.locationOf(this);
        List<String> droppedItems = new ArrayList<>();

        if (random.nextInt(100) < BASE_ITEM_DROP_RATE) {
            map.at(actorLocation.x(), actorLocation.y()).addItem(new HealingVial());
            droppedItems.add("Healing Vial");
        }

        // Remove actor only once
        map.removeActor(this);

        if (droppedItems.isEmpty()) {
            return super.unconscious(map);
        }

        return "The Forest Keeper drops " + String.join(" and ", droppedItems) + " at " + actorLocation;
    }


    /**
     * The wandering undead can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));

        }
        return actions;
    }

}
