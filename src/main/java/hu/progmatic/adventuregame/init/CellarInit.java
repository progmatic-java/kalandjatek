package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
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
            .description("Precious pet of Burrows.He doesn't seem friendly.")
            .imgRef("https://i.imgur.com/s4KyB2S.png")
            .friendly(false)
            .inventory(new Inventory())
            .hp(50)
            .mp(5)
            .attack(10)
            .defence(10)
            .damage(5)
            .action(Action.builder()
                .conversationText("*Agressive growling")
                .childActions(List.of(
                    Action.builder()
                        .conversationText("YIKES!")
                        .childActions(List.of(
                            Action.builder()
                                .conversationText("WUF WUF WUF")
                                .build()))
                        .build(),
                    Action.builder()
                        .conversationText("Holy Joe, what is that??!!")
                        .childActions(List.of(
                            Action.builder()
                                .conversationText("WUF WUF WUF")
                                .build()))
                        .build(),
                    Action.builder()
                        .conversationText("Begone you mutt!")
                        .childActions(List.of(
                            Action.builder()
                                .conversationText("*Whimpers away")
                                .build()))
                        .build()))
                .build())
            .build()
    );
  }

  @Override
  List<Item> getInitItems() {
    return List.of(
        Item.builder()
            .itemName("Imported Beer")
            .description("It's a holy drink from Ko'olossy")
            .hp(10)
            .value(0)
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        Item.builder()
            .itemName("Fine Wine")
            .description("It has a lot of antioxidants")
            .hp(15)
            .value(0)
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        Item.builder()
            .itemName("Dead body")
            .description("Dog food")
            .value(0)
            .typeOfItem(ItemEnum.JUNK)
            .build(),
        Item.builder()
            .itemName("Coins")
            .description("You should take it.")
            .value(100)
            .typeOfItem(ItemEnum.VALUABLE)
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
