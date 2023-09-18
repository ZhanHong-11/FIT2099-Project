package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

public class Runes extends Item implements Consumable {
    private static final String ATTRIBUTE = "balance";
    private final int value; // Amount of runes

     public Runes(int value) {
         super("Runes", '$', true);
         this.value = value;
     }

    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        actionList.add(new ConsumeAction(this));
        return actionList;
    }

    @Override
    public int consume(Actor actor) {
        actor.addBalance(value);
        return this.value;
    }

    @Override
    public String getAttribute() {
        return Runes.ATTRIBUTE;
    }

}