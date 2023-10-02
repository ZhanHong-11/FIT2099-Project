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

public class RedWolf extends Enemy {

    private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 15;
    private static final int BASE_INTRINSIC_HIT_RATE = 80;
    private static final String BASE_WEAPON_VERB = "bites";
    public static final int BASE_HIT_POINTS = 25;
    public static final int BASE_RUNES_DROP_AMOUNT = 25;
    private Random random = new Random();

    public RedWolf() {
        super("Red Wolf", 'r', BASE_HIT_POINTS);
        this.addCapability(Ability.FOLLOW);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        if (!this.hasCapability(Status.SUNNY_BUFF)){
            return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB, BASE_INTRINSIC_HIT_RATE);
        }
        else {
            return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE * 3, BASE_WEAPON_VERB, BASE_INTRINSIC_HIT_RATE);
        }
    }

    @Override
    public void drop(GameMap map) {
        int num = random.nextInt(10);
        Location location = map.locationOf(this);
        map.at(location.x(), location.y()).addItem(new Rune(BASE_RUNES_DROP_AMOUNT));
        if (num < 1) {
            map.at(location.x(), location.y()).addItem(new HealingVial());
        }
    }

}
