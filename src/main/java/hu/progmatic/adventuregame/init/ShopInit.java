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
    return List.of(
        NPC.builder()
            .name("Tezco")
            .description("The owner of the shop, maybe if i'm nice to him, i can get a better deal.")
            .inventory(
                Inventory.builder()
                    .items(List.of(
                            Item.builder()
                                .itemName("Bread")
                                .typeOfItem(ItemEnum.CONSUMABLE)
                                .build(),
                            Item.builder()
                                .itemName("Sword")
                                .typeOfItem(ItemEnum.ATTACK)
                                .build(),
                            Item.builder()
                                .itemName("Mace")
                                .typeOfItem(ItemEnum.ATTACK)
                                .build(),
                            Item.builder()
                                .itemName("Shield")
                                .typeOfItem(ItemEnum.SHIELD)
                                .build(),
                            Item.builder()
                                .itemName("Energy potion")
                                .typeOfItem(ItemEnum.CONSUMABLE)
                                .build(),
                            Item.builder()
                                .itemName("Chili con carne")
                                .typeOfItem(ItemEnum.CONSUMABLE)
                                .build()
                        )
                    )
                    .build()
            )
            .action(Action.builder()
                .conversationText("Welcome to Tezco's shop! My name is Tezco and I'm the humble owner of this store. Please take a look around a let me know if something catches your eyes.")
                .childActions(
                    List.of(
                        Action.builder()
                            .conversationText("I'm looking for Red Dragon scales, did you have some in stock?")
                            .childActions(List.of(
                                    Action.builder()
                                        .conversationText("")
                                        .childActions(
                                            List.of(
                                                Action.builder()
                                                    .build()
                                            )
                                        )
                                        .build()
                                )
                            )
                            .build(),
                        Action.builder()
                            .conversationText("")
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
