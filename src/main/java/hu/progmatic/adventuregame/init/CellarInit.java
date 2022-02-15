package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class CellarInit extends InitRoom {
    @Override
    public String getName() {
        return "Cellar of Black Hole Inn";
    }

    @Override
    List<NPC> getInitNpcs() {
        return List.of(
                NPC.builder()
                        .name("Mad dog")
                        .description("Precious pet of Burrows.He doesn't seem friendly.")
                        .imgRef("https://i.imgur.com/s4KyB2S.png")
                        .friendly(false)
                        .action(Action.builder()
                                .conversationText("*Agressive growling")
                                .childActions(List.of(
                                        Action.builder()
                                                .conversationText("YIKES!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("WUF WUF WUF")
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Holy Joe, what is that??!!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("WUF WUF WUF")
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Begone you mutt!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("*Whimpers away")
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
                        .itemName("Imported Beer")
                        .description("It's a holy drink from Ko'olossy")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build(),
                Item.builder()
                        .itemName("Fine Wine")
                        .description("It has a lot of antioxidants")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build(),
                Item.builder()
                        .itemName("Dead body")
                        .description("Dog food")
                        .typeOfItem(ItemEnum.JUNK)
                        .build()
        );
    }

    @Override
    public String getRoomDescription() {
        return "Large barrels of wine and beer await thirsty customers, but beware, the cellar can be a dangerous place! There's a mad dog growling in the dark shadows, which seems to have an insatiable appetite... Especially for fresh meat..";
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.imgur.com/qqBe4IX.jpg";
    }

    @Override
    public String getRoomAudio() {
        return "music/Damp.mp3";
    }
}
