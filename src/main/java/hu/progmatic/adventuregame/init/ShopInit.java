package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class ShopInit extends InitRoom{
  @Override
  public String getName() {
    return "Tezco’s Shop";
  }

  @Override
  List<NPC> getInitNpcs() {
    return List.of(
        NPC.builder()
            .name("Tezco")
            .description("The owner of the shop, maybe if i'm nice to him, i can get a better deal.")
            .build()
    );
  }

  @Override
  List<Item> getInitItems() {
    return List.of(
        Item.builder()
            .itemName("Bread")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        Item.builder()
            .itemName("Sword")
            .typeOfItem(ItemEnum.ATTACK)
            .build(),
        Item.builder()
            .itemName("Mace")
            .typeOfItem(ItemEnum.ATTACK)
            .build(),
        Item.builder()
            .itemName("Shield")
            .typeOfItem(ItemEnum.SHIELD)
            .build(),
        Item.builder()
            .itemName("Energy potion")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        Item.builder()
            .itemName("Chili con carne")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build()
    );
  }
}
