package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class RoadToTheForestInit extends InitRoom {

    @Override
    public String getName() {
        return "Road to the Forest of K'aam";
    }

    @Override
    List<NPC> getInitNpcs() {
        return List.of(
                NPC.builder()
                        .name("Returno")
                        .description("He just came out of the woods. He looks like he was hurt.")
                        .friendly(true)
                        .imgRef("https://i.imgur.com/jzbByKf.png")
                        .inventory(new Inventory())
                        .action(
                                Action.builder()
                                        .conversationText("You there! Can you help me?")
                                        .childActions(
                                                List.of(
                                                        Action.builder()
                                                                .conversationText("Hello, are you okay? What happened to your ear?")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("I was attacked by an angry monster inside the forest... It tore my ear off.... I wouldn't go there if I were you.")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("I'm looking for the last Golden Dragon Egg and I heard it's beyond the forest. I need to get across..")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("The Golden Dragon Egg? I've heard about it as well, but only in legends and myths. But you won't get across the forest if you can't defeat the monster...")
                                                                                                                                        .childActions(
                                                                                                                                                List.of(
                                                                                                                                                        Action.builder()
                                                                                                                                                                .conversationText("Do you know how to slay the beast?")
                                                                                                                                                                .childActions(
                                                                                                                                                                        List.of(
                                                                                                                                                                                Action.builder()
                                                                                                                                                                                        .conversationText("If you have a source of fire... Like a torch, try attacking the monster with it.. Fire might just be effective against it.")
                                                                                                                                                                                        .childActions(List.of(
                                                                                                                                                                                                Action.builder()
                                                                                                                                                                                                        .conversationText("Thank you sir! Make sure to drink a magic potion for your wounds!!")
                                                                                                                                                                                                        .childActions(List.of(Action.builder().conversationText("I will... But my ear probably won't grow back..").build()))
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
                                                                                                                .conversationText("Do I have any other choice?!")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("To leave.. Don't go into the forest if you want to live..")
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
                                                                .conversationText("Are you gravely injured? Do you need a potion?")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("Not gravely no... But that monster in the forest attacked me and tore off my ear.. It's definitely something cursed..")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("What kind of monster is it?")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("I have no idea.. You know I'm a warrior and I've killed an O'rem.. But this beast was completely different..")
                                                                                                                                        .childActions(List.of(
                                                                                                                                                Action.builder()
                                                                                                                                                        .conversationText("Does it have any weak points? I need to go inside the forest to defeat it..")
                                                                                                                                                        .childActions(List.of(Action.builder().conversationText("Try something like fire or a torch..! All the best, young warrior!").build()))
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
                                                                .conversationText("Hi, should I send for help?")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("Not if you have a bandage or a healing potion...")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("I have a potion... Here. Take it, you need it more than me.")
                                                                                                                .childActions(List.of(Action.builder()
                                                                                                                        .conversationText("Thanks, young warrior.")
                                                                                                                        .build()))
                                                                                                                .build(),
                                                                                                        Action.builder()
                                                                                                                .conversationText("I don't have either.. But I hope you recover soon.")
                                                                                                                .childActions(List.of(Action.builder()
                                                                                                                        .conversationText("Oh.. Thank you anyhow. And stay away from the forest! It's cursed!")
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
                        .build()
        );
    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Pebble")
                        .description("It's not much but at least it can do a bit of damage")
                        .typeOfItem(ItemEnum.ATTACK)
                        .attack(1)
                        .damage(1)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Magic Mushroom")
                        .description("I feel funny when I eat this")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .hp(10)
                        .value(0)
                        .build()
        );
    }

    @Override
    public String getRoomDescription() {
        return "The road ahead is somewhat dusty and has been tread on many times before! The Forest of K'aam lies ahead, it may take up to a day or two to reach the woods!";
    }

    @Override
    public String getRoomImgRef() {
        return "https://www.worldanvil.com/uploads/images/085e0ae64f26ed79426cdb5c1a3bb060.jpg";
    }
}
