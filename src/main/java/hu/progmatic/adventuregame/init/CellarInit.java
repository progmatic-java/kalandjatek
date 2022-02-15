package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class CellarInit extends InitRoom {
  @Override
  public String getName() {
    return "Cellar of Black Hole Inn";
  }

  @Override
  List<NPC> getInitNpcs() {
    return List.of(
        NPC.builder()
            .name("Mad dog")
            .description("Precious pet of Burrows")
            .build()
    );
  }

  @Override
  List<Item> getInitItems() {
    return List.of(
        Item.builder()
            .itemName("Imported Beer")
            .description("It's a holy drink from Ko'olossy")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        Item.builder()
            .itemName("Fine Wine")
            .description("It has a lot of antioxidants")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        Item.builder()
            .itemName("Dead body")
            .description("Dog food")
            .typeOfItem(ItemEnum.JUNK)
            .build()
    );
  }

  @Override
  public String getRoomDescription() {
    return "Large barrels of wine and beer await thirsty customers, but beware, the cellar can be a dangerous place! There's a mad dog growling in the dark shadows, which seems to have an insatiable appetite... Especially for fresh meat..";
  }

  @Override
  public String getRoomImgRef() {
    return "https://i.imgur.com/qqBe4IX.jpg";
  }

  @Override
  public String getRoomAudio() {
    return "music/Damp.mp3";
  }
}
