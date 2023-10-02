package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.items.HealingVial;
import game.items.Rune;


import java.util.*;

public class ForestKeeper extends Enemy {

    private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 25;
    private static final int BASE_INTRINSIC_HIT_RATE = 75;
    private static final String BASE_WEAPON_VERB = "smacks";
    public static final int BASE_HIT_POINTS = 125;
    public static final int BASE_RUNES_DROP_AMOUNT = 50;
    private Random random = new Random();

    public ForestKeeper() {
        super("Forest Keeper", '8', BASE_HIT_POINTS);
        this.addCapability(Ability.FOLLOW);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB, BASE_INTRINSIC_HIT_RATE);
    }

    @Override
    public void drop(GameMap map) {
        int num = random.nextInt(10);
        Location location = map.locationOf(this);
        map.at(location.x(), location.y()).addItem(new Rune(BASE_RUNES_DROP_AMOUNT));
        if (num < 2) {
            map.at(location.x(), location.y()).addItem(new HealingVial());
        }
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.RAINY_BUFF)){
            heal(10);
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
