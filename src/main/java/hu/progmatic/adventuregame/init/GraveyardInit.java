package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
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
                        .build(),
                NPC.builder()
                        .name("Skeleton Dragon")
                        .description("Gatekeeper of the Underworld")
                        .build());
    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Pomegranates")
                        .description("Blood-red fruits")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build(),
                Item.builder()
                        .itemName("Bones")
                        .description("They're everywhere")
                        .typeOfItem(ItemEnum.JUNK)
                        .build(),
                Item.builder()
                        .itemName("Stake")
                        .description("Might be useful")
                        .typeOfItem(ItemEnum.ATTACK)
                        .build(),
                Item.builder()
                        .itemName("Wilted Lilies")
                        .description("In memory of the deceased")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build()
        );
    }

    @Override
    public String getRoomImgRef() {
        return "https://cdna.artstation.com/p/assets/images/images/002/748/540/large/yang-qi-.jpg?1465263258";
    }
}
