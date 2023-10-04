package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.capabilities.Ability;

/**
 * A subclass of Item which implements the Consumable and Sellable Interface,
 * representing a consumable Item (Blood-berry)
 *
 * @author Alvin Andrean
 * @see Item
 * @see Consumable
 * @see Sellable
 */
public class Bloodberry extends Item implements Consumable, Sellable {
    private static final int BASE_SELL_PRICE = 10;

    /**
     * Constructs a new blood berry with the default attributes.
     */
    public Bloodberry() {
        super("Bloodberry", '*', true);
    }

    /**
     * List of allowable actions that blood-berry can perform to the current actor
     *
     * @param owner the actor that owns the item
     * @return an ActionList that contain the ConsumeAction
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * List of allowable actions that blood-berry can perform to the current actor
     *
     * @param otherActor the other actor that can purchase this item
     * @return an ActionList that contain the SellAction
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        if (otherActor.hasCapability(Ability.TRADING)){
            actions.add(new SellAction(this));
        }
        return actions;
    }

    /**
     * Consumes the blood berry and returns a message with the amount of maximum health gained by the actor.
     * the blood berry increases the actor's maximum health by 5 points and is removed once it is consumed.
     *
     * @param actor The actor who consumes the bloodberry
     * @return a string representing a message with the amount of maximum health increased by the actor
     */
    @Override
    public String consume(Actor actor) {
        int healthRecovery = 5;
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, healthRecovery);
        actor.removeItemFromInventory(this);
        return actor + " has increase the maximum health by " + healthRecovery + " points.";
    }

    /**
     * Sells the blood berry and returns a message showing that the player has sold the item
     * after selling the item, the blood-berry is removed from the player's inventory
     *
     * @param actor The actor who's selling the blood-berry
     * @return a message showing that the player has sold the blood-berry
     */
    @Override
    public String sell(Actor actor) {
        actor.removeItemFromInventory(this);
        return actor + " had sold a " + this;
    }

    /**
     * To get the sell price of a particular item
     *
     * @return the selling price of the item
     */
    @Override
    public int getSellPrice() {
        return BASE_SELL_PRICE;
    }
}
