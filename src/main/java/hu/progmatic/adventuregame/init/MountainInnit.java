package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class MountainInnit extends InitRoom {

    @Override
    public String getName() {
        return "Mountain to the Dragon";
    }

    @Override
    List<NPC> getInitNpcs() {
        return List.of(
                NPC.builder()
                        .name("E'rorr")
                        .mp(100)
                        .hp(150)
                        .gold(150)
                        .friendly(true)
                        .action(Action.builder()
                                .conversationText("Are you here for the dragon egg too Traveller?")
                                .childActions(List.of(
                                        Action.builder()
                                                .conversationText("Yes")
                                                .childActions(List.of(Action.builder()
                                                                .conversationText("Me too. I have been searching for this all my life. I'm willing to split the prize if you help me find it.")
                                                                .childActions(List.of(
                                                                        Action.builder()
                                                                                .conversationText("Okay sure!")
                                                                                .childActions(List.of(
                                                                                        Action.builder()
                                                                                                .conversationText("Great! I will forever by in your favor. Lets find it together!")
                                                                                                .build()))
                                                                                .build(),
                                                                        Action.builder()
                                                                                .conversationText("No way, that egg is mine!")
                                                                                .childActions(List.of(
                                                                                        Action.builder()
                                                                                                .conversationText("I get it. But I will not give up. May the best man get the egg.")
                                                                                                .build()))
                                                                                .build()))
                                                        .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("No")
                                                .build(),
                                        Action.builder()
                                                .conversationText("Why do you care?")
                                                .build()))
                                .build())
                        .build(),

                NPC.builder().name("Ms. Spring").mp(50).hp(50).gold(50).friendly(true).build(),

                NPC.builder().name("DeeTeeOh").mp(100).hp(100).gold(50).friendly(false).build(),

                NPC.builder().name("Whitelabel Error").mp(1).hp(1).gold(1).friendly(false).build());
    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Golden dragon scale")
                        .description("Is this really true? The dragon is here?")
                        .typeOfItem(ItemEnum.JUNK)
                        .build(),
                Item.builder()
                        .itemName("Bones")
                        .description("A lot of people died here.")
                        .typeOfItem(ItemEnum.JUNK)
                        .build(),
                Item.builder()
                        .itemName("Broken watch")
                        .description("Isn't this Robert C. Martin's?? What was he doing here?")
                        .typeOfItem(ItemEnum.JUNK)
                        .build()
        );
    }

    @Override
    public String getRoomDescription() {
        return "The mountains leading up to the Lair of the Dragon are filled with lost souls, who search for peace and quiet, after dying terrible deaths. You're not far away from the Golden Dragon Egg now!";
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.imgur.com/rRbG2Jt.jpg";
    }
}
