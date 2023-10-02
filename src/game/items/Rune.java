package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

public class Rune extends Item implements Consumable {
    private final int value; // Amount of runes

     public Rune(int value) {
         super("Rune", '$', true);
         this.value = value;
     }

    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        actionList.add(new ConsumeAction(this));
        return actionList;
    }

    @Override
    public String consume(Actor actor) {
        actor.addBalance(this.value);
        actor.removeItemFromInventory(this);
        return actor + " had consumed the runes and gained $" + this.value;
    }
}
