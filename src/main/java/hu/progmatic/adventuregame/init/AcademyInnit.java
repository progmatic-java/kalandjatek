package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class AcademyInnit extends InitRoom{@Override

public String getName() {
    return "Academy of Magic and Programming";
}

    @Override
    List<NPC> getInitNpcs() {
        return List.of(
                NPC.builder()
                        .name("Robert C. Martin")
                        .description("The headmaster of the Academy")
                        .build()
        );
    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Clean Code")
                        .description("The most valuable thing in all of K'aam.")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build(),
                Item.builder()
                        .itemName("Old scrolls")
                        .description("They are too old, I can't even read them.")
                        .typeOfItem(ItemEnum.JUNK)
                        .build(),
                Item.builder()
                        .itemName("The key to Holy Joe's Tomb")
                        .description("What is this doing here? I better take it with me")
                        .typeOfItem(ItemEnum.KEY)
                        .build()
        );
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.pinimg.com/originals/68/d2/6c/68d26cfe85a1557d62cb69d4f090fb49.jpg";
    }

    @Override
    public String getRoomAudio() {
        return "music/Damp.mp3";
    }
}

