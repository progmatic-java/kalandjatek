package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class InnInit extends InitRoom {
  @Override
  public String getName() {
    return "The Black Hole Inn";
  }

  List<NPC> getInitNpcs() {
    return List.of(
        NPC.builder()
            .name("Burrows")
            .description("The drunk owner of The Black Hole Inn.")
            .friendly(true)
            .hp(50)
            .mp(50)
            .gold(1000)
            .imgRef("https://i.imgur.com/9AnmjLg.png")
            .build(),
        NPC.builder()
            .name("Switcher")
            .description("Your old friend from the prison.")
            .friendly(true)
            .hp(100)
            .mp(20)
            .gold(500)
            .imgRef("https://i.imgur.com/3IOuWgJ.png")
            .build(),
        NPC.builder()
            .name("Lady Regex")
            .description("A mysterious witch from another town.")
            .friendly(false)
            .hp(200)
            .mp(300)
            .gold(250)
            .imgRef("https://i.imgur.com/RpbOdHt.png")
            .build()
    );
  }

  List<Item> getInitItems() {
    return List.of(
        Item.builder()
            .itemName("Beer")
            .description("A cold drink from Go'odor")
            .typeOfItem(ItemEnum.CONSUMABLE)
            .build(),
        Item.builder()
            .itemName("Knife")
            .description("Good for slicing bread")
            .typeOfItem(ItemEnum.ATTACK)
            .build(),
        Item.builder()
            .itemName("Coins")
            .description("You should take it")
            .typeOfItem(ItemEnum.VALUABLE)
            .build()
    );
  }

  @Override
  public String getRoomImgRef() {
    return "https://wallpaperaccess.com/full/6079735.png";
  }
}
