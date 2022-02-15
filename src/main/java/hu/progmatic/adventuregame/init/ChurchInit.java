package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class ChurchInit extends InitRoom {

  @Override
  public String getName() {
    return "Church of Holy Joe";
  }

  @Override
  List<NPC> getInitNpcs() {
    return List.of(
        NPC.builder()
            .name("Padre Lombok")
            .description("A celestial entity, the main priest of Progmatique")
            .build(),
        NPC.builder()
            .name("Acolyte Thyme")
            .description("An altar-boy, Padre Lombok’s most eager devotee")
            .build());
  }

  @Override
  List<Item> getInitItems() {
    return List.of(
        Item.builder()
            .itemName("Holy water")
            .description("Purifies you inside and out")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        Item.builder()
            .itemName("The Holy Sword of Ma'dog")
            .description("A relic of the almighty Holy Joe")
            .typeOfItem(ItemEnum.ATTACK)
            .build(),
        Item.builder()
            .itemName("Candles")
            .description("They light your path and max your wax")
            .typeOfItem(ItemEnum.JUNK)
            .build(),
        Item.builder()
            .itemName("Magic Scroll")
            .description("Source of ancient knowledge")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build()
    );
  }

  @Override
  public String getRoomDescription() {
    return "A space built for worshipping Holy Joe, the deity of the kingdom, who was also known as the Bringer of the Light. The more energy you devote and money you donate to the Church, the bigger the chance that Holy Joe will bless you";
  }

  @Override
  public String getRoomImgRef() {
    return "https://i.pinimg.com/originals/1e/88/af/1e88af53ffdd1b34ae3d3e6cee4e7594.jpg";
  }
}
