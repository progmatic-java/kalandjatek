package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class RoadToTheForestInit extends InitRoom {

    @Override
    public String getName() {
        return "Road to the Forest of Ka’am";
    }

    @Override
    List<NPC> getInitNpcs() {
        return List.of(NPC.builder().name("Returno").description("He just came out of the woods. He looks hurt.").build());
    }


    @Override
    List<Item> getInitItems() {
        return null;
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.imgur.com/qqBe4IX.jpg";
    }
}
