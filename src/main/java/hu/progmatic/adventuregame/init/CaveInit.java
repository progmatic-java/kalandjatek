package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class CaveInit extends InitRoom{
    @Override
    public String getName() {
        return "The Lair of the Chicken Dragon, who was originally born a Phoenix";
    }

    @Override
    List<NPC> getInitNpcs() {
        return List.of(
                NPC.builder()
                        .name("The Chicken Dragon")
                        .description("A Chicken Dragon, who was originally born a Phoenix")
                        .friendly(true)
                        .hp(500)
                        .mp(50)
                        .gold(1000)
                        .action(Action.builder()
                                .conversationText("What are you doing here, in my lair?")
                                .childActions(List.of(
                                        Action.builder()
                                                .conversationText("I have come here for the Golden Dragon Egg!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("Bwak bwak bwak")
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Holy Joe, A CHICKEN DRAGON??!!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("Bwak bwak bwak")
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Do you want to dance?!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("No.")
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
                        .itemName("Assortment of bones")
                        .description("What else did you expect in a dragon lair?")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build(),
                Item.builder()
                        .itemName("Jewels")
                        .description("The dragon seems to have a whole collection of jewels and gold.")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build()
        );
    }

    @Override
    public String getRoomDescription() {
        return "You have finally arrived at the Lair of the Dragon to retrieve the Golden Egg! Are you prepared for whatever awaits you?";
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.ytimg.com/vi/i94BOiRbqDU/maxresdefault.jpg";
    }

}
