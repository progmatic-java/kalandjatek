package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class ForestLakeInit extends InitRoom {
    @Override
    public String getName() {
        return "The Hikari Pool Lake\n";
    }

    @Override
    List<NPC> getInitNpcs() {
        return List.of(
                NPC.builder()
                        .name("Fairy of the Lake")
                        .description("This beautiful fairy loves to seduce young travelers!")
                        .friendly(true)
                        .hp(200)
                        .mp(200)
                        .gold(1000)
                        .inventory(new Inventory())
                        .imgRef("https://cdn.pixabay.com/photo/2018/02/10/21/05/transparent-background-3144550_960_720.png")
                        .action(Action.builder()
                                .conversationText("Hehehehe! Hello there, gorgeous!")
                                .childActions(List.of(
                                        Action.builder()
                                                .conversationText("Hello! Are you the fairy of the Hikari Pool Lake?")
                                                .childActions(
                                                        List.of(
                                                                Action.builder()
                                                                        .conversationText("That's right. Why have you come here?")
                                                                        .childActions(List.of(
                                                                                Action.builder()
                                                                                        .conversationText("I'm in search of the Golden Dragon's Egg! Can you help me?")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("You won't find it here..! It's beyond the lake.. You have to defeat the cursed monster to get there...")
                                                                                                                .build())
                                                                                        )
                                                                                        .build(),
                                                                                Action.builder()
                                                                                        .conversationText("I accidentally wandered here.. How can I get out?")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("Well, if you don't want to stay with me, just go back the way you came!")
                                                                                                                .build())
                                                                                        )
                                                                                        .build()
                                                                        ))
                                                                        .build())
                                                )
                                                .build()
                                ))
                                .build())
                        .build()
        );
    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Fairy Shield")
                        .description("A magical shield that is useful against dark powers!(+6 DEF)")
                        .typeOfItem(ItemEnum.SHIELD)
                        .defence(6)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Fairy Panties")
                        .description("Hmmm... What's that doing floating in the water?(+7 DEF)")
                        .typeOfItem(ItemEnum.SHIELD)
                        .defence(7)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Rotten fish")
                        .description("They exude a disgusting stench!!")
                        .typeOfItem(ItemEnum.JUNK)
                        .value(0)
                        .build()
        );
    }

    @Override
    public String getRoomDescription() {
        return "The serene and gorgeous Hikari Pool Lake in the middle of the Forest of K'aam. It's a tranquil place where you can rest and rejuvenate a little!";
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.pinimg.com/originals/da/52/4e/da524e6c19b3a3933daa7793d9b34ad4.jpg";
    }
}
