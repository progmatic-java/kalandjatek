package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
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
                        .friendly(true)
                        .hp(200)
                        .mp(300)
                        .gold(250)
                        .imgRef("https://i.pinimg.com/originals/c6/52/15/c65215d53a814f0a39afa77e2696e942.png")
                        .action(Action.builder()
                                .conversationText("Hello. I'm Lady Regexxx, the madam of the Red Try and Catch. What can I do for you?")
                                .childActions(
                                        List.of(
                                                Action.builder()
                                                        .conversationText("Umm... I think I'm in the wrong place... Sorry..!")
                                                        .childActions(
                                                                List.of(
                                                                        Action.builder()
                                                                                .conversationText("Maybe next time, you'll grace us with your presence for a bit longer..?")
                                                                                .build()
                                                                )
                                                        )
                                                        .build(),
                                                Action.builder()
                                                        .conversationText("Hello. I'm looking for a bit of fun.")
                                                        .childActions(
                                                                List.of(
                                                                        Action.builder()
                                                                                .conversationText("Well, you've come to the right place! Just follow me to choose a lover...")
                                                                                .build()
                                                                )
                                                        )
                                                        .build()                                        )
                                )
                                .build()
                        )
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
    public String getRoomDescription() {
        return "Feel your best in the lap of luxury! Beautiful women and exotic men are waiting in line to satisfy all your needs. Mix pleasure with business, as you can also gather some essential information for completing your quest.";
    }

    @Override
    public String getRoomImgRef() {
        return "https://www.scabard.com/user/Pochibella/image/a61e06d492332f186a58ce2baa1d30c2.jpg";
    }
}
