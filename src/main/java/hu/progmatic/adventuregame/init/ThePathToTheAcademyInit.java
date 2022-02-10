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
                        .itemName("Tored books")
                        .description("Someone got expelled...")
                        .typeOfItem(ItemEnum.JUNK)
                        .build(),
                Item.builder()
                        .itemName("Empty potion bottles")
                        .description("These younglings dont care about the climate??")
                        .typeOfItem(ItemEnum.JUNK)
                        .build()
        );
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

