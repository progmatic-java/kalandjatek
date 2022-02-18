package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.inventory.Inventory;
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
        Inventory lombok = new Inventory();
        lombok.setItems(List.of(
                Item.builder()
                        .itemName("Prayer book")
                        .typeOfItem(ItemEnum.ATTACK)
                        .value(30)
                        .description("A book containing prayers used in worship of Holy Joe")
                        .inventory(lombok)
                        .build(),
                Item.builder()
                        .itemName("The Blessing of Holy Joe")
                        .typeOfItem(ItemEnum.CONSUMABLE)
                        .description("Receiving this blessing will grant you the ability to see through deceptions")
                        .inventory(lombok)
                        .value(1000)
                        .build(),
                Item.builder()
                        .itemName("Relic")
                        .typeOfItem(ItemEnum.VALUABLE)
                        .description("It is said to be a handkerchief Holy Joe used to dry his tears with")
                        .inventory(lombok)
                        .value(150)
                        .build()));

        Inventory thyme = new Inventory();
        thyme.setItems(List.of(
                Item.builder()
                        .itemName("Map to the Academy of Magic and Programming")
                        .typeOfItem(ItemEnum.KEY)
                        .description("With this map you won't lose your way")
                        .inventory(thyme)
                        .value(20)
                        .build()));

        return List.of(
                NPC.builder()
                        .name("Padre Lombok")
                        .description("A celestial entity, the main priest of Progmatique")
                        .friendly(true)
                        .hp(1000)
                        .mp(1000)
                        .gold(10000)
                        .inventory(lombok)
                        .imgRef("https://i.imgur.com/UGvfBhP.png")
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
                                                                                                                                .build(),
                                                                                                        Action.builder()
                                                                                                                .conversationText("Let me prove how devoted I am")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("I appreciate your determination. However, to prove yourself to be worthy, I must make you go through some tribulations")
                                                                                                                                        .childActions(
                                                                                                                                                List.of(
                                                                                                                                                        Action.builder()
                                                                                                                                                                .conversationText("I'm ready, Padre. Let's start immediately!")
                                                                                                                                                                .childActions(
                                                                                                                                                                        List.of(
                                                                                                                                                                                Action.builder()
                                                                                                                                                                                        .conversationText("Very well, my friend. For your first trial, you'll kneel in front of the statue of the almighty Holy Joe. As cold as the stone may be and as much as your knees might hurt from the hard floor, you must stay there as long as I deem it necessary")
                                                                                                                                                                                        .build())
                                                                                                                                                                )
                                                                                                                                                                .build(),
                                                                                                                                                        Action.builder()
                                                                                                                                                                .conversationText("Tribulations? I don't think I'm ready for such sacred rituals...")
                                                                                                                                                                .childActions(
                                                                                                                                                                        List.of(
                                                                                                                                                                                Action.builder()
                                                                                                                                                                                        .conversationText("Oh, is that so? I shall make you demonstrate your faith in an underhanded way then. Come with me, my friend")
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
                                                                                                                                        .build()
                                                                                                                        )
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
                        .inventory(thyme)
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
                                                                .build(),
                                                        Action.builder()
                                                                .conversationText("I want to make a confession, for I have sinned")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("You've come to the right place! Let me give you a hand, my friend")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("That's great, thanks! Can we move to somewhere private?")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("I'll lead you to our confessional. We can have a confidential conversation there")
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
                                                                .conversationText("I'm looking for the way to the Academy")
                                                                .childActions(
                                                                        List.of(
                                                                                Action.builder()
                                                                                        .conversationText("You're in luck, my friend! I have a map that can help you on this arduous journey!")
                                                                                        .childActions(
                                                                                                List.of(
                                                                                                        Action.builder()
                                                                                                                .conversationText("Thanks! Here's some gold for your kindness")
                                                                                                                .childActions(
                                                                                                                        List.of(
                                                                                                                                Action.builder()
                                                                                                                                        .conversationText("Holy Joe bless your way, my friend")
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
