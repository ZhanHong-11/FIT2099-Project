package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.actions.ConsumeRunesAction;

public class Bloodberry extends Item implements Consumable {

    /**
     * The type of attribute that is affected by consuming the bloodberry
     */
    private final static String ATTRIBUTE = "maximum health";

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
     * Consumes the blood berry and returns the amount of maximum health gained by the actor.
     * the blood berry increases the actor's maximum health by 5 points and is removed once it is consumed.
     *
     * @param actor The actor who consumes the healing vial
     * @return The amount of maximum health increased by the actor
     */
    @Override
    public int consume(Actor actor) {
        int value = 5;
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, value);
        actor.removeItemFromInventory(this);
        return value;
    }

    @Override
    public String getAttribute() {
        return Bloodberry.ATTRIBUTE;
    }
}