package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
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
        Inventory burrows = new Inventory();
        burrows.setItems(List.of(
                Item.builder()
                        .itemName("Beer")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .value(10)
                        .description("A little lukewarm and tastes like horse piss but it's fine.")
                        .inventory(burrows)
                        .build(),
                Item.builder()
                        .itemName("Wine")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .description("Is this from a tablet?")
                        .inventory(burrows)
                        .value(15)
                        .build(),
                Item.builder()
                        .itemName("Cracker")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .description("Mmmm sooo tastyyy and crispyyy.")
                        .inventory(burrows)
                        .value(5)
                        .build()));

        return List.of(
                NPC.builder()
                        .name("Burrows")
                        .description("The drunk owner of The Black Hole Inn.")
                        .friendly(true)
                        .hp(50)
                        .mp(50)
                        .gold(1000)
                        .inventory(burrows)
                        .imgRef("https://i.imgur.com/v436jHn.png")
                        .action(
                                Action.builder()
                                        .conversationText("Welcome traveller to my inn! What can I do for you?")
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
                                                                                                                .conversationText("I'm actually in search for the last Golden Dragon Egg and I heard it's here somewhere in Thorncall. Is it true?")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("The Golden Dragon egg? That's a bedtime story that parents tell their kids to help them sleep. Don't tell me you believe in that.")
                                                                                                                                        .childActions(
                                                                                                                                                List.of(
                                                                                                                                                        Action.builder()
                                                                                                                                                                .conversationText("I don't, but if it exists, it is the most valuable item in all of Progmatique.")
                                                                                                                                                                .childActions(
                                                                                                                                                                        List.of(
                                                                                                                                                                                Action.builder()
                                                                                                                                                                                        .conversationText("You're crazy my friend! But if you want to begin somewhere, I would begin in Holy Joe's Church. That old guy had a lot of secrets.")
                                                                                                                                                                                        .childActions(List.of(
                                                                                                                                                                                                Action.builder()
                                                                                                                                                                                                        .conversationText("Thanks!")
                                                                                                                                                                                                        .childActions(List.of(
                                                                                                                                                                                                                Action.builder()
                                                                                                                                                                                                                        .conversationText("Have a good day my friend!")
                                                                                                                                                                                                                        .build()))
                                                                                                                                                                                                        .build()))
                                                                                                                                                                                        .build())
                                                                                                                                                                )
                                                                                                                                                                .build()
                                                                                                                                                )
                                                                                                                                        )
                                                                                                                                        .build()
                                                                                                                        )
                                                                                                                )
                                                                                                                .build(),
                                                                                                        Action.builder()
                                                                                                                .conversationText("No thanks!")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("Have a good day my friend!")
                                                                                                                                        .build())
                                                                                                                )
                                                                                                                .build()
                                                                                                )
                                                                                        )
                                                                                        .build()
                                                                        )
                                                                )
                                                                .build(),
                                                        Action.builder()
                                                                .conversationText("Hey, I'm actually in search for the last Golden Dragon Egg and I heard it's here somewhere in Thorncall. Is it true?")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("The Golden Dragon Egg? That's a bedtime story that parents tell their kids to help them sleep. Don't tell me you believe in that.")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("I don't, but if it exists, it is the most valuable item in all of Progmatique.")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("You're crazy my friend! But if you want to begin somewhere, I would begin in Holy Joe's Church. That old guy had a lot of secrets.")
                                                                                                                                        .childActions(List.of(
                                                                                                                                                Action.builder()
                                                                                                                                                        .conversationText("Thanks!")
                                                                                                                                                        .childActions(List.of(Action.builder().conversationText("Have a good day my friend!").build()))
                                                                                                                                                        .build()))
                                                                                                                                        .build())
                                                                                                                )
                                                                                                                .build()
                                                                                                )
                                                                                        )
                                                                                        .build()
                                                                        )
                                                                )
                                                                .build(),
                                                        Action.builder()
                                                                .conversationText("Hi, Im searching for a job opportunity.")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("Oh! Maybe I have a mission you'd be interested in...")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("Oh really? Tell me more!")
                                                                                                                .childActions(List.of(Action.builder()
                                                                                                                        .conversationText("There's a forest just outside of the town. It has a very valuable ring that I need. I can give you a lot of money if you bring it back to me. But beware, legends say the forest is cursed!")
                                                                                                                        .childActions(List.of(Action.builder()
                                                                                                                                        .conversationText("Cursed?")
                                                                                                                                        .childActions(List.of(Action.builder()
                                                                                                                                                .conversationText("Yes, something lurks there... Something very dangerous. People call it X-epsion. Even it's name is scary.")
                                                                                                                                                .childActions(List.of(
                                                                                                                                                        Action.builder()
                                                                                                                                                                .conversationText("Wow, yeah no sorry, I don't want to risk my life.")
                                                                                                                                                                .childActions(List.of(
                                                                                                                                                                        Action.builder()
                                                                                                                                                                                .conversationText("I get it, if you change your mind, you know where to find me.")
                                                                                                                                                                                .build()))
                                                                                                                                                                .build(),
                                                                                                                                                        Action.builder()
                                                                                                                                                                .conversationText("Sounds scary... I'm in!")
                                                                                                                                                                .childActions(List.of(
                                                                                                                                                                        Action.builder()
                                                                                                                                                                                .conversationText("Great! Please bring it back to me. I will give you a lot of coins and free beers for life!")
                                                                                                                                                                                .build()))
                                                                                                                                                                .build()))
                                                                                                                                                .build()))
                                                                                                                                        .build(),
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("WHATEVER I NEED THE COINS")
                                                                                                                                        .childActions(List.of(Action.builder()
                                                                                                                                                .conversationText("Great! Please bring it back to me. I will give you a lot of coins and free beers for life!")
                                                                                                                                                .build()))
                                                                                                                                        .build()))
                                                                                                                        .build()))
                                                                                                                .build(),
                                                                                                        Action.builder()
                                                                                                                .conversationText("I'm not the one to boss around...")
                                                                                                                .childActions(List.of(Action.builder()
                                                                                                                        .conversationText("Geez calm down, I was just asking.")
                                                                                                                        .build()))
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
                        .build(),
                NPC.builder()
                        .name("Switcher")
                        .description("Your old friend from the prison.")
                        .friendly(true)
                        .hp(100)
                        .mp(20)
                        .gold(500)
                        .imgRef("https://i.imgur.com/3IOuWgJ.png")
                        .inventory(new Inventory())
                        .action(
                                Action.builder()
                                        .conversationText("Wait, i know you!")
                                        .childActions(List.of(
                                                Action.builder()
                                                        .conversationText("Switcher is that you!?")
                                                        .childActions(List.of(
                                                                Action.builder()
                                                                        .conversationText("Yes, my friend! How are you? So good to see you!")
                                                                        .childActions(List.of(
                                                                                Action.builder()
                                                                                        .conversationText("I'm good! Can you help me? I'm here to search for the last Golden Dragon Egg and I heard it's here somewhere in Thorncall.")
                                                                                        .childActions(List.of(
                                                                                                Action.builder()
                                                                                                        .conversationText("Sorry I haven't heard about it. Maybe ask Burrows, he knows about everything.")
                                                                                                        .build()))
                                                                                        .build()))
                                                                        .build()))
                                                        .build(),
                                                Action.builder()
                                                        .conversationText("Sorry, you've got the wrong person.")
                                                        .childActions(List.of(
                                                                Action.builder()
                                                                        .conversationText("Really? I could've swore... Sorry, my bad.")
                                                                        .build()))
                                                        .build()
                                        ))
                                        .build())

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
