package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class MainSquareInit extends InitRoom {
  @Override
  public String getName() {
    return "Ko’lossy Square";
  }

  @Override
  List<NPC> getInitNpcs() {
    return List.of(
        NPC.builder()
            .name("Trace")
            .description("A drunk guy sleeping outside of The Black Hole Inn.")
            .build(),
        NPC.builder()
            .name("Tomcat")
            .description("The town's beggar and thief hunter.")
            .build(),
        NPC.builder()
            .name("Admina")
            .description("The town's prostitute")
            .build()
    );
  }

  @Override
  List<Item> getInitItems() {
    return List.of(
        Item.builder()
            .itemName("Coins")
            .typeOfItem(ItemEnum.VALUABLE)
            .build(),
        Item.builder()
            .itemName("Stale bread")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        Item.builder()
            .itemName("Bucket")
            .typeOfItem(ItemEnum.SHIELD)
            .build(),
        Item.builder()
            .itemName("Suspicious letter")
            .typeOfItem(ItemEnum.JUNK)
            .build()
    );
  }

  @Override
  public String getRoomDescription() {
    return "NEMTUDOM";
  }

  @Override
  public String getRoomImgRef() {
    return "https://i.pinimg.com/originals/30/91/a0/3091a017b704dd6c24339700f4a16ff4.jpg";
  }
}
