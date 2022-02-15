package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
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
                        .action(
                                Action.builder()
                                        .conversationText("Welcome sexy traveler to my inn! What can I do for you?")
                                        .childActions(
                                                List.of(
                                                        Action.builder()
                                                                .conversationText("Hello, I just want a beer!")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("The first beer is on the house! Anything else I can get for you?")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("No thanks!")
                                                                                                                .build()
                                                                                                )
                                                                                        )
                                                                                        .build()
                                                                        )
                                                                )
                                                                .build(),
                                                        Action.builder()
                                                                .conversationText("Hey, I'm actually in search of the town's brothel")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("The Red Try and Catch? You'll find it next to the Church of Holy Joe!")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("Thanks!")
                                                                                                                .build()
                                                                                                )
                                                                                        )
                                                                                        .build()
                                                                        )
                                                                )
                                                                .build(),
                                                        Action.builder()
                                                                .conversationText("Hi, I want to fight some big trolls!")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("Oh! Are you in search of a quest? Maybe I have a mission you'd be interested in...")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("Oh really? Tell me more!")
                                                                                                                .build()
                                                                                                )
                                                                                        )
                                                                                        .build()
                                                                        )
                                                                )
                                                                .build()
                                                )
                                        )
                                        .build()
                        )
                        .build()
//                NPC.builder()
//                        .name("Switcher")
//                        .description("Your old friend from the prison.")
//                        .friendly(true)
//                        .hp(100)
//                        .mp(20)
//                        .gold(500)
//                        .imgRef("https://i.imgur.com/3IOuWgJ.png")
//                        .build()
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
    public String getRoomDescription() {
        return "The famous inn of Thorncall, where every tired traveller can replenish themselves at. Even though the Black Hole Inn is a smokey space where prostitutes and thieves love to gather, it is also a friendly and warm tavern where you can refresh yourself with a pint of beer and a piece of fine bread.";
    }

    @Override
    public String getRoomImgRef() {
        return "https://wallpaperaccess.com/full/6079735.png";
    }

    @Override
    public String getRoomAudio() {
        return "music/Tavern.mp3";
    }
}
