package game.actors.merchants;

import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.weapons.Broadsword;

public class Traveller extends Merchant {

  public Traveller() {
    super("Traveller", 'ඞ');
    this.addItemToInventory(new HealingVial());
    this.addItemToInventory(new RefreshingFlask());
    this.addItemToInventory(new Broadsword());
  }
}
