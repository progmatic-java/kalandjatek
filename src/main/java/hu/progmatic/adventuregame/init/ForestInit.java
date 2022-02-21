package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class ForestInit extends InitRoom {

    @Override
    public String getName() {
        return "The Dark Forest of K'aam\n";
    }

    @Override
    List<NPC> getInitNpcs() {

        Inventory xepsion = new Inventory();
        xepsion.setItems(List.of(
                Item.builder()
                        .itemName("The Ring of X-epson")
                        .description("X-epson's magic is protecting me in battles!")
                        .typeOfItem(ItemEnum.SHIELD)
                        .defence(6)
                        .inventory(xepsion)
                        .value(0)
                        .build()));

        return List.of(
                NPC.builder()
                        .name("Ugly Troll")
                        .description("As big and ugly as trolls get!")
                        .imgRef("https://i.imgur.com/DAcDKOP.png")
                        .friendly(true)
                        .inventory(new Inventory())
                        .action(Action.builder()
                                .conversationText("WAAAAAAAAA!")
                                .childActions(List.of(
                                        Action.builder()
                                                .conversationText("HOLY JOE, WHAT THE HELL!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("HUNGRYYYYYY!! EAT!!")
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Wow, you scared me!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("SMALL CREATURE! IM WILL EAT YOU!")
                                                                .build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Get away you troll! You are no match for me!")
                                                .childActions(List.of(
                                                        Action.builder()
                                                                .conversationText("TASTY FLESH CREATURE!")
                                                                .build()))
                                                .build()))
                                .build())
                        .build(),
                NPC.builder()
                        .name("X-epson")
                        .description("A being from another dimension that haunts the forest.")
                        .friendly(false)
                        .attack(5)
                        .defence(7)
                .imgRef("https://i.imgur.com/bjiVhGr.png")
                        .mp(20)
                        .hp(40)
                        .gold(100)
                        .inventory(xepsion)
                        .action(
                                Action.builder()
                                        .conversationText("Grrrrrrrrrrrrrr. I am X-epson! I will eat you!!")
                                        .childActions(
                                                List.of(
                                                        Action.builder()
                                                                .conversationText("X-epson...? Can you make an exception for me? Don't eat me!")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("There is a runtime exception...")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("Does that mean I need to run... Now?")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("GRRRRRRRRRRRRR*%#@*+×°!!!!!!!!")
                                                                                                                                        .build())
                                                                                                                )
                                                                                                                .build()
                                                                                                )
                                                                                        )
                                                                                        .build()
                                                                        )
                                                                )
                                                                .build()
                                                )
                                        )
                                        .build()
                        ).
                        build()
        );
    }


    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Poisonous berries")
                        .description("I should not eat this, but it tastes so good.")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .hp(0)
                        .value(0)
                        .build(),
                Item.builder()
                        .itemName("Returno's left ear")
                        .description("Ew!")
                        .typeOfItem(ItemEnum.JUNK)
                        .value(0)
                        .build()
        );
    }

    @Override
    public String getRoomDescription() {
        return "Thick creepers dangle from the trees and the trees create a shroud of darkness. You can feel that something is amiss... Like something is constantly watching you.";
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.pinimg.com/originals/bf/1d/16/bf1d1605e62b31685316fa5ab1f7a32d.jpg";
    }
}