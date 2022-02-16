package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.Action;
import hu.progmatic.adventuregame.npc.NPC;

import java.util.List;

public class ChurchInit extends InitRoom {

    @Override
    public String getName() {
        return "Church of Holy Joe";
    }

    @Override
    List<NPC> getInitNpcs() {
        return List.of(
                NPC.builder()
                        .name("Padre Lombok")
                        .description("A celestial entity, the main priest of Progmatique")
                        .friendly(true)
                        .hp(1000)
                        .mp(1000)
                        .gold(10000)
                        .imgRef("https://i.imgur.com/grNc1xh.png")
                                        .action(
                                Action.builder()
                                        .conversationText("Praise be to our Lord, Holy Joe. Welcome, my friend. Are you here for the common prayer?")
                                        .childActions(
                                                List.of(
                                                        Action.builder()
                                                                .conversationText("I'm seeking the blessing of Holy Joe")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("Only the most devoted ones to be held in good favour of our Lord")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("I'm sure the almighty Holy Joe would show compassion to this humble servant...")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("You must prove your resolution first. Bring me 1000 gold and we may talk again")
                                                                                                                                        .build())
                                                                                                                )
                                                                                                                .build()
                                                                                                )
                                                                                        )
                                                                                        .build()
                                                                        )
                                                                )
                                                                .build(),
                                                        Action.builder()
                                                                .conversationText("No. I heard word from someone about a secret ritual. I want to participate in it!")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("You must be mistaken, my friend")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("The ancient scroll of this ritual is in my property, you signed it yourself")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("You're a sly one, aren't you? ...Follow me")
                                                                                                                                        .build())
                                                                                                                )
                                                                                                                .build()
                                                                                                )
                                                                                        )
                                                                                        .build()
                                                                        )
                                                                )
                                                                .build(),
                                                        Action.builder()
                                                                .conversationText("Yeah, sure...")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("Wonderful! You may leave your contributions in the chest")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("No way! I'm as poor as church mice, I got no money for you!")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("That's exactly what a heretic would say, my friend. Be careful not to be accused as one")
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
                        )
                        .build(),
                NPC.builder()
                        .name("Acolyte Thyme")
                        .description("An altar-boy, Padre Lombok’s most eager devotee")
                        .friendly(true)
                        .hp(500)
                        .mp(50)
                        .gold(50)
                        .imgRef("https://i.imgur.com/7dbqJvD.png")
                        .action(
                                Action.builder()
                                        .conversationText("Praise be to our Lord, Holy Joe. Can I help you?")
                                        .childActions(
                                                List.of(
                                                        Action.builder()
                                                                .conversationText("Show me the way to the dungeons!")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("I beg your pardon, I have no permission to comply with your request. Unless...it stays a secret between us")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("I won't tell a soul!")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("Pray to Holy Joe you keep your word. Come with me")
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
                        )
                        .build()
        );
    }

    @Override
    List<Item> getInitItems() {
        return List.of(
                Item.builder()
                        .itemName("Holy water")
                        .description("Purifies you inside and out")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build(),
                Item.builder()
                        .itemName("The Holy Sword of Ma'dog")
                        .description("A relic of the almighty Holy Joe")
                        .typeOfItem(ItemEnum.ATTACK)
                        .build(),
                Item.builder()
                        .itemName("Candles")
                        .description("They light your path and max your wax")
                        .typeOfItem(ItemEnum.JUNK)
                        .build(),
                Item.builder()
                        .itemName("Magic Scroll")
                        .description("Source of ancient knowledge")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .build()
        );
    }

    @Override
    public String getRoomDescription() {
        return "A space built for worshipping Holy Joe, the deity of the kingdom, who was also known as the Bringer of the Light. The more energy you devote and money you donate to the Church, the bigger the chance that Holy Joe will bless you";
    }

    @Override
    public String getRoomImgRef() {
        return "https://i.pinimg.com/originals/1e/88/af/1e88af53ffdd1b34ae3d3e6cee4e7594.jpg";
    }
}
