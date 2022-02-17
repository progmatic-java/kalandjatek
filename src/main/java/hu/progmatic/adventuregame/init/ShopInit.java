package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class ShopInit extends InitRoom {
    @Override
    public String getName() {
        return "Tezco’s Shop";
    }

    @Override
    List<NPC> getInitNpcs() {
        Inventory tezco = Inventory.builder().build();
        tezco.setItems(
                List.of(
                        Item.builder()
                                .itemName("Bread")
                                .description("It's like a beer, but solid and les fun")
                                .typeOfItem(ItemEnum.CONSUMABLE)
                                .value(20)
                                .inventory(tezco)
                                .build(),
                        Item.builder()
                                .itemName("Sword")
                                .description("He attack")
                                .typeOfItem(ItemEnum.ATTACK)
                                .value(50)
                                .inventory(tezco)
                                .build(),
                        Item.builder()
                                .itemName("Mace")
                                .description("So big")
                                .typeOfItem(ItemEnum.ATTACK)
                                .value(60)
                                .inventory(tezco)
                                .build(),
                        Item.builder()
                                .itemName("Shield")
                                .description("He protect")
                                .typeOfItem(ItemEnum.SHIELD)
                                .value(100)
                                .inventory(tezco)
                                .build(),
                        Item.builder()
                                .itemName("Energy potion")
                                .description("Gives you energy to study all night")
                                .typeOfItem(ItemEnum.CONSUMABLE)
                                .value(60)
                                .inventory(tezco)
                                .build(),
                        Item.builder()
                                .itemName("Chili con carne")
                                .description("A hearty meal")
                                .typeOfItem(ItemEnum.CONSUMABLE)
                                .value(80)
                                .inventory(tezco)
                                .build()
                )
        );

        return List.of(
                NPC.builder()
                        .name("Tezco")
                        .description("The owner of the shop, maybe if i'm nice to him, i can get a better deal.")
                        .inventory(new Inventory())
                        .imgRef("https://i.pinimg.com/originals/9f/44/28/9f442894bec6ab5311e1c00ca5735ce7.png")
                        .inventory(tezco)
                        .action(Action.builder()
                                .conversationText("Welcome to Tezco's shop! My name is Tezco and I'm the humble owner of this store. Please take a look around a let me know if something catches your eyes.")
                                .childActions(
                                        List.of(
                                                Action.builder()
                                                        .conversationText("I'm looking for Red Dragon scales.")
                                                        .childActions(List.of(
                                                                        Action.builder()
                                                                                .conversationText("Oh Red Dragon scales. Hmmm interesting choice, but unfortunately I don't have them")
                                                                                .build()
                                                                )
                                                        )
                                                        .build()
                                        )
                                )
                                .build())
                        .build()
        );
    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Candle")
                        .typeOfItem(ItemEnum.JUNK)
                        .build(),
                Item.builder()
                        .itemName("Empty bottle")
                        .typeOfItem(ItemEnum.JUNK)
                        .build()
        );
    }

    @Override
    public String getRoomDescription() {
        return "The shop where you're bound to find anything you'll need on a quest! A galore of weapons and magical items to aid you on your missions. Also, the shopkeeper is a renowned potion brewer. He sometimes adds a bit of poison to his concoctions, so if you fall ill, you might know whom to blame for it.";
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.pinimg.com/originals/40/70/2d/40702d8635092eeb1925f9bfca13dfd2.jpg";
    }
}
