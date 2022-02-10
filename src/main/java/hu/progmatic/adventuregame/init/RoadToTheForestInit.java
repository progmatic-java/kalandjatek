package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class RoadToTheForestInit extends InitRoom {

  @Override
  public String getName() {
    return "Road to the Forest of K'aam";
  }

  @Override
  List<NPC> getInitNpcs() {
    return List.of(
        NPC.builder()
            .name("Returno")
            .description("He just came out of the woods. He looks hurt.")
            .build()
    );
  }

  @Override
  List<Item> getInitItems() {
    return List.of(
        Item.builder()
            .itemName("Pebble")
            .description("It's not much but at least it can do a bit of damage")
            .typeOfItem(ItemEnum.ATTACK)
            .build(),
        Item.builder()
            .itemName("Elderflower")
            .description("I feel funny when I eat this")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build()
    );
  }

  @Override
  public String getRoomImgRef() {
    return "https://www.worldanvil.com/uploads/images/085e0ae64f26ed79426cdb5c1a3bb060.jpg";
  }
}
