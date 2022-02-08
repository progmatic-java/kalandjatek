package hu.progmatic.kalandjatek.init;

import hu.progmatic.kalandjatek.Item;
import hu.progmatic.kalandjatek.ItemEnum;
import hu.progmatic.kalandjatek.NPC.NPC;

import java.util.List;

public class CellarInit extends InitRoom {
    public CellarInit() {
        super();
        setName("Inn cellar");
    }

    @Override
    List<NPC> getInitNpcs() {
        return List.of(NPC.builder().name("Mad dog").description("Precious pet of Burrows").build());
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
                        .itemName("Wine")
                        .description("It has a lot of antioxidants")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build(),
                Item.builder()
                        .itemName("Dead body")
                        .description("Dog food")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build()
        );
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.imgur.com/qqBe4IX.jpg";
    }
}
