package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class ChurchDungeonsInit extends InitRoom {

    @Override
    public String getName() {
        return "Tomb of Holy Joe";
    }

    @Override
    List<NPC> getInitNpcs() {
        Inventory dungeonKeeper = new Inventory();
        dungeonKeeper.setItems(List.of(
                Item.builder()
                        .itemName("Skeleton key")
                        .typeOfItem(ItemEnum.KEY)
                        .description("Can open skeleton doors, e.g. in the Cemetery")
                        .inventory(dungeonKeeper)
                        .value(49)
                        .build()));

        return List.of(
                NPC.builder()
                        .name("Dungeon keeper")
                        .description("A robust man with a ferocious aura")
                        .imgRef("https://i.imgur.com/zf7gYuJ.png")
                        .friendly(false)
                        .mp(50)
                        .hp(20)
                        .attack(4)
                        .defence(12)
                        .damage(10)
                        .gold(100)
                        .inventory(dungeonKeeper)
                        .action(Action.builder()
                                .conversationText("What are you doing here?! This area is permitted!")
                                .childActions(List.of(
                                        Action.builder()
                                                .conversationText("Sorry! I came here by accident.")
                                                .childActions(List.of(
                                                        Action.builder().conversationText("Stupid kids...They can't think nowadays...").build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("What are YOU doing here??!!")
                                                .childActions(List.of(
                                                        Action.builder().conversationText("I work here you idiot! GET OUT!").build()))
                                                .build(),
                                        Action.builder()
                                                .conversationText("Is this The Black Hole Inn?")
                                                .childActions(List.of(
                                                        Action.builder().conversationText("No you idiot! LEAVE NOW!!").build()))
                                                .build()))
                                .build())
                        .build());
    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Dead body of a heretic")
                        .description("Has a stench of rotting flesh")
                        .typeOfItem(ItemEnum.JUNK)
                        .build(),
                Item.builder()
                        .itemName("Sacrificial knife")
                        .description("Used for rituals")
                        .typeOfItem(ItemEnum.ATTACK)
                        .build(),
                Item.builder()
                        .itemName("Torch")
                        .description("Might be useful")
                        .typeOfItem(ItemEnum.ATTACK)
                        .build()
        );
    }

    @Override
    public String getRoomDescription() {
        return "Word has it that the Church Dungeon was built for secret rituals and torturing heretics. Anyone who has gone inside, has never been seen again..";
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.pinimg.com/originals/94/72/39/947239b51bb11bd3802cd2ab29414633.jpg";
    }
}
