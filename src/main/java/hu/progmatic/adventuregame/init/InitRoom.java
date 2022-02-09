package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.npc.NPC;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class InitRoom {
  private String name;
  private String roomImgRef;
  private String roomAudio;
  private final Inventory inventory;
  private final List<Item> items;
  private final List<NPC> npcs;


  public InitRoom() {
    this.items = getInitItems();
    this.inventory = getInitInventory();
    this.npcs = getInitNpcs();
  }

  final Inventory getInitInventory() {
    Inventory inventory = Inventory.builder().build();
    for (Item item : items) {
      inventory.getItems().add(item);
      item.setInventory(inventory);
    }
    return inventory;
  }

  abstract List<NPC> getInitNpcs();

  abstract List<Item> getInitItems();
}
