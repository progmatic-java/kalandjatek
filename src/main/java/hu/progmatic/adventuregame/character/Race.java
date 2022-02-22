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
                Item.builder().itemName("Battle Axe").typeOfItem(ItemEnum.ATTACK).description("So sharp.").attack(3).damage(10).build(),
                Item.builder().itemName("Spoon").typeOfItem(ItemEnum.ATTACK).description("So handy!").attack(1).damage(1).build(),
                Item.builder().itemName("Raw meat").typeOfItem(ItemEnum.CONSUMABLE).description("It's a bit chewy").hp(10).build()
        )),
    HUMAN("https://i.imgur.com/MQRZudq.png",
        "You are a Human! You remind me of a piece of stale bread... kinda boring, but it's fine, I guess!",
        150, 100, 200, 10,16,
        List.of(
            Item.builder().itemName("Long Sword").typeOfItem(ItemEnum.ATTACK).description("So long... And a bit rusty").attack(2).damage(8).build(),
            Item.builder().itemName("Steel shield").typeOfItem(ItemEnum.SHIELD).description("So hard..").defence(3).build()
        ),
        List.of(
                Item.builder().itemName("Fine wine").typeOfItem(ItemEnum.CONSUMABLE).description("So refreshing!").hp(10).build(),
                Item.builder().itemName("Liver pâté").typeOfItem(ItemEnum.CONSUMABLE).description("So ... Disgusting!!!!").hp(1).build(),
                Item.builder().itemName("Scroll of Lightning").typeOfItem(ItemEnum.CONSUMABLE).description("Thunder and Lightning!").mp(20).damage(20).build()
        )),
    ELF("https://i.imgur.com/13NVorA.png",
        "You are an Elf! You are a magical and mysterious being of human stature who are friendly with nature and animals... also you're pretty hot.",
        100, 200, 200,8,20,
        List.of(
            Item.builder().itemName("Bow and Arrow").typeOfItem(ItemEnum.ATTACK).description("Fast and accurate.").attack(3).damage(7).build(),
            Item.builder().itemName("Mithril chain mail").typeOfItem(ItemEnum.SHIELD).description("So shiny and stylish.").defence(5).build()
        ),
        List.of(
                Item.builder().itemName("Nectar").typeOfItem(ItemEnum.CONSUMABLE).description("Juicy...!").hp(20).build(),
                Item.builder().itemName("Elixir of Immortality").typeOfItem(ItemEnum.CONSUMABLE).description("Strengthens your vitality!").mp(30).damage(30).build(),
                Item.builder().itemName("Scroll of Thousand Calamities").typeOfItem(ItemEnum.CONSUMABLE).description("Unleashes chaotic forces!").mp(40).damage(40).build()
        )),
    REPTILIAN("https://i.imgur.com/0f45Fce.png",
        "You are a Reptilian Humanoid! You are a brave, yet ssssly fighter! If you were a Hogwarts House, you would be Slytherin.",
        150, 150, 200,12, 13,
        List.of(
            Item.builder().itemName("Trident").typeOfItem(ItemEnum.ATTACK).description("So spiky.").attack(4).damage(8).build(),
            Item.builder().itemName("Chitin shield").typeOfItem(ItemEnum.SHIELD).description("So stinky.").defence(3).build()
        ),
        List.of(
                Item.builder().itemName("Can of Flies").typeOfItem(ItemEnum.CONSUMABLE).description("So crunchy.").hp(20).build(),
                Item.builder().itemName("Acid Spit").typeOfItem(ItemEnum.CONSUMABLE).description("So dangerous.").mp(20).damage(20).build(),
                Item.builder().itemName("Hardened Skin").typeOfItem(ItemEnum.SHIELD).description("I wonder whose this was?").defence(3).build()
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
