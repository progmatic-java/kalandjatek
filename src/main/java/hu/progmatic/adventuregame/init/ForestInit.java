package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class ForestInit extends InitRoom {
  @Override
  public String getName() {
    return "The Dark Forest of K'aaam\n";
  }

  @Override
  List<NPC> getInitNpcs() {
    return List.of(
        NPC.builder()
            .name("Ugly Troll")
            .description("As big and ugly as trolls get!")
            .build(),
        NPC.builder()
            .name("X-epson")
            .description("A being from another dimension that haunts the forest.")
            .build()
    );
  }


  @Override
  List<Item> getInitItems() {
    return List.of(
        Item.builder()
            .itemName("Poisonous berries")
            .description("I should not eat this, but it tastes so good.")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        Item.builder()
            .itemName("The Ring of X-epson")
            .description("X-epson's magic is protecting me in battles!")
            .typeOfItem(ItemEnum.SHIELD)
            .build(),
        Item.builder()
            .itemName("Returno's left ear")
            .description("Ew!")
            .typeOfItem(ItemEnum.JUNK)
            .build()
    );
  }

  @Override
  public String getRoomImgRef() {
    return "https://i.pinimg.com/originals/bf/1d/16/bf1d1605e62b31685316fa5ab1f7a32d.jpg";
  }
}