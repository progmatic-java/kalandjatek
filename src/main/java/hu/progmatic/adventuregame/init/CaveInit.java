package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class CaveInit extends InitRoom {
    @Override
    public String getName() {
        return "The Lair of the Dragon";
    }

    @Override
    List<NPC> getInitNpcs() {

        Inventory phoneix = new Inventory();
        phoneix.setItems(List.of(
                Item.builder()
                        .itemName("The Golden Dragon Egg")
                        .description("Wow! I can't believe it's mine!")
                        .typeOfItem(ItemEnum.VALUABLE)
                        .inventory(phoneix)
                        .build()));

        return List.of(
                NPC.builder()
                        .name("The Chicken Dragon")
                        .description("A Chicken Dragon, who was originally born a Phoenix")
                        .friendly(false)
                        .hp(80)
                        .mp(50)
                        .gold(1000)
                        .attack(10)
                        .defence(10)
                        .inventory(phoneix)
                        .imgRef("https://i.imgur.com/ZZsNPwW.png")
                        .action(Action.builder()
                                .conversationText("What are you doing here, in my lair?")
                                .childActions(List.of(
                                        Action.builder()
                                                .conversationText("I have come here for the Golden Dragon Egg!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("No way I'm gonna give it to you! You have to get it out of my cold, dead...wings!")
                                                                .childActions(List.of(
                                                                        Action.builder()
                                                                                .conversationText("Yeah, no. I rather leave, I'm tired. I don't even want your egg.")
                                                                                .childActions(List.of(
                                                                                        Action.builder()
                                                                                                .conversationText("Go and don't come back!")
                                                                                                .build()))
                                                                                .build(),
                                                                        Action.builder()
                                                                                .conversationText("So it shall be...")
                                                                                .childActions(List.of(
                                                                                        Action.builder()
                                                                                                .conversationText("You're making the biggest mistake of your life!")
                                                                                                .build()))
                                                                                .build()))
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Holy Joe, A CHICKEN DRAGON??!!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("Don't laugh at me! I was born a Phoenix, I don't know what happened!")
                                                                .childActions(List.of(
                                                                        Action.builder()
                                                                                .conversationText("Soooo... are you a chicken or a dragon?")
                                                                                .childActions(List.of(
                                                                                        Action.builder()
                                                                                                .conversationText("I'm actually both... and neither...but I was born a Phoenix. What do you want from me?")
                                                                                                .childActions(List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("I have come here for the Golden Dragon Egg!")
                                                                                                                .childActions(List.of(
                                                                                                                        Action.builder()
                                                                                                                                .conversationText("No way I'm gonna give it to you! You have to get it out of my cold, dead...wings!")
                                                                                                                                .childActions(List.of(
                                                                                                                                        Action.builder()
                                                                                                                                                .conversationText("Yeah, no. I rather leave, I'm tired. I don't even want your egg.")
                                                                                                                                                .childActions(List.of(
                                                                                                                                                        Action.builder()
                                                                                                                                                                .conversationText("Go and don't come back!")
                                                                                                                                                                .build()))
                                                                                                                                                .build(),
                                                                                                                                        Action.builder()
                                                                                                                                                .conversationText("So it shall be...")
                                                                                                                                                .childActions(List.of(
                                                                                                                                                        Action.builder()
                                                                                                                                                                .conversationText("You're making the biggest mistake of your life!")
                                                                                                                                                                .build()))
                                                                                                                                                .build()))
                                                                                                                                .build()))
                                                                                                                .build()
                                                                                                ))
                                                                                                .build()))
                                                                                .build(),
                                                                        Action.builder()
                                                                                .conversationText("I'm not laughing!...BWHAHAHAHA")
                                                                                .childActions(List.of(
                                                                                        Action.builder()
                                                                                                .conversationText("I will kill you!")
                                                                                                .build()))
                                                                                .build()))
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Do you want to dance?!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("No. Just leave me alone!")
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
                        .typeOfItem(ItemEnum.JUNK)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Jewels")
                        .description("The dragon seems to have a whole collection of jewels and gold.")
                        .typeOfItem(ItemEnum.VALUABLE)
                        .value(0)
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
