package game.actors.enemies;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.items.Bloodberry;

import java.util.Random;

/**
 * A class that represents a Living Branch. A Living Branch is an enemy that can attack other actors who are
 * hostile to them. The Living Branch cannot follow nor wander. It can drop a bloodberry when killed by another actor
 *
 * @see Enemy
 */
public class LivingBranch extends Enemy {

    /**
     * The base intrinsic weapon damage of the living branch
     */
    private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 250;

    /**
     * The base intrinsic weapon hit rate of the living branch
     */
    private static final int BASE_INTRINSIC_HIT_RATE = 90;

    /**
     * The base intrinsic weapon verb of the living branch
     */
    private static final String BASE_WEAPON_VERB = "struck";

    /**
     * The base healing vial drop rate of the living branch
     */
    private static final int BASE_BLOODBERRY_DROP_CHANCE = 50;

    /**
     * The base runes drop amount of the living branch
     */
    private static final int BASE_RUNES_DROP_AMOUNT = 500;

    private Random random = new Random();


    /**
     * Constructs a new living branch
     */
    public LivingBranch() {
        super("Living Branch", '?', 75);
        this.addCapability(Ability.IMMUNE_TO_VOID);
    }

    /**
     * Returns an IntrinsicWeapon that represents the attack of the Living Branch.
     *
     * @return An IntrinsicWeapon that represents the attack
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, BASE_WEAPON_VERB,
                BASE_INTRINSIC_HIT_RATE);

    }

    @Override
    protected int getDropRuneAmount() {
        return BASE_RUNES_DROP_AMOUNT;
    }

    /**
     * Drops an item on the game map when the Living Branch is killed. The item dropped is Bloodberry
     * with a probability of 50%. The item is dropped at the location
     * of the Living Branch. It will also drop Runes when killed by another actor
     *
     * @param map The game map where the Living Branch is located.
     */
    @Override
    public void drop(GameMap map) {
        super.drop(map);
        int num = random.nextInt(100);
        Location location = map.locationOf(this);
        if (num < BASE_BLOODBERRY_DROP_CHANCE) {
            map.at(location.x(), location.y()).addItem(new Bloodberry());
        }

    }
}
