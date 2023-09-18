package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

public class ConsumeRunesAction extends ConsumeAction {
    private final Consumable consumableItem;
    /**
     * Constructs a new consume action with the given consumable item.
     *
     * @param consumableItem The consumable item that is to be consumed by the player
     */
    public ConsumeRunesAction(Consumable consumableItem) {
        super(consumableItem);
        this.consumableItem = consumableItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int runesValue = this.consumableItem.consume(actor);
        return actor + " consumed some runes which increased their " + consumableItem.getAttribute() + " by " + runesValue;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " transfers Runes to wallet";
    }
}
