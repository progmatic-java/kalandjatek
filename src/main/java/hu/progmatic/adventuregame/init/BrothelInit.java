package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class BrothelInit extends InitRoom {
    @Override
    public String getName() {
        return "The Red Try and Catch";
    }

    @Override
    List<NPC> getInitNpcs() {
        return List.of(
                NPC.builder()
                        .name("Lady Regexxx")
                        .description("A mysterious witch from another town, who is also the madam of the brothel")
                        .friendly(false)
                        .hp(200)
                        .mp(300)
                        .gold(250)
                        .imgRef("https://i.imgur.com/RpbOdHt.png")
                        .build()
        );
    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Mysterious Perfume")
                        .description("Gives you the ability to shapeshift. I wonder what it's doing in a brothel..?")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build(),
                Item.builder()
                        .itemName("A Pack of Tarot Cards")
                        .description("Would you like to know your fortune?")
                        .typeOfItem(ItemEnum.JUNK)
                        .build()
        );
    }

    @Override
    public String getRoomImgRef() {
        return "https://www.scabard.com/user/Pochibella/image/a61e06d492332f186a58ce2baa1d30c2.jpg";
    }
}
