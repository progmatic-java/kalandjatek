package hu.progmatic.adventuregame.init;

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
        return List.of(
                NPC.builder()
                        .name("Witch King")
                        .description("Grandmaster of necromancy")
                        .friendly(false)
                        .hp(8000)
                        .mp(10000)
                        .gold(100)
                        .imgRef("https://aow.triumph.net/wp-content/uploads/2015/04/Website_Background_Necromancer_Arvik2_Tiny.png")
                        .action(
                                Action.builder()
                                        .conversationText("Who dares disturb my peace?")
                                        .childActions(
                                                List.of(
                                                        Action.builder()
                                                                .conversationText("Hi, sorry, it seems I took the wrong road")
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
                                                                .conversationText("I'm sorry to disturb you but I need your help")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("My help won't come cheap. What can you offer?")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("I have nothing, I'll return another time")
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
                        .imgRef("https://mpng.subpng.com/20180615/rsq/kisspng-dragon-s-dogma-skeleton-the-elder-scrolls-v-skyri-dungeons-dragons-order-of-the-griffon-5b2346556c5791.1398915015290384214438.jpg")
                        .action(
                                Action.builder()
                                        .conversationText("What brings you here, stranger? There's no return after you pass the Gate of the Underworld")
                                        .childActions(
                                                List.of(
                                                        Action.builder()
                                                                .conversationText("I'm looking for something")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("Beyond this gate, only death awaits you. Do you want to proceed?")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("Yes. I have no other choice")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("You have been warned, stranger")
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
                        ).build()
        );

    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Pomegranates")
                        .description("Blood-red delicacy from the Underworld")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build(),
                Item.builder()
                        .itemName("Bones")
                        .description("Why are they scattered outside of the graves?")
                        .typeOfItem(ItemEnum.JUNK)
                        .build(),
                Item.builder()
                        .itemName("Stake")
                        .description("You better take it with you")
                        .typeOfItem(ItemEnum.ATTACK)
                        .build(),
                Item.builder()
                        .itemName("Wilted Lilies")
                        .description("In memory of the deceased")
                        .typeOfItem(ItemEnum.JUNK)
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
