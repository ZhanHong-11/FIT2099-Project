package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

public class ConsumeRunesAction extends ConsumeAction {

    /**
     * Constructs a new consume action with the given consumable item.
     *
     * @param consumableItem The consumable item that is to be consumed by the player
     */
    public ConsumeRunesAction(Consumable consumableItem) {
        super(consumableItem);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " consumed some runes which increased their balance!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return super.menuDescription(actor);
    }
}
