package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;



/**
 * A subclass of Item which implements the Consumable Interface,
 * representing a consumable Item (Rune)
 *
 * @author Alvin Andrean
 * @see Item
 * @see Consumable
 */
public class Rune extends Item implements Consumable {
    private final int value; // Amount of runes

    /**
     * Constructs a new rune with the default attributes and the value of the rune.
     */
     public Rune(int value) {
         super("Rune", '$', true);
         this.value = value;
     }

    /**
     * List of allowable actions that the runes can perform to the current actor
     *
     * @param owner the actor that owns the item
     * @return an ActionList that contain the ConsumeAction
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        actionList.add(new ConsumeAction(this));
        return actionList;
    }


    /**
     * Consumes the blood berry and returns the amount of maximum health gained by the actor.
     * the blood berry increases the actor's maximum health by 5 points and is removed once it is consumed.
     *
     * @param actor The actor who consumes the rune
     * @return a string representing a message that the actor successfully consumed the runes with the value/amount shown in the end.
     *
     */
    @Override
    public String consume(Actor actor) {
        actor.addBalance(this.value);
        actor.removeItemFromInventory(this);
        return actor + " had consumed the runes and gained $" + this.value;
    }
}
