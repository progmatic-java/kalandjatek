package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
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
                        .inventory(Inventory.builder().build())
                        .description("He looks like a nice guy.")
                        .imgRef("https://i.imgur.com/W2UARUn.png")
                        .action(Action.builder()
                                .conversationText("Are you here for the dragon egg too, Traveller?")
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
                                                                                        .conversationText("Great! I will forever be in your favor. Lets find it together!")
                                                                                        .build()))
                                                                        .build(),
                                                                Action.builder()
                                                                        .conversationText("No way, that egg is mine!")
                                                                        .childActions(List.of(
                                                                                Action.builder()
                                                                                        .conversationText("I get it. But I will not give up. May the best man find the egg!")
                                                                                        .build()))
                                                                        .build()))
                                                        .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("No")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("They why are you here?")
                                                                .childActions(List.of(
                                                                        Action.builder()
                                                                                .conversationText("None of your business.")
                                                                                .childActions(List.of(
                                                                                        Action.builder()
                                                                                                .conversationText("No need to be rude, my friend. Our time on earth is too short, don't spew negativity. Take care. ")
                                                                                                .build()))
                                                                                .build(),
                                                                        Action.builder()
                                                                                .conversationText("I just came here to enjoy the view.")
                                                                                .childActions(List.of(
                                                                                        Action.builder()
                                                                                                .conversationText("I love your perspective of the world, we should have a beer when I finish my quest. Take care, my friend.")
                                                                                                .build()))
                                                                                .build()))
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Why do you care?")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("No need to be rude my friend. Our time on earth is too short, don't spew negativity. Take care. ")
                                                                .build()))
                                                .build()))
                                .build())
                        .build(),

                NPC.builder()
                        .name("Ms. Spring")
                        .mp(50)
                        .hp(50)
                        .gold(50)
                        .friendly(true)
                        .inventory(Inventory.builder().build())
                        .imgRef("https://i.imgur.com/dh8PT14.png")
                        .description("What is she doing here? She doesn't look like someone who belongs here.")
                        .action(Action.builder()
                                .conversationText("Please don't hurt me! I'm just gathering some fresh water for my sick grandma!")
                                .childActions(List.of(
                                        Action.builder()
                                                .conversationText("I would never!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("Sorry for being in the way, I will leave!")
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Then get out of the way you pleb!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("AAAHH!")
                                                                .build()))
                                                .build()))
                                .build())
                        .build(),

                NPC.builder()
                        .name("DeeTeeOh")
                        .mp(100)
                        .hp(100)
                        .gold(50)
                        .friendly(true)
                        .inventory(Inventory.builder().build())
                        .imgRef("https://i.imgur.com/YgAWr7l.png")
                        .description("He's scary, I hope he leaves me alone.")
                        .action(Action.builder()
                                .conversationText("Stop while you can...")
                                .childActions(List.of(
                                        Action.builder()
                                                .conversationText("Who are you?")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("I'm DeeTeeOh and I will have that egg! So get out of my way!")
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Are you here for the egg too?")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("Yes and I will have that egg! So get out of my way or feel my wrath!")
                                                                .build()))
                                                .build()))
                                .build())
                        .build(),

                NPC.builder()
                        .name("Whitelabel Error")
                        .mp(50)
                        .hp(1)
                        .friendly(false)
                        .damage(6)
                        .attack(5)
                        .defence(4)
                        .imgRef("https://i.imgur.com/j1KaqbJ.jpg")
                        .description("Is this...??")
                        .inventory(Inventory.builder().build())
                        .action(Action.builder()
                                .conversationText("This application has no explicit mapping for /error")
                                .childActions(List.of(
                                        Action.builder()
                                                .conversationText("Not you again!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("You will see me again...")
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("OH GOD NO")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("You will see me again...")
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Hello darkness my old friend")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("You will see me again...")
                                                                .build()))
                                                .build()))
                                .build())
                        .build());
    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Golden dragon scale")
                        .description("Is this really true? The dragon is here?")
                        .typeOfItem(ItemEnum.JUNK)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Bones")
                        .description("A lot of people died here.")
                        .typeOfItem(ItemEnum.JUNK)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Broken watch")
                        .description("Isn't this Robert C. Martin's?? What was he doing here?")
                        .typeOfItem(ItemEnum.JUNK)
                        .value(0)
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
