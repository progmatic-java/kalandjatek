package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class ForestLakeInit extends InitRoom {
  @Override
  public String getName() {
    return "The Forest Lake\n";
  }

  @Override
  List<NPC> getInitNpcs() {
    return List.of(
        NPC.builder()
            .name("Fairy of the Lake")
            .description("This beautiful fairy loves to seduce young travelers!")
            .build()
    );
  }

  @Override
  List<Item> getInitItems() {
    return List.of(
        Item.builder()
            .itemName("Fairy Shield")
            .description("A magical shield that is useful against dark powers!")
            .typeOfItem(ItemEnum.SHIELD)
            .build(),
        Item.builder()
            .itemName("Fairy Panties")
            .description("Hmmm... What's that doing floating in the water?")
            .typeOfItem(ItemEnum.SHIELD)
            .build(),
        Item.builder()
            .itemName("Rotten fish")
            .description("They exude a disgusting stench!")
            .typeOfItem(ItemEnum.JUNK)
            .build()
    );
  }

  @Override
  public String getRoomImgRef() {
    return "https://i.pinimg.com/originals/da/52/4e/da524e6c19b3a3933daa7793d9b34ad4.jpg";
  }
}
