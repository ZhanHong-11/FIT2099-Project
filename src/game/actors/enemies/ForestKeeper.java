package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.FollowBehaviour;
import game.items.HealingVial;


import java.util.*;

public class ForestKeeper extends Enemy {

    private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 25;
    private static final int BASE_INTRINSIC_HIT_RATE = 75;
    private static final String BASE_WEAPON_VERB = "Attacks";
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private Random random = new Random();

    public ForestKeeper() {
        super("Forest Keeper", '8', 125);
        this.addBehaviour(new FollowBehaviour());
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB, BASE_INTRINSIC_HIT_RATE);
    }

    public void drop(GameMap map) {

        int num = random.nextInt(10);
        if (num < 2) {
            Location location = map.locationOf(this);
            map.at(location.x(), location.y()).addItem(new HealingVial());
        }

    }

}
