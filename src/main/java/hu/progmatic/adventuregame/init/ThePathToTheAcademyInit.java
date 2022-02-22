package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class ThePathToTheAcademyInit extends InitRoom {

    @Override
    public String getName() {
        return "The Path to the Academy";
    }

    @Override
    List<NPC> getInitNpcs() {
        return List.of();
    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Ripped books")
                        .description("Someone got expelled...")
                        .typeOfItem(ItemEnum.JUNK)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Empty potion bottles")
                        .description("These younglings don't care about climate change??")
                        .typeOfItem(ItemEnum.JUNK)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Coins")
                        .description("Someone drop these, now they are mine!")
                        .typeOfItem(ItemEnum.VALUABLE)
                        .value(25)
                        .build()
        );
    }

    @Override
    public String getRoomDescription() {
        return "A long path winds ahead of you, leading up to the Academy of Magic and Programming. While the landscape is breathtakingly beautiful, make sure that you stay safe, as there may be monsters prowling nearby.";
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.imgur.com/E7k4guh.jpg";
    }

    @Override
    public String getRoomAudio() {
        return "music/Damp.mp3";
    }
}

