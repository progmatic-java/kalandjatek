package hu.progmatic.adventuregame.character;


import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;

import java.util.List;

public enum Race {
    ORC("https://i.imgur.com/lXcw2hg.png",
        "You are an Orc! You are a fierce fighter who is ready for any challenge!",
        200, 50, 200, 15, 10,
        List.of(
            Item.builder().itemName("Mace").typeOfItem(ItemEnum.ATTACK).description("So big.").attack(2).damage(10).build(),
            Item.builder().itemName("Rock shield").typeOfItem(ItemEnum.SHIELD).description("So rock.").defence(2).build()
        ),
        List.of(
            Item.builder().build()
        )),
    HUMAN("https://i.imgur.com/MQRZudq.png",
        "You are a Human! You remind me of a piece of stale bread... kinda boring, but it's fine, I guess!",
        150, 100, 200, 10,16,
        List.of(
            Item.builder().itemName("Mace").typeOfItem(ItemEnum.ATTACK).description("So big.").attack(2).damage(10).build(),
            Item.builder().itemName("Rock shield").typeOfItem(ItemEnum.SHIELD).description("So rock.").defence(2).build()
        ),
        List.of(
            Item.builder().build()
        )),
    ELF("https://i.imgur.com/13NVorA.png",
        "You are an Elf! You are a magical and mysterious being of human stature who are friendly with nature and animals... also you're pretty hot.",
        100, 200, 200,8,20,
        List.of(
            Item.builder().itemName("Mace").typeOfItem(ItemEnum.ATTACK).description("So big.").attack(2).damage(10).build(),
            Item.builder().itemName("Rock shield").typeOfItem(ItemEnum.SHIELD).description("So rock.").defence(2).build()
        ),
        List.of(
            Item.builder().build()
        )),
    REPTILIAN("https://i.imgur.com/0f45Fce.png",
        "You are a Reptilian Humanoid! You are a brave, yet ssssly fighter! If you were a Hogwarts House, you would be Slytherin.",
        150, 150, 200,12, 13,
        List.of(
            Item.builder().itemName("Mace").typeOfItem(ItemEnum.ATTACK).description("So big.").attack(2).damage(10).build(),
            Item.builder().itemName("Rock shield").typeOfItem(ItemEnum.SHIELD).description("So rock.").defence(2).build()
        ),
        List.of(
            Item.builder().build()
        ));

    final String img;
    final String description;
    final Integer hp;
    final Integer mp;
    final Integer gold;
    final Integer attack;
    final Integer defence;
    final List<Item> activeItems;
    final List<Item> invItems;

    Race(String img, String description, Integer hp, Integer mp, Integer gold, Integer attack, Integer defence, List<Item> activeItems, List<Item> invItems) {
        this.img = img;
        this.description = description;
        this.hp = hp;
        this.mp = mp;
        this.gold = gold;
        this.attack = attack;
        this.defence = defence;
        this.activeItems = activeItems;
        this.invItems = invItems;
    }
}
