package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class GraveyardInit extends InitRoom {

    @Override
    public String getName() {
        return "Cemetery of Enlightened Souls";
    }

    @Override
    List<NPC> getInitNpcs() {
        Inventory witchKing = new Inventory();
        witchKing.setItems(List.of(
                Item.builder()
                        .itemName("Black magic spells")
                        .typeOfItem(ItemEnum.ATTACK)
                        .value(80)
                        .mp(35)
                        .damage(40)
                        .description("Powerful spells that help you achieve your goals")
                        .inventory(witchKing)
                        .build(),
                Item.builder()
                        .itemName("Sacrificed souls")
                        .typeOfItem(ItemEnum.JUNK)
                        .description("The Skeleton Dragon loves chowing on them")
                        .inventory(witchKing)
                        .value(200)
                        .build(),
                Item.builder()
                        .itemName("Scythe of the Old One")
                        .typeOfItem(ItemEnum.ATTACK)
                        .description("A powerful weapon that executes decapitation with a clean-cut")
                        .inventory(witchKing)
                        .value(100)
                        .attack(8)
                        .damage(20)
                        .build()));

        return List.of(
                NPC.builder()
                        .name("Witch King")
                        .description("Grandmaster of necromancy")
                        .friendly(true)
                        .hp(8000)
                        .mp(10000)
                        .gold(100)
                        .inventory(witchKing)
                        .imgRef("https://aow.triumph.net/wp-content/uploads/2015/04/Website_Background_Necromancer_Arvik2_Tiny.png")
                        .action(
                                Action.builder()
                                        .conversationText("Who dares disturb my peace?")
                                        .childActions(
                                                List.of(
                                                        Action.builder()
                                                                .conversationText("It seems I took the wrong road...")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("Leave or I'll burn you to ashes")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("I'll take my leave now!")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("You made a wise choice")
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
                                                                .conversationText("I need to speak with the Skeleton Dragon, help me find it!")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("My help won't come cheap. You have to lure out the dragon, and for that, you'll need the souls of the sacrificed")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("I'll give you half the loot I get on my quest as payment!")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("Hmmm, alright. I'm in a good mood, so let's get on with it")
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
                                                                .conversationText("Hey, I'm here to make a deal with you!")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("Very well. In order to make a deal with me, you must sacrifice your soul. What do you say?")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("I'm in! Let's make a deal")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("Great! You won't regret your decision, child. I shall get in contact with you soon. Now get out of my sight")
                                                                                                                                        .build())
                                                                                                                )
                                                                                                                .build(),
                                                                                                        Action.builder()
                                                                                                                .conversationText("WTH?! I don't want to sacrifice my soul! ")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("I see. Where are you going? There's no use running away, your soul will be mine sooner or later anyway!")
                                                                                                                                        .build())
                                                                                                                )
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
                        .name("Skeleton Dragon")
                        .description("Gatekeeper of the Underworld")
                        .friendly(true)
                        .hp(10000)
                        .mp(10000)
                        .gold(10000)
                        .inventory(new Inventory())
                        .imgRef("https://www.pikpng.com/pngl/b/407-4070001_final-fantasy-xiv-dragon-clipart.png")
                        .action(
                                Action.builder()
                                        .conversationText("What brings you here, stranger? There's no return after you pass the Gate of the Underworld")
                                        .childActions(
                                                List.of(
                                                        Action.builder()
                                                                .conversationText("I'm looking for the last Golden Dragon Egg")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("Only those can find the Golden Dragon Egg who prove their worth by defeating X-epson")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("And where do I find this 'X-epson'?")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("That monster is said to be lurking in the shadows, deep within the Forest")
                                                                                                                                        .build()))
                                                                                                                .build()
                                                                                                )
                                                                                        )
                                                                                        .build()
                                                                        )
                                                                )
                                                                .build(),
                                                        Action.builder()
                                                                .conversationText("That's fine, I'm prepared to face any challenge!")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("Beyond this gate, only death awaits you. Do you really want to proceed?")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("I do")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("You have been warned, stranger. Your fate is in your hands now")
                                                                                                                                        .build())
                                                                                                                )
                                                                                                                .build(),
                                                                                                        Action.builder()
                                                                                                                .conversationText("Umm..I changed my mind")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("Good, now leave. Don't waste my time any more")
                                                                                                                                        .build())

                                                                                                                )
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
                        .itemName("Pomegranates")
                        .description("Blood-red delicacy from the Underworld")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Bones")
                        .description("Why are they scattered outside of the graves?")
                        .typeOfItem(ItemEnum.JUNK)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Stake")
                        .description("You better take it with you")
                        .typeOfItem(ItemEnum.ATTACK)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Wilted Lilies")
                        .description("In memory of the deceased")
                        .typeOfItem(ItemEnum.JUNK)
                        .value(0)
                        .build()
        );
    }

    @Override
    public String getRoomDescription() {
        return "Are you here for your own funeral? If not, you'd better leave quickly. The cemetery is the lair of the Witch King, who will easily finish you off with his dark magic. The gate that links together the Underworld with the world of the living is found here.";
    }

    @Override
    public String getRoomImgRef() {
        return "https://cdna.artstation.com/p/assets/images/images/002/748/540/large/yang-qi-.jpg?1465263258";
    }
}
