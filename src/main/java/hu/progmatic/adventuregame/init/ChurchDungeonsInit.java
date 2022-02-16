package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class ChurchDungeonsInit extends InitRoom {

  @Override
  public String getName() {
    return "Tomb of Holy Joe";
  }

  @Override
  List<NPC> getInitNpcs() {
    return List.of(
        NPC.builder()
            .name("Dungeon keeper")
            .description("A robust man with a ferocious aura")
                .friendly(true)
                .hp(1000)
                .mp(50)
                .gold(200)
                .imgRef("https://i.imgur.com/zf7gYuJ.png")
                .action(
                        Action.builder()
                                .conversationText("")
                                .build()
                )
            .build());
  }

  @Override
  List<Item> getInitItems() {
    return List.of(
        Item.builder()
            .itemName("Dead body of a heretic")
            .description("Has a stench of rotting flesh")
            .typeOfItem(ItemEnum.JUNK)
            .build(),
        Item.builder()
            .itemName("Sacrificial knife")
            .description("Used for rituals")
            .typeOfItem(ItemEnum.ATTACK)
            .build(),
        Item.builder()
            .itemName("Skeleton key")
            .description("Can open skeleton doors")
            .typeOfItem(ItemEnum.KEY)
            .build(),
        Item.builder()
            .itemName("Torch")
            .description("Might be useful")
            .typeOfItem(ItemEnum.ATTACK)
            .build()
    );
  }

  @Override
  public String getRoomDescription() {
    return "Word has it that the Church Dungeon was built for secret rituals and torturing heretics. Anyone who has gone inside, has never been seen again..";
  }

  @Override
  public String getRoomImgRef() {
    return "https://i.pinimg.com/originals/94/72/39/947239b51bb11bd3802cd2ab29414633.jpg";
  }
}
