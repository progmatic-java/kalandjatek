package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class MainSquareInit extends InitRoom {
    @Override
    public String getName() {
        return "Ko’lossy Square";
    }

    @Override
    List<NPC> getInitNpcs() {
        return List.of(
                NPC.builder()
                        .name("Trace")
                        .description("A drunk guy sleeping outside of The Black Hole Inn.")
                        .imgRef("https://i.imgur.com/42nO5xi.png")
                        .friendly(true)
                        .inventory(Inventory.builder().build())
                        .action(
                                Action.builder()
                                        .conversationText("*Trace snoring loudly, while exhaling his nearly poisonous breath, filled with alcohol. You shake his shoulder, but it seems you can't wake him up now.*")
                                        .build()
                        )
                        .build(),
                NPC.builder()
                        .name("Tomcat")
                        .imgRef("https://i.imgur.com/sz1g3oR.png")
                        .description("The town's beggar and thief hunter.")
                        .friendly(true)
                        .inventory(Inventory.builder().build())
                        .action(
                                Action.builder()
                                        .conversationText("Hey traveller, can you toss me some coins? I'm living on the streets and slowly starving to DEATH!")
                                        .childActions(
                                                List.of(
                                                        Action.builder()
                                                                .conversationText("I'm not giving you anything, go and get some work!")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("Fuck you!")
                                                                                        .build()
                                                                        )
                                                                )
                                                                .build(),
                                                        Action.builder()
                                                                .conversationText("Sorry, I ran out of coins.")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("LIAR! Do you think I'm blind? I can see that your purse is filled with coins. I'm cursing you for 100 years of serious back pain!")
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
                        .name("Admina")
                        .description("The town's prostitute")
                        .friendly(true)
                        .inventory(Inventory.builder().build())
                        .imgRef("https://i.pinimg.com/originals/d6/53/a5/d653a5283092eccf6135ef4b36699271.png")
                        .action(
                                Action.builder()
                                        .conversationText("If you want to have some 'fun time' with me, talk to Lady Regexxx in The Red Try and Catch.")
                                        .build()
                        )
                        .build()
        );
    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Stale bread")
                        .description("I want a sandwich so bad.")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .value(0)
                        .hp(5)
                        .build(),
                Item.builder()
                        .itemName("Bucket")
                        .description("It has a hole in it.")
                        .typeOfItem(ItemEnum.SHIELD)
                        .defence(1)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Suspicious letter")
                        .description("It says BEWARE OF THE FORREST")
                        .typeOfItem(ItemEnum.JUNK)
                        .value(0)
                        .build()
        );
    }

    @Override
    public String getRoomDescription() {
        return "Every road leads to the main square of Thorncall. Watch out for any thieves who may steal your purse!";
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.pinimg.com/originals/30/91/a0/3091a017b704dd6c24339700f4a16ff4.jpg";
    }
}
