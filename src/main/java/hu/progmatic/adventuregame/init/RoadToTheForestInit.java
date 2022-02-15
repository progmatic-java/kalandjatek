package hu.progmatic.adventuregame.init;

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
                        .imgRef("https://w7.pngwing.com/pngs/253/208/png-transparent-dungeons-dragons-pathfinder-roleplaying-game-trickster-character-concept-art-others-weapon-art-pathfinder-roleplaying-game.png")
                        .action(
                                Action.builder()
                                        .conversationText("You there! Can you help me?")
                                        .childActions(
                                                List.of(
                                                        Action.builder()
                                                                .conversationText("Hello, are you okay?")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("I was attacked by an angry monster inside the forest... I wouldn't go there if I were you.")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("I'm looking for the last gold dragon egg and I heard it's beyond the forest. I need to get across..")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("A gold dragon egg? I've heard about it as well. But you won't get across the forest if you can't defeat the monster.")
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
                                                                                                                                                                                                        .childActions(List.of(Action.builder().conversationText("Will do!").build()))
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
                                                                                                                                        .conversationText("To leave..! Don't go into the forest if you want to live..")
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
                                                                                        .conversationText("Not gravely no... But that monster in the forest attacked me.. It's definitely something cursed..")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("What kind of monster is it?")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("I have no idea.. You know I'm a warrior and I've killed a Fos'Orem.. But this beast was completely different..")
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
                        .build(),
                Item.builder()
                        .itemName("Magic Mushroom")
                        .description("I feel funny when I eat this")
                        .typeOfItem(ItemEnum.CONSUMABLE)
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
